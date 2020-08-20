package com.robin.dmpanel;

public class Merchant {

    private String bkash,businessAddress,businessDetails,businessName,email,id,name,phone,profile_image_link,rocket,pickUp_district,pickUp_thana,address_details;


    public Merchant(){

    }

    public Merchant(String bkash, String businessAddress, String businessDetails, String businessName, String email, String id, String name, String phone, String profile_image_link, String rocket, String pickUp_district, String pickUp_thana, String address_details) {
        this.bkash = bkash;
        this.businessAddress = businessAddress;
        this.businessDetails = businessDetails;
        this.businessName = businessName;
        this.email = email;
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.profile_image_link = profile_image_link;
        this.rocket = rocket;
        this.pickUp_district = pickUp_district;
        this.pickUp_thana = pickUp_thana;
        this.address_details = address_details;
    }


    public String getBkash() {
        return bkash;
    }

    public void setBkash(String bkash) {
        this.bkash = bkash;
    }

    public String getBusinessAddress() {
        return businessAddress;
    }

    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }

    public String getBusinessDetails() {
        return businessDetails;
    }

    public void setBusinessDetails(String businessDetails) {
        this.businessDetails = businessDetails;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getProfile_image_link() {
        return profile_image_link;
    }

    public void setProfile_image_link(String profile_image_link) {
        this.profile_image_link = profile_image_link;
    }

    public String getRocket() {
        return rocket;
    }

    public void setRocket(String rocket) {
        this.rocket = rocket;
    }

    public String getPickUp_district() {
        return pickUp_district;
    }

    public void setPickUp_district(String pickUp_district) {
        this.pickUp_district = pickUp_district;
    }

    public String getPickUp_thana() {
        return pickUp_thana;
    }

    public void setPickUp_thana(String pickUp_thana) {
        this.pickUp_thana = pickUp_thana;
    }

    public String getAddress_details() {
        return address_details;
    }

    public void setAddress_details(String address_details) {
        this.address_details = address_details;
    }
}
