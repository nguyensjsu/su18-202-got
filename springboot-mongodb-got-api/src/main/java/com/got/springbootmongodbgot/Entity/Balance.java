package com.got.springbootmongodbgot.Entity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document
public class Balance {
    @Id
    String id;
    String UserId;
    int Balance;
    LocalDateTime LastUpdatedOn;

    public Balance(String UserId, int Balance, LocalDateTime LastUpdatedOn) {
        this.UserId = UserId;
        this.Balance = Balance;
        this.LastUpdatedOn = LastUpdatedOn;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public int getBalance() {
        return Balance;
    }

    public void setBalance(int balance) {
        Balance = balance;
    }

    public LocalDateTime getLastUpdatedOn() {
        return LastUpdatedOn;
    }

    public void setLastUpdatedOn(LocalDateTime lastUpdatedOn) {
        LastUpdatedOn = lastUpdatedOn;
    }
}

