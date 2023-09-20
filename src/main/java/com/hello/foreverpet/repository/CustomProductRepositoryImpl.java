package com.hello.foreverpet.repository;

import static com.hello.foreverpet.domain.entity.QCartProduct.cartProduct;
import static com.hello.foreverpet.domain.entity.QProduct.product;

import com.hello.foreverpet.domain.dto.response.CartProductResponse;
import com.hello.foreverpet.domain.dto.response.QCartProductResponse;
import com.hello.foreverpet.domain.entity.Product;
import com.hello.foreverpet.domain.entity.UserInfo;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CustomProductRepositoryImpl implements CustomProductRepository {

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
                .orderBy(product.createDate.desc())
                .fetch();
    }

    @Override
    public List<Product> findProductByCategories(String categoryName) {
        return jpaQueryFactory.selectFrom(product)
                .where(
                        eqCategories(categoryName)
                )
                .fetch();
    }


    @Override
    public List<CartProductResponse> getCartProductResponsesByUserId(UserInfo userInfo) {
        return jpaQueryFactory
                .select(new QCartProductResponse(
                        cartProduct.id,
                        cartProduct.product.productId,
                        cartProduct.product.productName,
                        cartProduct.product.productDescription,
                        cartProduct.product.category.name,
                        cartProduct.product.productPrice,
                        cartProduct.product.numberOfSold,
                        cartProduct.product.productImage,
                        cartProduct.product.brandName,
                        cartProduct.quantity,
                        cartProduct.product.createDate,
                        cartProduct.product.modifiedDate
                ))
                .from(cartProduct)
                .leftJoin(cartProduct.product, product)
                .where(cartProduct.cart.userInfo.eq(userInfo))
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

    private BooleanExpression eqCategories(String categoryName) {
        if (categoryName != null) {
            return product.category.name.eq(categoryName);
        }
        return null;
    }
}
