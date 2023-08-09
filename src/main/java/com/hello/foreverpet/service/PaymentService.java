package com.hello.foreverpet.service;

import org.springframework.stereotype.Service;

import com.hello.foreverpet.domain.dto.request.PaymentRequest;
import com.hello.foreverpet.domain.entity.Payment;
import com.hello.foreverpet.repository.PaymentJpaRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentJpaRepository paymentJpaRepository;

    public Payment createPayment(PaymentRequest paymentInfoRequest) {

        Payment newPayment = paymentInfoRequest.toEntity();

        paymentJpaRepository.save(newPayment);

        // log.info();

        return newPayment;
    }
}
