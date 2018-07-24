package com.got.springbootmongodbgot.Service;

import com.got.springbootmongodbgot.Dao.BalanceDao;
import com.got.springbootmongodbgot.Entity.Balance;
import com.got.springbootmongodbgot.Entity.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class BalanceService {


    @Autowired
    private BalanceDao balancedao;//Dependency Injection using Autowire

    public double getTotalBalance(String UserId)
    {
        List<Balance> lst = balancedao.findBalanceByuserid(UserId);
        if(!lst.isEmpty())
        {
            Balance bal1= lst.get(0);
            double bal = bal1.getBalance();
            return bal;
        }
        return 0;
    }

    public Balance addCardBalance(String userid, double balance)
    {
        List<Balance> lst = balancedao.findBalanceByuserid(userid);
        if(lst.isEmpty())
            return balancedao.save(new Balance(userid,balance, LocalDateTime.now()));
        else
        {
            Balance bal1= lst.get(0);
            double bal = bal1.getBalance();
            bal1.setBalance(bal+balance);

            return balancedao.save(bal1);
        }
    }

    public String deductPaymentBalance(double paybalance, String userid)
    {
        List<Balance> lst = balancedao.findBalanceByuserid(userid);
        if(lst.isEmpty())
            return "You do not have sufficient fund to pay. Please reload your account first.";
        else
        {
            Balance bal1= lst.get(0);
            double bal = bal1.getBalance();
            if(bal < paybalance)
                return "You do not have sufficient fund to pay. Please reload your account first.";
            else
            {
                //add pay history
                bal1.setBalance(bal-paybalance);
                balancedao.save(bal1);
            }

            return "You paid successfully. Enjoy your Coffee. ";
        }
    }

}