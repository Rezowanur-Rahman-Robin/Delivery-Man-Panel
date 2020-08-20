package com.robin.dmpanel;

public class Parcel {

    private long parcelId;
    private String status;
    private String userId;
    private String senderName;
    private String senderPhone;
    private String pickupAddress;
    private String pickupCity;
    private String pickupThana;
    private String pickupInstructions;
    private String recipientName;
    private String recipientPhone;
    private String recipientAddress;
    private String recipientCity;
    private String recipientThana;
    private String deliveryInstructions;
    private String packageType;
    private String packageSize;
    private String packageWeight;
    private String paymentMethod;
    private String totalAmount;
    private String TxdId;
    private String Date;

    public Parcel(){

    }

    public Parcel(long parcelId, String status, String userId, String senderName, String senderPhone, String pickupAddress, String pickupCity, String pickupThana, String pickupInstructions, String recipientName, String recipientPhone, String recipientAddress, String recipientCity, String recipientThana, String deliveryInstructions, String packageType, String packageSize, String packageWeight, String paymentMethod, String totalAmount, String txdId, String date) {
        this.parcelId = parcelId;
        this.status = status;
        this.userId = userId;
        this.senderName = senderName;
        this.senderPhone = senderPhone;
        this.pickupAddress = pickupAddress;
        this.pickupCity = pickupCity;
        this.pickupThana = pickupThana;
        this.pickupInstructions = pickupInstructions;
        this.recipientName = recipientName;
        this.recipientPhone = recipientPhone;
        this.recipientAddress = recipientAddress;
        this.recipientCity = recipientCity;
        this.recipientThana = recipientThana;
        this.deliveryInstructions = deliveryInstructions;
        this.packageType = packageType;
        this.packageSize = packageSize;
        this.packageWeight = packageWeight;
        this.paymentMethod = paymentMethod;
        this.totalAmount = totalAmount;
        TxdId = txdId;
        Date = date;
    }

    public long getParcelId() {
        return parcelId;
    }

    public void setParcelId(long parcelId) {
        this.parcelId = parcelId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderPhone() {
        return senderPhone;
    }

    public void setSenderPhone(String senderPhone) {
        this.senderPhone = senderPhone;
    }

    public String getPickupAddress() {
        return pickupAddress;
    }

    public void setPickupAddress(String pickupAddress) {
        this.pickupAddress = pickupAddress;
    }

    public String getPickupCity() {
        return pickupCity;
    }

    public void setPickupCity(String pickupCity) {
        this.pickupCity = pickupCity;
    }

    public String getPickupThana() {
        return pickupThana;
    }

    public void setPickupThana(String pickupThana) {
        this.pickupThana = pickupThana;
    }

    public String getPickupInstructions() {
        return pickupInstructions;
    }

    public void setPickupInstructions(String pickupInstructions) {
        this.pickupInstructions = pickupInstructions;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getRecipientPhone() {
        return recipientPhone;
    }

    public void setRecipientPhone(String recipientPhone) {
        this.recipientPhone = recipientPhone;
    }

    public String getRecipientAddress() {
        return recipientAddress;
    }

    public void setRecipientAddress(String recipientAddress) {
        this.recipientAddress = recipientAddress;
    }

    public String getRecipientCity() {
        return recipientCity;
    }

    public void setRecipientCity(String recipientCity) {
        this.recipientCity = recipientCity;
    }

    public String getRecipientThana() {
        return recipientThana;
    }

    public void setRecipientThana(String recipientThana) {
        this.recipientThana = recipientThana;
    }

    public String getDeliveryInstructions() {
        return deliveryInstructions;
    }

    public void setDeliveryInstructions(String deliveryInstructions) {
        this.deliveryInstructions = deliveryInstructions;
    }

    public String getPackageType() {
        return packageType;
    }

    public void setPackageType(String packageType) {
        this.packageType = packageType;
    }

    public String getPackageSize() {
        return packageSize;
    }

    public void setPackageSize(String packageSize) {
        this.packageSize = packageSize;
    }

    public String getPackageWeight() {
        return packageWeight;
    }

    public void setPackageWeight(String packageWeight) {
        this.packageWeight = packageWeight;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getTxdId() {
        return TxdId;
    }

    public void setTxdId(String txdId) {
        TxdId = txdId;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
