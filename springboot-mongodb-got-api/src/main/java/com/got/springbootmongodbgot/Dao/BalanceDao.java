package com.got.springbootmongodbgot.Dao;

import com.got.springbootmongodbgot.Entity.Card;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BalanceDao extends MongoRepository<Card, String> {
    //public List<Card> findCardByUserId(String UserId);
}