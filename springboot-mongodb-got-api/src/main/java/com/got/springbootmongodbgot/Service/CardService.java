package com.got.springbootmongodbgot.Service;

import com.got.springbootmongodbgot.Dao.CardDao;
import com.got.springbootmongodbgot.Entity.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;

@Service
public class CardService {

    @Autowired
    private CardDao carddao;//Dependency Injection using Autowire



    public List<Card> getall()
    {
        return carddao.findAll();
    }

    public List<Card> getcards(String UserId)
    {
        return carddao.findCardsByuserid(UserId);
    }


    public Card create(String CardId, String cardCode, double cardvalue, String userid)
    {
        return carddao.save(new Card(CardId,cardCode, cardvalue, userid, LocalDateTime.now()));
    }

}
