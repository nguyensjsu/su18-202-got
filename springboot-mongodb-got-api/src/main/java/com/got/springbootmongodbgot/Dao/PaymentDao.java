package com.got.springbootmongodbgot.Dao;
import com.got.springbootmongodbgot.Entity.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

//Dependency Injection convers to dao at runtime
@Repository
public interface PaymentDao extends MongoRepository<Payment, String> {
    public List<Payment> findPaymentsByuserid(String UserId);
}