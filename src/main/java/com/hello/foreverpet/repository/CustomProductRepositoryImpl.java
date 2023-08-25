package com.hello.foreverpet.repository;

import static com.hello.foreverpet.domain.entity.QProduct.product;

import com.hello.foreverpet.domain.dto.Categories;
import com.hello.foreverpet.domain.dto.response.CartProductResponse;
import com.hello.foreverpet.domain.dto.response.QCartProductResponse;
import com.hello.foreverpet.domain.entity.Product;
import com.hello.foreverpet.domain.entity.QCart;
import com.hello.foreverpet.domain.entity.QCartProduct;
import com.hello.foreverpet.domain.entity.QProduct;
import com.hello.foreverpet.domain.entity.QUserInfo;
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
                .orderBy(product.numberOfSold.asc())
                .fetch();
    }

    @Override
    public List<Product> findProductOrderByNew() {
        return jpaQueryFactory.selectFrom(product)
                .orderBy(product.createDate.desc())
                .fetch();
    }

    @Override
    public List<Product> findProductByCategories(Categories categories) {
        return jpaQueryFactory.selectFrom(product)
                .where(
                        eqCategories(categories)
                )
                .fetch();
    }


    @Override
    public List<CartProductResponse> getCartProductResponsesByUserId(UserInfo userInfo) {
        QCartProductResponse cartProductResponse = new QCartProductResponse(
                QCartProduct.cartProduct.id,
                QProduct.product.productName,
                QProduct.product.productDescription,
                QProduct.product.categories,
                QProduct.product.productPrice,
                QProduct.product.numberOfSold,
                QProduct.product.productImage,
                QProduct.product.brandName,
                QCartProduct.cartProduct.quantity,
                QProduct.product.createDate,
                QProduct.product.modifiedDate
        );

        return jpaQueryFactory
                .select(cartProductResponse)
                .from(QUserInfo.userInfo)
                .leftJoin(QUserInfo.userInfo.cart, QCart.cart)
                .leftJoin(QCart.cart.cartProducts, QCartProduct.cartProduct)
                .leftJoin(QCartProduct.cartProduct.product, QProduct.product)
                .where(QUserInfo.userInfo.eq(userInfo))
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

    private BooleanExpression eqCategories(Categories categories) {
        if (categories != null) {
            return product.categories.eq(categories);
        }
        return null;
    }
}
