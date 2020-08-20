package com.robin.dmpanel;

public class ParcelDm {

    private String parcelId;
    private long time;
    private String action;
    private String driverId;
    private String type;


    public ParcelDm(){

    }
    public ParcelDm(String parcelId, long time, String action, String driverId, String type) {
        this.parcelId = parcelId;
        this.time = time;
        this.action = action;
        this.driverId = driverId;
        this.type = type;
    }

    public String getParcelId() {
        return parcelId;
    }

    public void setParcelId(String parcelId) {
        this.parcelId = parcelId;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
