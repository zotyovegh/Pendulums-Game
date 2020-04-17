package com.example.pendulumtestjava.main;

public class TempListItem {
    private String timeStamp;
    private int mIconId;

    public TempListItem(String timeStamp, int mIconId) {
        this.timeStamp = timeStamp;
        this.mIconId = mIconId;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public int getmIconId() {
        return mIconId;
    }
}
