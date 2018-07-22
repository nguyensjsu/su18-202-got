package com.got.springbootmongodbgot.Entity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
public class Payment {
    @Id
    String id;
    String UserId;
    String ProductCode;
    String StoreCode;
    LocalDateTime LastUpdatedOn;

    public Payment(String UserId, String ProductCode, String StoreCode, LocalDateTime LastUpdatedOn) {
        this.UserId = UserId;
        this.ProductCode = ProductCode;
        this.StoreCode = StoreCode;
        this.LastUpdatedOn = LastUpdatedOn;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getProductCode() {
        return ProductCode;
    }

    public void setProductCode(String productCode) {
        ProductCode = productCode;
    }

    public String getStoreCode() {
        return StoreCode;
    }

    public void setStoreCode(String storeCode) {
        StoreCode = storeCode;
    }

    public LocalDateTime getLastUpdatedOn() {
        return LastUpdatedOn;
    }

    public void setLastUpdatedOn(LocalDateTime lastUpdatedOn) {
        LastUpdatedOn = lastUpdatedOn;
    }
}
