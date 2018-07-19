package com.got.springbootmongodbgot.Service;

import com.got.springbootmongodbgot.Dao.CardDao;
import com.got.springbootmongodbgot.Entity.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {

    @Autowired
    private CardDao carddao;



    public List<Card> getAll()
    {
        return carddao.findAll();
    }

    public Card create(String CardId, String cardCode, String userid)
    {
        return carddao.save(new Card(CardId,cardCode, userid));
    }

}
