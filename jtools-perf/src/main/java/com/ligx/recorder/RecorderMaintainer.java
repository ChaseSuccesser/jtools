package com.ligx.recorder;

import com.ligx.base.Constants;
import com.ligx.base.PropertiesValue;
import com.ligx.metrics.PerfStatsCalculator;
import com.ligx.metrics.impl.MethodMetrics;
import com.ligx.processor.AsyncMethodMetricsProcessor;
import com.ligx.processor.MetricsProcessor;
import com.ligx.processor.influxdb.InfluxDBMethodMetricsProcessor;
import com.ligx.processor.logger.LoggerMethodMetricsProcessor;
import com.ligx.tag.MethodTag;
import com.ligx.tag.MethodTagMaintainer;
import com.ligx.util.ProfilingConf;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReferenceArray;

/**
 * Author: ligongxing.
 * Date: 2018/09/20.
 */
public class RecorderMaintainer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RecorderMaintainer.class);

    private List<Recorders> recordersList;

    private int currIndex = 0;

    private volatile Recorders currRecorders;

    private MetricsProcessor<MethodMetrics> methodMetricProcessor;


    private static final RecorderMaintainer instance = new RecorderMaintainer();

    private RecorderMaintainer(){}

    public static RecorderMaintainer getInstance() {
        return instance;
    }


    ///////////////////////////// 初始化 ////////////////////////////////
    public boolean init() {
        switch (ProfilingConf.getInstance().getMethodMetricsProcessor()) {
            case PropertiesValue.LOGGER_METHOD_METRICS_PROCESSOR:
                this.methodMetricProcessor = new AsyncMethodMetricsProcessor(new LoggerMethodMetricsProcessor());
                break;
            case PropertiesValue.INFLUXDB_METHOD_METRICS_PROCESSOR:
                this.methodMetricProcessor = new AsyncMethodMetricsProcessor(new InfluxDBMethodMetricsProcessor());
                break;
            default:
                this.methodMetricProcessor = new AsyncMethodMetricsProcessor(new LoggerMethodMetricsProcessor());
        }
        int backupRecordersCount = getFitBackupRecordersCount(ProfilingConf.getInstance().getBackupRecordersCount());

        if (!initRecorders(backupRecordersCount)) {
            return false;
        }

        return true;
    }

    private int getFitBackupRecordersCount(int backupRecordersCount) {
        if (backupRecordersCount < Constants.MIN_BACKUP_RECORDERS_COUNT) {
            return Constants.MIN_BACKUP_RECORDERS_COUNT;
        } else if (backupRecordersCount > Constants.MAX_BACKUP_RECORDERS_COUNT) {
            return Constants.MAX_BACKUP_RECORDERS_COUNT;
        }
        return backupRecordersCount;
    }

    private boolean initRecorders(int backupRecordersCount) {
        recordersList = new ArrayList<>(backupRecordersCount + 1);
        for (int i = 0; i < backupRecordersCount + 1; i++) {
            Recorders recorders = new Recorders(new AtomicReferenceArray<>(Constants.MAX_METHOD_TAG_ID));
            recordersList.add(recorders);
        }
        currRecorders = recordersList.get(currIndex % recordersList.size());
        return true;
    }
    ///////////////////////////////////////////////////////////////////


    public void addRecorder(int methodTagId) {
        if (CollectionUtils.isEmpty(recordersList)) {
            LOGGER.warn("RecorderMaintainer#addRecorder, RecorderMaintainer暂未初始化完成!! methodTagId={}, MethodTag={}",
                    methodTagId, MethodTagMaintainer.getInstance().getMethodTag(methodTagId));
            return;
        }
        for (int i = 0; i < recordersList.size(); i++) {
            Recorders recorders = recordersList.get(i);
            recorders.setRecorderIfAbsent(methodTagId, AccurateRecorder.getInstance(methodTagId));
        }
    }

    public AccurateRecorder getRecorder(int methodTagId) {
        if (currRecorders == null) {
            LOGGER.warn("RecorderMaintainer#getRecorder, RecorderMaintainer暂未初始化完成!! methodTagId={}, MethodTag={}",
                    methodTagId, MethodTagMaintainer.getInstance().getMethodTag(methodTagId));
            return null;
        }
        return currRecorders.getRecorder(methodTagId);
    }

    public void run(long timeSliceStartMillTime, long timeSliceEndMillTime) {
        try {
            Recorders tmpCurrRecorders = currRecorders;

            currIndex = getNextIdx(currIndex);

            Recorders nextRecorders = recordersList.get(currIndex);
            nextRecorders.resetRecorder();
            currRecorders = nextRecorders;

            methodMetricProcessor.beforeProcess(timeSliceStartMillTime);
            int methodTagCount = MethodTagMaintainer.getInstance().getMethodTagCount();
            for (int i = 0; i < methodTagCount; i++) {
                AccurateRecorder recorder = tmpCurrRecorders.getRecorder(i);
                if (recorder == null || !recorder.isHasRecord()) {
                    continue;
                }
                MethodTag methodTag = MethodTagMaintainer.getInstance().getMethodTag(recorder.getMethodTagId());
                if (methodTag == null) {
                    LOGGER.error("RecorderMaintainer#run, MethodTag is null!! methodTagId={}", recorder.getMethodTagId());
                    continue;
                }
                MethodMetrics methodMetrics = PerfStatsCalculator.calPerfStats(recorder, methodTag, timeSliceStartMillTime, timeSliceEndMillTime);
                methodMetricProcessor.process(timeSliceStartMillTime, methodMetrics, timeSliceStartMillTime, timeSliceEndMillTime);
            }
            methodMetricProcessor.afterProcess(timeSliceStartMillTime, timeSliceStartMillTime, timeSliceEndMillTime);
        } catch (Exception e) {
            LOGGER.error("RecorderMaintainer#run, timeSliceStartMillTime={}, currIndex={}", timeSliceStartMillTime, currIndex, e);
        }
    }

    private int getNextIdx(int idx) {
        if (idx == Integer.MAX_VALUE) {
            return 0;
        }
        return (idx + 1) % recordersList.size();
    }
}
