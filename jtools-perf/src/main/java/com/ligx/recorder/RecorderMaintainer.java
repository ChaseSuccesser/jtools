package com.ligx.recorder;

import com.ligx.base.Constants;
import com.ligx.metrics.MethodMetrics;
import com.ligx.metrics.PerfStatsCalculator;
import com.ligx.processor.LoggerMethodMetricProcessor;
import com.ligx.tag.MethodTag;
import com.ligx.tag.MethodTagMaintainer;
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

    private LoggerMethodMetricProcessor methodMetricProcessor;


    private static final RecorderMaintainer instantce = new RecorderMaintainer();

    private RecorderMaintainer(){}

    public static RecorderMaintainer getInstantce() {
        return instantce;
    }


    public boolean init(LoggerMethodMetricProcessor methodMetricProcessor, int backupRecordersCount) {
        this.methodMetricProcessor = methodMetricProcessor;
        backupRecordersCount = getFitBackupRecordersCount(backupRecordersCount);

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

    public void addRecorder(int methodTagId) {
        for (int i = 0; i < recordersList.size(); i++) {
            Recorders recorders = recordersList.get(i);
            recorders.setRecorder(methodTagId, AccurateRecorder.getInstance(methodTagId));
        }
    }

    public AccurateRecorder getRecorder(int methodTagId) {
        return currRecorders.getRecorder(methodTagId);
    }

    public void run(long lastTimeSliceStartTime, long millTimeSlice) {
        try {
            Recorders tmpCurrRecorders = currRecorders;

            currIndex = getNextIdx(currIndex);
            LOGGER.info("RecorderMaintainer#run, round robin Recorders, currIndex={}", currIndex);

            Recorders nextRecorders = recordersList.get(currIndex);
            nextRecorders.resetRecorder();
            currRecorders = nextRecorders;

            methodMetricProcessor.beforeProcess(lastTimeSliceStartTime);
            int methodTagCount = MethodTagMaintainer.getInstance().getMethodTagCount();
            for (int i = 0; i < methodTagCount; i++) {
                AccurateRecorder recorder = tmpCurrRecorders.getRecorder(i);
                if (recorder == null || !recorder.isHasRecord()) {
                    continue;
                }
                MethodTag methodTag = MethodTagMaintainer.getInstance().getMethodTag(recorder.getMethodTagId());
                if (methodTag == null) {
                    continue;
                }
                MethodMetrics methodMetrics = PerfStatsCalculator.calPerfStats(recorder, methodTag, lastTimeSliceStartTime, lastTimeSliceStartTime + millTimeSlice);
                methodMetricProcessor.process(lastTimeSliceStartTime, methodMetrics);
            }
            methodMetricProcessor.afterProcess(lastTimeSliceStartTime, lastTimeSliceStartTime, lastTimeSliceStartTime + millTimeSlice);
        } catch (Exception e) {
            LOGGER.error("RecorderMaintainer#run, lastTimeSliceStartTime={}", lastTimeSliceStartTime, e);
        }
    }

    private int getNextIdx(int idx) {
        if (idx == Integer.MAX_VALUE) {
            return 0;
        }
        return (idx + 1) % recordersList.size();
    }
}
