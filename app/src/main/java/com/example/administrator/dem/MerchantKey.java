package com.example.administrator.dem;


public class MerchantKey {
    String merchantID, name, coupon, location, distance, picUrl,
            couponType, cardType, groupType;
    double gpsX, gpsY;
    int goodSayNum, midSayNum, badSayNum;

    public String getCouponType() {
        return couponType;
    }

    public void setCouponType(String couponType) {
        this.couponType = couponType;
    }

    public String getMerchantID() {
        return merchantID;
    }

    public void setMerchantID(String merchantID) {
        this.merchantID = merchantID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoupon() {
        return coupon;
    }

    public void setCoupon(String coupon) {
        this.coupon = coupon;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getGroupType() {
        return groupType;
    }

    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }

    public double getGpsX() {
        return gpsX;
    }

    public void setGpsX(double gpsX) {
        this.gpsX = gpsX;
    }

    public double getGpsY() {
        return gpsY;
    }

    public void setGpsY(double gpsY) {
        this.gpsY = gpsY;
    }

    public int getGoodSayNum() {
        return goodSayNum;
    }

    public void setGoodSayNum(int goodSayNum) {
        this.goodSayNum = goodSayNum;
    }

    public int getMidSayNum() {
        return midSayNum;
    }

    public void setMidSayNum(int midSayNum) {
        this.midSayNum = midSayNum;
    }

    public int setMerchantID() {
        return badSayNum;
    }

    public void setBadSayNum(int badSayNum) {
        this.badSayNum = badSayNum;
    }


}
