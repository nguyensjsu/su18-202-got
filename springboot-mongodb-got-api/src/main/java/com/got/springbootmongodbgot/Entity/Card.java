package com.got.springbootmongodbgot.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Card {
    @Id
    String id;
    String CardId;
    String CardCode;
    String UserId;

    public Card(String CardId, String CardCode, String UserId) {
        this.CardId = CardId;
        this.CardCode = CardCode;
        this.UserId = UserId;
    }

    public String getCardId() {
        return CardId;
    }

    public void setCardId(String cardId) {
        CardId = cardId;
    }

    public String getCardCode() {
        return CardCode;
    }

    public void setCardCode(String cardCode) {
        CardCode = cardCode;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

   // public String toString() {
     //   return "Person First Name:"+firstName+"Last Name:"+lastName+" age:"+age;
    //}
}
