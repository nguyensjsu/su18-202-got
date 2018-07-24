package com.got.springbootmongodbgot.Service;


import com.got.springbootmongodbgot.Dao.PaymentDao;
import com.got.springbootmongodbgot.Entity.Balance;

import com.got.springbootmongodbgot.Entity.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentService {
    @Autowired
    private PaymentDao paymentdao;//Dependency Injection using Autowire

    public Payment add(double pay, String storecode, String userid)
    {
        return paymentdao.save(new Payment(userid, pay, storecode, LocalDateTime.now()));
    }

    public List<Payment> getpaymenthistory(String UserId)
    {
        return paymentdao.findPaymentsByuserid(UserId);
    }
}
