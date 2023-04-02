package com.ss.PaymentService.repository;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ss.PaymentService.entity.Payment;

@Repository
public interface PaymentRepository extends CrudRepository<Payment, Long> {

}

