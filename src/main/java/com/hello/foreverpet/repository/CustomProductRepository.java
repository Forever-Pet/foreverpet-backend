package com.hello.foreverpet.repository;

import com.hello.foreverpet.domain.dto.response.CartProductResponse;
import com.hello.foreverpet.domain.entity.Product;
import com.hello.foreverpet.domain.entity.UserInfo;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomProductRepository {
    List<Product> findProductBySearch(String search);

    List<Product> findProductOrderBySold();

    List<Product> findProductOrderByNew();

    List<Product> findProductByCategories(String categoryName);

    List<CartProductResponse> getCartProductResponsesByUserId(UserInfo userInfo);
}
