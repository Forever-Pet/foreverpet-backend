package com.hello.foreverpet.repository;

import static com.hello.foreverpet.domain.entity.QProduct.product;

import com.hello.foreverpet.domain.entity.Product;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CustomProductRepositoryImpl implements CustomProductRepository{

    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public List<Product> findProductBySearch(String search) {
        return jpaQueryFactory.selectFrom(product)
                .where(
                        containsProductName(search),
                        containsDescription(search)
                )
                .fetch();
    }

    @Override
    public List<Product> findProductOrderBySold() {
        return jpaQueryFactory.selectFrom(product)
                .orderBy(product.numberOfSold.desc())
                .fetch();
    }

    @Override
    public List<Product> findProductOrderByNew() {
        return jpaQueryFactory.selectFrom(product)
                .orderBy(product.createDate.asc())
                .fetch();
    }

    private BooleanExpression containsProductName(String productName) {
        if (productName != null && !productName.isEmpty()) {
            return product.productName.contains(productName);
        }
        return null;
    }

    private BooleanExpression containsDescription(String productDescription) {
        if (productDescription != null && !productDescription.isEmpty()) {
            return product.productDescription.contains(productDescription);
        }
        return null;
    }

    private BooleanExpression eqPrice(String price) {
        if (price != null && !price.isEmpty()) {
            return product.productPrice.eq(Long.parseLong(price));
        }
        return null;
    }
}
