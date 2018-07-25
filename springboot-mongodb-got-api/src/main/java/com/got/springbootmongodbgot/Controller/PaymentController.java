package com.got.springbootmongodbgot.Controller;

import com.got.springbootmongodbgot.Entity.Payment;
import com.got.springbootmongodbgot.Service.BalanceService;
import com.got.springbootmongodbgot.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PaymentController {
    @Autowired
    private BalanceService balanceService;

    @Autowired
    private PaymentService paymentService;

    @RequestMapping(value = "/pay", method = RequestMethod.POST)
    public String addcard(@RequestBody Payment payment){
   // other way public String pay(@RequestParam double amount, @RequestParam String storecode, @RequestParam String userid) {
        String storecode = payment.getStoreCode();
        double amount = payment.getPayAmount();
        String userid = payment.getUserId();
        if(amount < 0)
            return "This is not a valid amount to pay.";

        double currentbal = balanceService.getTotalBalance(userid);
        if(currentbal < amount)
            return "You do not have sufficient fund to pay. Please reload your account first.";
        else
            paymentService.add(amount,storecode, userid);

        //do payment
        String ret = balanceService.deductPaymentBalance(amount, userid); //updating balance as per card

        return ret;
    }

    @RequestMapping("/getpayhistory")
    public List<Payment> getpayhistory(@RequestParam String userid){
        return paymentService.getpaymenthistory(userid);
    }
}
