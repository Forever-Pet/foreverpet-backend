package com.hello.foreverpet.service;

import org.springframework.stereotype.Service;

import com.hello.foreverpet.domain.dto.request.PaymentInfoRequest;
import com.hello.foreverpet.domain.entity.PaymentInfo;
import com.hello.foreverpet.repository.PaymentJpaRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentJpaRepository paymentJpaRepository;

     public PaymentInfo createPayment(PaymentInfoRequest paymentInfoRequest) {

        PaymentInfo newPayment = paymentInfoRequest.toEntity();
        
        paymentJpaRepository.save(newPayment);

        // log.info();
  
        return newPayment;
    }
}
