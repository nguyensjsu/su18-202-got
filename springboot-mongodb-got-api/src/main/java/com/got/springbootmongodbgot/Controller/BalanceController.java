package com.got.springbootmongodbgot.Controller;
import com.got.springbootmongodbgot.Service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class BalanceController {
    @Autowired
    private BalanceService balanceService;  //Dependency Injection using Autowire
    @RequestMapping("/getbalance")
    public double getbalance(@RequestParam String userid){
        return balanceService.getTotalBalance(userid);
    }
}
