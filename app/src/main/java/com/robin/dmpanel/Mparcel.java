package com.robin.dmpanel;

public class Mparcel {

    private String buyer_id,merchant_id,order_time,payment_type,percel_id,receiver_address_details,receiver_district,receiver_name,receiver_phone,receiver_thana,sender_name,sender_phone,status,total_amount_of_order,total_amount_of_order_for_merchant,trxID;
    private long order_no;

    public Mparcel(){
        
    }

    public Mparcel(String buyer_id, String merchant_id, String order_time, String payment_type, String percel_id, String receiver_address_details, String receiver_district, String receiver_name, String receiver_phone, String receiver_thana, String sender_name, String sender_phone, String status, String total_amount_of_order, String total_amount_of_order_for_merchant, String trxID, long order_no) {
        this.buyer_id = buyer_id;
        this.merchant_id = merchant_id;
        this.order_time = order_time;
        this.payment_type = payment_type;
        this.percel_id = percel_id;
        this.receiver_address_details = receiver_address_details;
        this.receiver_district = receiver_district;
        this.receiver_name = receiver_name;
        this.receiver_phone = receiver_phone;
        this.receiver_thana = receiver_thana;
        this.sender_name = sender_name;
        this.sender_phone = sender_phone;
        this.status = status;
        this.total_amount_of_order = total_amount_of_order;
        this.total_amount_of_order_for_merchant = total_amount_of_order_for_merchant;
        this.trxID = trxID;
        this.order_no = order_no;
    }

    public String getBuyer_id() {
        return buyer_id;
    }

    public void setBuyer_id(String buyer_id) {
        this.buyer_id = buyer_id;
    }

    public String getMerchant_id() {
        return merchant_id;
    }

    public void setMerchant_id(String merchant_id) {
        this.merchant_id = merchant_id;
    }

    public String getOrder_time() {
        return order_time;
    }

    public void setOrder_time(String order_time) {
        this.order_time = order_time;
    }

    public String getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
    }

    public String getPercel_id() {
        return percel_id;
    }

    public void setPercel_id(String percel_id) {
        this.percel_id = percel_id;
    }

    public String getReceiver_address_details() {
        return receiver_address_details;
    }

    public void setReceiver_address_details(String receiver_address_details) {
        this.receiver_address_details = receiver_address_details;
    }

    public String getReceiver_district() {
        return receiver_district;
    }

    public void setReceiver_district(String receiver_district) {
        this.receiver_district = receiver_district;
    }

    public String getReceiver_name() {
        return receiver_name;
    }

    public void setReceiver_name(String receiver_name) {
        this.receiver_name = receiver_name;
    }

    public String getReceiver_phone() {
        return receiver_phone;
    }

    public void setReceiver_phone(String receiver_phone) {
        this.receiver_phone = receiver_phone;
    }

    public String getReceiver_thana() {
        return receiver_thana;
    }

    public void setReceiver_thana(String receiver_thana) {
        this.receiver_thana = receiver_thana;
    }

    public String getSender_name() {
        return sender_name;
    }

    public void setSender_name(String sender_name) {
        this.sender_name = sender_name;
    }

    public String getSender_phone() {
        return sender_phone;
    }

    public void setSender_phone(String sender_phone) {
        this.sender_phone = sender_phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTotal_amount_of_order() {
        return total_amount_of_order;
    }

    public void setTotal_amount_of_order(String total_amount_of_order) {
        this.total_amount_of_order = total_amount_of_order;
    }

    public String getTotal_amount_of_order_for_merchant() {
        return total_amount_of_order_for_merchant;
    }

    public void setTotal_amount_of_order_for_merchant(String total_amount_of_order_for_merchant) {
        this.total_amount_of_order_for_merchant = total_amount_of_order_for_merchant;
    }

    public String getTrxID() {
        return trxID;
    }

    public void setTrxID(String trxID) {
        this.trxID = trxID;
    }

    public long getOrder_no() {
        return order_no;
    }

    public void setOrder_no(long order_no) {
        this.order_no = order_no;
    }
}
