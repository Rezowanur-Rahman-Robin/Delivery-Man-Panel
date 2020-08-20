package com.robin.dmpanel;

public class MProduct {


    private String product_name,p_id,product_size,product_weight,product_price,product_description,product_discount_percentage,product_discount_price,address_details,d_id,t_id,id,image_url,pickUp_district,pickUp_thana,product_type,pickUp_instructions;


    public MProduct(){

    }

    public MProduct(String product_name, String p_id, String product_size, String product_weight, String product_price, String product_description, String product_discount_percentage, String product_discount_price, String address_details, String d_id, String t_id, String id, String image_url, String pickUp_district, String pickUp_thana, String product_type, String pickUp_instructions) {
        this.product_name = product_name;
        this.p_id = p_id;
        this.product_size = product_size;
        this.product_weight = product_weight;
        this.product_price = product_price;
        this.product_description = product_description;
        this.product_discount_percentage = product_discount_percentage;
        this.product_discount_price = product_discount_price;
        this.address_details = address_details;
        this.d_id = d_id;
        this.t_id = t_id;
        this.id = id;
        this.image_url = image_url;
        this.pickUp_district = pickUp_district;
        this.pickUp_thana = pickUp_thana;
        this.product_type = product_type;
        this.pickUp_instructions = pickUp_instructions;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getP_id() {
        return p_id;
    }

    public void setP_id(String p_id) {
        this.p_id = p_id;
    }

    public String getProduct_size() {
        return product_size;
    }

    public void setProduct_size(String product_size) {
        this.product_size = product_size;
    }

    public String getProduct_weight() {
        return product_weight;
    }

    public void setProduct_weight(String product_weight) {
        this.product_weight = product_weight;
    }

    public String getProduct_price() {
        return product_price;
    }

    public void setProduct_price(String product_price) {
        this.product_price = product_price;
    }

    public String getProduct_description() {
        return product_description;
    }

    public void setProduct_description(String product_description) {
        this.product_description = product_description;
    }

    public String getProduct_discount_percentage() {
        return product_discount_percentage;
    }

    public void setProduct_discount_percentage(String product_discount_percentage) {
        this.product_discount_percentage = product_discount_percentage;
    }

    public String getProduct_discount_price() {
        return product_discount_price;
    }

    public void setProduct_discount_price(String product_discount_price) {
        this.product_discount_price = product_discount_price;
    }

    public String getAddress_details() {
        return address_details;
    }

    public void setAddress_details(String address_details) {
        this.address_details = address_details;
    }

    public String getD_id() {
        return d_id;
    }

    public void setD_id(String d_id) {
        this.d_id = d_id;
    }

    public String getT_id() {
        return t_id;
    }

    public void setT_id(String t_id) {
        this.t_id = t_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
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

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public String getPickUp_instructions() {
        return pickUp_instructions;
    }

    public void setPickUp_instructions(String pickUp_instructions) {
        this.pickUp_instructions = pickUp_instructions;
    }
}
