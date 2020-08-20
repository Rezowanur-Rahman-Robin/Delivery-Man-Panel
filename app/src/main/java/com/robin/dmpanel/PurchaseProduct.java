package com.robin.dmpanel;

public class PurchaseProduct {

    private String productID;
    private String MerchantID;
    private String MerchantName;
    private String productName;
    private String Quantity;
    private String Price;
    private String deliveryCharge;

    public PurchaseProduct(){

    }

    public PurchaseProduct(String productID, String merchantID, String merchantName, String productName, String quantity, String price, String deliveryCharge) {
        this.productID = productID;
        MerchantID = merchantID;
        MerchantName = merchantName;
        this.productName = productName;
        Quantity = quantity;
        Price = price;
        this.deliveryCharge = deliveryCharge;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getMerchantID() {
        return MerchantID;
    }

    public void setMerchantID(String merchantID) {
        MerchantID = merchantID;
    }

    public String getMerchantName() {
        return MerchantName;
    }

    public void setMerchantName(String merchantName) {
        MerchantName = merchantName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getDeliveryCharge() {
        return deliveryCharge;
    }

    public void setDeliveryCharge(String deliveryCharge) {
        this.deliveryCharge = deliveryCharge;
    }
}
