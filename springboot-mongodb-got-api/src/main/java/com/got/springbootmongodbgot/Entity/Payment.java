package com.got.springbootmongodbgot.Entity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
public class Payment {
    @Id
    String id;
    String userid;
    double payamount;
    String storecode;
    LocalDateTime lastupdatedon;

    public Payment(String userid, double payamount, String storecode, LocalDateTime lastupdatedon) {
        this.userid = userid;
        this.payamount = payamount;
        this.storecode = storecode;
        this.lastupdatedon = lastupdatedon;
    }

    public String getUserId() {
        return userid;
    }

    public void setUserId(String userId) {
        this.userid = userId;
    }

    public double getPayAmount() {
        return payamount;
    }

    public void setPayAmount(double payamount) {
        this.payamount = payamount;
    }

    public String getStoreCode() {
        return storecode;
    }

    public void setStoreCode(String storeCode) {
        this.storecode = storeCode;
    }

    public LocalDateTime getLastUpdatedOn() {
        return lastupdatedon;
    }

    public void setLastUpdatedOn(LocalDateTime lastUpdatedOn) {
        this.lastupdatedon = lastUpdatedOn;
    }
}
