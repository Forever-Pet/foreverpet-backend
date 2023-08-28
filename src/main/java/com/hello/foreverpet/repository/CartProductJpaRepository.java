package com.hello.foreverpet.repository;

import com.hello.foreverpet.domain.entity.Cart;
import com.hello.foreverpet.domain.entity.CartProduct;
import com.hello.foreverpet.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartProductJpaRepository extends JpaRepository<CartProduct,Long> {

    CartProduct findByCartAndProduct(Cart cart, Product product);
}
