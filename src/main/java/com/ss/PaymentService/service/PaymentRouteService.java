package com.ss.PaymentService.service;

import java.util.List;

import com.ss.PaymentService.entity.Payment;
import com.ss.PaymentService.exceptions.PaymentNotFoundException;

public interface PaymentRouteService {

	
    // Save operation
	Payment saveBusPayment(Payment Payment);
 
    // Read operation
    List<Payment> fetchBusPaymentList() throws PaymentNotFoundException;
    
    // Read operation
    Payment fetchBusPayment(Long PaymentId) throws PaymentNotFoundException;
 
    // Update operation
    Payment updateBusPayment(Payment Payment,
                                Long PaymentId) throws PaymentNotFoundException;
 
    // Delete operation
    void deleteBusPaymentById(Long PaymentId) throws PaymentNotFoundException;
}
