package com.hello.foreverpet.service;

import org.springframework.stereotype.Service;

import com.hello.foreverpet.domain.dto.request.BillingInfoRequest;
import com.hello.foreverpet.domain.entity.BillingInfo;
import com.hello.foreverpet.repository.BillingJpaRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class BillingService {

    private final BillingJpaRepository billingJpaRepository;

     public BillingInfo createBilling(BillingInfoRequest billingInfoRequest) {

        BillingInfo newBilling = billingInfoRequest.toEntity();
        
        billingJpaRepository.save(newBilling);

        // log.info();
  
        return newBilling;
    }
}
