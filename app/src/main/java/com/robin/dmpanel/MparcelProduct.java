package com.robin.dmpanel;

public class MparcelProduct {

    private String id,ppid,product_id,product_price,product_quantity,sender_address_details,sender_district,sender_thana;

    public MparcelProduct(){

    }

    public MparcelProduct(String id, String ppid, String product_id, String product_price, String product_quantity, String sender_address_details, String sender_district, String sender_thana) {
        this.id = id;
        this.ppid = ppid;
        this.product_id = product_id;
        this.product_price = product_price;
        this.product_quantity = product_quantity;
        this.sender_address_details = sender_address_details;
        this.sender_district = sender_district;
        this.sender_thana = sender_thana;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPpid() {
        return ppid;
    }

    public void setPpid(String ppid) {
        this.ppid = ppid;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getProduct_price() {
        return product_price;
    }

    public void setProduct_price(String product_price) {
        this.product_price = product_price;
    }

    public String getProduct_quantity() {
        return product_quantity;
    }

    public void setProduct_quantity(String product_quantity) {
        this.product_quantity = product_quantity;
    }

    public String getSender_address_details() {
        return sender_address_details;
    }

    public void setSender_address_details(String sender_address_details) {
        this.sender_address_details = sender_address_details;
    }

    public String getSender_district() {
        return sender_district;
    }

    public void setSender_district(String sender_district) {
        this.sender_district = sender_district;
    }

    public String getSender_thana() {
        return sender_thana;
    }

    public void setSender_thana(String sender_thana) {
        this.sender_thana = sender_thana;
    }
}
