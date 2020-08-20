package com.robin.dmpanel;

public class Purchase {

    private String userID;
    private String BuyerName;
    private String ShippingAddress;
    private String ShippingCity;
    private String ShippingThana;
    private String Date;
    private String Total_taka;
    private String PaymentType;
    private String TrxID;
    private String status;
    private long OrderNo;

    public Purchase(){

    }

    public Purchase(String userID, String buyerName, String shippingAddress, String shippingCity, String shippingThana, String date, String total_taka, String paymentType, String trxID, String status, long orderNo) {
        this.userID = userID;
        BuyerName = buyerName;
        ShippingAddress = shippingAddress;
        ShippingCity = shippingCity;
        ShippingThana = shippingThana;
        Date = date;
        Total_taka = total_taka;
        PaymentType = paymentType;
        TrxID = trxID;
        this.status = status;
        OrderNo = orderNo;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getBuyerName() {
        return BuyerName;
    }

    public void setBuyerName(String buyerName) {
        BuyerName = buyerName;
    }

    public String getShippingAddress() {
        return ShippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        ShippingAddress = shippingAddress;
    }

    public String getShippingCity() {
        return ShippingCity;
    }

    public void setShippingCity(String shippingCity) {
        ShippingCity = shippingCity;
    }

    public String getShippingThana() {
        return ShippingThana;
    }

    public void setShippingThana(String shippingThana) {
        ShippingThana = shippingThana;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTotal_taka() {
        return Total_taka;
    }

    public void setTotal_taka(String total_taka) {
        Total_taka = total_taka;
    }

    public String getPaymentType() {
        return PaymentType;
    }

    public void setPaymentType(String paymentType) {
        PaymentType = paymentType;
    }

    public String getTrxID() {
        return TrxID;
    }

    public void setTrxID(String trxID) {
        TrxID = trxID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getOrderNo() {
        return OrderNo;
    }

    public void setOrderNo(long orderNo) {
        OrderNo = orderNo;
    }
}
