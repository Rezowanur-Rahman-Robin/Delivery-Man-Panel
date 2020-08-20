package com.robin.dmpanel;

public class DmRequest {

    private String name,phone,email,nid,vehicle_type,licenseNo,district,address_details,thana,service_district,service_thana,status,id;

    public DmRequest(){

    }

    public DmRequest(String name, String phone, String email, String nid, String vehicle_type, String licenseNo, String district, String address_details, String thana, String service_district, String service_thana, String status, String id) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.nid = nid;
        this.vehicle_type = vehicle_type;
        this.licenseNo = licenseNo;
        this.district = district;
        this.address_details = address_details;
        this.thana = thana;
        this.service_district = service_district;
        this.service_thana = service_thana;
        this.status = status;
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getVehicle_type() {
        return vehicle_type;
    }

    public void setVehicle_type(String vehicle_type) {
        this.vehicle_type = vehicle_type;
    }

    public String getLicenseNo() {
        return licenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress_details() {
        return address_details;
    }

    public void setAddress_details(String address_details) {
        this.address_details = address_details;
    }

    public String getThana() {
        return thana;
    }

    public void setThana(String thana) {
        this.thana = thana;
    }

    public String getService_district() {
        return service_district;
    }

    public void setService_district(String service_district) {
        this.service_district = service_district;
    }

    public String getService_thana() {
        return service_thana;
    }

    public void setService_thana(String service_thana) {
        this.service_thana = service_thana;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
