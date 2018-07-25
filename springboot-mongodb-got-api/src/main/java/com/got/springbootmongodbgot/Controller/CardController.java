package com.got.springbootmongodbgot.Controller;


import com.got.springbootmongodbgot.Entity.Card;
import com.got.springbootmongodbgot.Service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.got.springbootmongodbgot.Service.BalanceService;

import java.util.List;

@RestController
public class CardController {


    @Autowired
    private CardService cardService;  //Dependency Injection using Autowire
    @Autowired
    private BalanceService balanceService;


    @RequestMapping(value = "/addcard", method = RequestMethod.POST)
    public String addcard(@RequestParam String cardid, @RequestParam String cardcode, @RequestParam double cardvalue,@RequestParam String userid) {

        if(cardid.length() != 9 || cardcode.length() != 3)
            return "This is not a valid card. Please enter 9 digits for Card id and 3 digit for Card Code."+"\n You entered cardid="+cardid+", cardcode="+cardcode+", cardvalue="+cardvalue+", userid="+userid+", CardId len="+cardid.length()+", Card Code len="+cardcode.length();
        Card p = cardService.create(cardid,cardcode, cardvalue, userid);
        if(cardvalue == 0)
            cardvalue = 20;

        balanceService.addCardBalance(userid, cardvalue); //updating balance as per card
        return "Card is successfully added.";//p.toString();
    }

    @RequestMapping("/getall")
    public List<Card> getall(){
        return cardService.getall();
    }

    @RequestMapping("/getcardshistory")
    public List<Card> getcardshistory(@RequestParam String userid){
        return cardService.getcards(userid);
    }
}
