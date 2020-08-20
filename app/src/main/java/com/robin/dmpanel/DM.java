package com.robin.dmpanel;

public class DM {

    private String address;
    private String city;
    private String driverId;
    private String jobLocationArea;
    private String jobLocationCity;
    private String licenceNumber;
    private String nId;
    private String name;
    private String phoneNumber;
    private String status;
    private String thana;
    private String vehicleType;

    public DM(){

    }

    public DM(String address, String city, String driverId, String jobLocationArea, String jobLocationCity, String licenceNumber, String nId, String name, String phoneNumber, String status, String thana, String vehicleType) {
        this.address = address;
        this.city = city;
        this.driverId = driverId;
        this.jobLocationArea = jobLocationArea;
        this.jobLocationCity = jobLocationCity;
        this.licenceNumber = licenceNumber;
        this.nId = nId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.thana = thana;
        this.vehicleType = vehicleType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getJobLocationArea() {
        return jobLocationArea;
    }

    public void setJobLocationArea(String jobLocationArea) {
        this.jobLocationArea = jobLocationArea;
    }

    public String getJobLocationCity() {
        return jobLocationCity;
    }

    public void setJobLocationCity(String jobLocationCity) {
        this.jobLocationCity = jobLocationCity;
    }

    public String getLicenceNumber() {
        return licenceNumber;
    }

    public void setLicenceNumber(String licenceNumber) {
        this.licenceNumber = licenceNumber;
    }

    public String getnId() {
        return nId;
    }

    public void setnId(String nId) {
        this.nId = nId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getThana() {
        return thana;
    }

    public void setThana(String thana) {
        this.thana = thana;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }
}
