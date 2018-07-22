package com.got.springbootmongodbgot.Dao;

import com.got.springbootmongodbgot.Entity.Card;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PaymentDao extends MongoRepository<Card, String> {
    public double doPayment(String UserId, String Product);
}