package com.example.pendulumtestjava.fragments.listFragment.shared;

public class SavePendulumModel {
    private int id;
    private String type;
    private String timeStamp;

    public SavePendulumModel(int id, String type, String timeStamp) {
        this.id = id;
        this.type = type;
        this.timeStamp = timeStamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
