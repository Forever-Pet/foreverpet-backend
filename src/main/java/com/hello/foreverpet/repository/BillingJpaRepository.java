package com.hello.foreverpet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hello.foreverpet.domain.entity.BillingInfo;

@Repository
public interface BillingJpaRepository extends JpaRepository<BillingInfo,Long> {
}
    
