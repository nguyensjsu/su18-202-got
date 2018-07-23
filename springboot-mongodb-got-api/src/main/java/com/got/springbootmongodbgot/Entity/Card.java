package com.got.springbootmongodbgot.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
public class Card {
    @Id
    String id;
    String cardid;
    String cardcode;
    double cardvalue;
    String userid;
    LocalDateTime lastupdatedon;

    public Card(String cardid, String cardcode, double cardvalue, String userid, LocalDateTime lastupdatedon) {
        this.cardid = cardid;
        this.cardcode = cardcode;
        this.cardvalue = cardvalue;
        this.userid = userid;
        this.lastupdatedon = lastupdatedon;
    }

    public String getCardId() {
        return cardid;
    }

    public void setCardId(String CardId) {
        this.cardid = CardId;
    }

    public String getCardCode() {
        return cardcode;
    }

    public void setCardCode(String CardCode) {
        this.cardcode = CardCode;
    }

    public String getUserId() {
        return userid;
    }

    public void setUserId(String UserId) {
        this.userid = UserId;
    }

    public double getCardValue() {
        return cardvalue;
    }

    public void setCardValue(double cardvalue) {
        this.cardvalue = cardvalue;
    }

   // public String toString() {
     //   return "Person First Name:"+firstName+"Last Name:"+lastName+" age:"+age;
    //}
}
