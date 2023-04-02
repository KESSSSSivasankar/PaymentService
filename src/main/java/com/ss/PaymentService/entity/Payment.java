package com.ss.PaymentService.entity;


import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long paymentId;
	
	@Column 
	private Long bookingId;
	@Column 
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	private Date dateOfPaymment;


}
