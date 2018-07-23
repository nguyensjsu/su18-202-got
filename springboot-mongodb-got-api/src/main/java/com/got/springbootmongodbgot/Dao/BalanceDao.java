package com.got.springbootmongodbgot.Dao;

import com.got.springbootmongodbgot.Entity.Balance;
import com.got.springbootmongodbgot.Entity.Card;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//Dependency Injection convers to dao at runtime
@Repository
public interface BalanceDao extends MongoRepository<Balance, String> {
    public List<Balance> findBalanceByuserid(String UserId);
}