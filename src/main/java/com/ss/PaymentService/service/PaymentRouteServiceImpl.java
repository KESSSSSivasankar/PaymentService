package com.ss.PaymentService.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ss.PaymentService.entity.Payment;
import com.ss.PaymentService.exceptions.PaymentNotFoundException;
import com.ss.PaymentService.repository.PaymentRepository;

import jakarta.transaction.Transactional;

@Service
public class PaymentRouteServiceImpl implements PaymentRouteService {

	@Autowired
	PaymentRepository PaymentRepository;
	
	@Override
	@Transactional
	public Payment saveBusPayment(Payment route) {
		// TODO Auto-generated method stub
		return PaymentRepository.save(route);
	}

	@Override
	public List<Payment> fetchBusPaymentList() throws PaymentNotFoundException{
		return (List<Payment>)
				PaymentRepository.findAll();
	}

	@Override
	@Transactional
	public Payment updateBusPayment(Payment route, Long routeId) throws PaymentNotFoundException{
		Payment depDB
        = PaymentRepository.findById(routeId)
              .get();

    if (Objects.nonNull(route.getBookingId())) {
        depDB.setBookingId(
        		route.getBookingId());
    }
    
    if (Objects.nonNull(route.getDateOfPaymment())) {
            depDB.setDateOfPaymment(
            		route.getDateOfPaymment());
     }
    
    return PaymentRepository.save(depDB);

	}

	@Override
	@Transactional
	public void deleteBusPaymentById(Long routeId) throws PaymentNotFoundException{
		PaymentRepository.deleteById(routeId);
		
	}

	@Override
	public Payment fetchBusPayment(Long routeId) throws PaymentNotFoundException{
		// TODO Auto-generated method stub
		return PaymentRepository.findById(routeId)
	              .get();
	}


}
