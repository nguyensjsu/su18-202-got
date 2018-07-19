package com.got.springbootmongodbgot.Controller;

import com.got.springbootmongodbgot.Entity.Card;
import com.got.springbootmongodbgot.Service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CardController {

    @Autowired
    private CardService cardService;

    @RequestMapping("/create")
    public String create(@RequestParam String cardid, @RequestParam String cardcode, @RequestParam String userid) {
        Card p = cardService.create(cardid,cardcode, userid);
        return p.toString();
    }
/*
    @RequestMapping("/get")
    public Card getPerson(@RequestParam String firstName) {
        return cardService.getByFirstName(firstName);
    }
    */
    @RequestMapping("/getAll")
    public List<Card> getAll(){
        return cardService.getAll();
    }

}
