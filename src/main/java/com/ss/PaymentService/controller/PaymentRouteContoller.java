package com.ss.PaymentService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ss.PaymentService.broker.JMSProducer;
import com.ss.PaymentService.entity.Payment;
import com.ss.PaymentService.exceptions.PaymentNotFoundException;
import com.ss.PaymentService.service.PaymentRouteService;

@RestController
public class PaymentRouteContoller {
	
	
	@Autowired  
	PaymentRouteService PaymentRouteService;
	
	@Autowired
    JMSProducer jmsProducer;

    @PostMapping(value="/jms/payment")
    public Payment sendMessage(@RequestBody Payment employee){
    	Payment pay = PaymentRouteService.fetchBusPayment(employee.getPaymentId());
        jmsProducer.sendMessage(pay);
        return pay;
    }
	
	
	@GetMapping("/payment")  
	private ResponseEntity<List<Payment>> getAllBusRoutes() throws PaymentNotFoundException 
	{  
	return new ResponseEntity<>(PaymentRouteService.fetchBusPaymentList(), 
			   HttpStatus.OK);
	}  
	
	@GetMapping("/payment/{paymentid}")  
	private ResponseEntity<Payment> getBusroutesId(@PathVariable("paymentid") long busroutesid) throws PaymentNotFoundException  
	{  
	return new ResponseEntity<>(PaymentRouteService.fetchBusPayment(busroutesid), 
			   HttpStatus.OK);
	}  
	
	@DeleteMapping("/payment/{paymentid}")  
	private ResponseEntity<String> deleteBook(@PathVariable("Paymentid") long paymentid)  throws PaymentNotFoundException 
	{  
		try{
			PaymentRouteService.deleteBusPaymentById(paymentid);  
			return ResponseEntity.ok("Entity deleted");      
		   }
		   catch (EmptyResultDataAccessException e){
		      return ResponseEntity.notFound().build();
		  } 
		
		
	}
	
	@PostMapping("/payment")  
	@ResponseStatus(HttpStatus.OK)
	private ResponseEntity<String> saveBook(@RequestBody Payment busroute)   
	{  
		PaymentRouteService.saveBusPayment(busroute) ;
		return new ResponseEntity<>("Bus Route updated success", 
				   HttpStatus.OK);  
	
	}  
	
	@PutMapping("/payment/{paymentid}")  
	@ResponseStatus(HttpStatus.OK)
	private ResponseEntity<String> update(@RequestBody Payment busroute,@PathVariable("paymentid") long paymentid) throws PaymentNotFoundException  
	{  
		PaymentRouteService.updateBusPayment(busroute, paymentid);  
		return new ResponseEntity<>("Bus Route updated success", 
				   HttpStatus.OK);   
	}  

}
