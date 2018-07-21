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

}
