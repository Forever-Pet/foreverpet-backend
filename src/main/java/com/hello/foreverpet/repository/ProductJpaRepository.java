package com.hello.foreverpet.repository;

import com.hello.foreverpet.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductJpaRepository extends JpaRepository<Product, Long> {
}
