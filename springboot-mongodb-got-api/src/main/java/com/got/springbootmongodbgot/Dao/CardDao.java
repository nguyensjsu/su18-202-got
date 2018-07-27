package com.got.springbootmongodbgot.Dao;
import java.util.List;
import com.got.springbootmongodbgot.Entity.Card;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

//Dependency Injection convers to dao at runtime
@Repository
public interface CardDao extends MongoRepository<Card, String>{
    public List<Card> findCardsByuserid(String UserId);
    //List<Card> findCardsByRegexpName(String regexp);
}

