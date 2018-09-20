package com.ligx.recorder;

import com.ligx.Constants;
import com.ligx.processor.LoggerMethodMetricProcessor;
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

    public void addRecorder(int methodTagId, AccurateRecorder recorder) {
        for (int i = 0; i < recordersList.size(); i++) {
            Recorders recorders = recordersList.get(i);
            recorders.setRecorder(methodTagId, recorder);
        }
    }

    public AccurateRecorder getRecorder(int methodTagId) {
        return currRecorders.getRecorder(methodTagId);
    }

    public void run(long lastTimeSliceStartTime, long millTimeSlice) {
        try {
            Recorders tmpCurrRecorders = currRecorders;
            tmpCurrRecorders.setStartTime(lastTimeSliceStartTime);
            tmpCurrRecorders.setEndTime(lastTimeSliceStartTime + millTimeSlice);

            currIndex = getNextIdx(currIndex);
            LOGGER.info("RecorderMaintainer#run, round robin Recorders, currIndex={}", currIndex);

            Recorders nextRecorders = recordersList.get(currIndex);
            nextRecorders.setStartTime(lastTimeSliceStartTime + millTimeSlice);
            nextRecorders.setEndTime(lastTimeSliceStartTime + millTimeSlice * 2);
            nextRecorders.resetRecorder();
            currRecorders = nextRecorders;

            methodMetricProcessor.beforeProcess(lastTimeSliceStartTime);

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
