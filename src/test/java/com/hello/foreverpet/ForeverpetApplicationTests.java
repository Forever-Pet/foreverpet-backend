package com.hello.foreverpet;

import com.hello.foreverpet.domain.entity.Product;
import com.hello.foreverpet.repository.ProductJpaRepository;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ForeverpetApplicationTests {

    @Autowired
    ProductJpaRepository productJpaRepository;

    @Test
    void test() {
        List<Product> all = productJpaRepository.findAll();

        for (Product product : all) {
            System.out.println("product = " + product.getProductName());
        }
    }
}
