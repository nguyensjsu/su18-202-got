package com.got.springbootmongodbgot.Entity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document
public class Balance {
    @Id
    String id;
    String userid;
    double balance;
    LocalDateTime lastupdatedon;

    public Balance(String userid, double balance, LocalDateTime lastupdatedon) {
        this.userid = userid;
        this.balance = balance;
        this.lastupdatedon = lastupdatedon;
    }

    public String getUserId() {
        return userid;
    }

    public void setUserId(String userId) {
        this.userid = userId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public LocalDateTime getLastUpdatedOn() {
        return this.lastupdatedon;
    }

    public void setLastUpdatedOn(LocalDateTime lastUpdatedOn) {
        this.lastupdatedon = lastUpdatedOn;
    }
}

