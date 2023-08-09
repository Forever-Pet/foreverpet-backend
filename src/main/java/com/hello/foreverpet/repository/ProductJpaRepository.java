package com.hello.foreverpet.repository;

import com.hello.foreverpet.domain.entity.Product;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductJpaRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByProductName(String productName);
}
