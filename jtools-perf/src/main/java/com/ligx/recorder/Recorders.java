package com.ligx.recorder;

import java.util.concurrent.atomic.AtomicReferenceArray;

/**
 * Author: ligongxing.
 * Date: 2018/09/20.
 */
public class Recorders {

    private AtomicReferenceArray<AccurateRecorder> recorderArr;


    public Recorders(AtomicReferenceArray<AccurateRecorder> recorderArr) {
        this.recorderArr = recorderArr;
    }

    public AccurateRecorder getRecorder(int index) {
        return recorderArr.get(index);
    }

    public void setRecorder(int index, AccurateRecorder recorder) {
        recorderArr.set(index, recorder);
    }

    public int size() {
        return recorderArr.length();
    }

    public void resetRecorder() {
        int count = recorderArr.length();
        for (int i = 0; i < count; i++) {
            AccurateRecorder recorder = recorderArr.get(i);
            if (recorder != null) {
                recorder.resetRecord();
            }
        }
    }
}
