package com.got.springbootmongodbgot.Controller;

import com.got.springbootmongodbgot.Entity.Payment;
import com.got.springbootmongodbgot.Service.BalanceService;
import com.got.springbootmongodbgot.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class PaymentController {
    @Autowired
    private BalanceService balanceService;

    @Autowired
    private PaymentService paymentService;

    @RequestMapping(value = "/pay", method = RequestMethod.POST)
    public String pay(@RequestParam double amount, @RequestParam String storecode, @RequestParam String userid) {

        if(amount < 0)
            return "This is not a valid amount to pay.";

        double currentbal = balanceService.getTotalBalance(userid);
        if(currentbal < amount)
            return "You do not have sufficient fund to pay. Please reload your account first.";
        else
            paymentService.add(amount,storecode, userid);

        //do payment
        String ret = balanceService.deductPaymentBalance(amount, userid); //updating balance as per card

        final String uri = "http://cmpe202.redbeach.net/addRewards.php?serverKey=202cmpepayapi&userId=" + userid + "&amount=" + amount;

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);

        //System.out.println(result);

        return ret;
    }

    @RequestMapping("/getpayhistory")
    public List<Payment> getpayhistory(@RequestParam String userid){
        return paymentService.getpaymenthistory(userid);
    }
}
