package com.hello.foreverpet.service;

import com.hello.foreverpet.domain.dto.response.CartProductResponse;
import com.hello.foreverpet.domain.dto.response.ProductResponse;
import com.hello.foreverpet.domain.entity.CartProduct;
import com.hello.foreverpet.domain.entity.Product;
import com.hello.foreverpet.domain.entity.UserInfo;
import com.hello.foreverpet.domain.exception.ProductNotFoundException;
import com.hello.foreverpet.domain.exception.UserNotFoundException;
import com.hello.foreverpet.handler.ErrorCode;
import com.hello.foreverpet.jwt.TokenProvider;
import com.hello.foreverpet.repository.CartProductJpaRepository;
import com.hello.foreverpet.repository.CustomProductRepository;
import com.hello.foreverpet.repository.ProductJpaRepository;
import com.hello.foreverpet.repository.UserInfoJpaRepository;
import jakarta.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartAndWishService {

    private final TokenProvider tokenProvider;
    private final UserInfoJpaRepository userInfoJpaRepository;
    private final ProductJpaRepository productJpaRepository;
    private final CartProductJpaRepository cartProductJpaRepository;
    private final CustomProductRepository customProductRepository;

    public List<CartProductResponse> getCart(HttpServletRequest httpServletRequest) {
        UserInfo userInfo = getUserInfo(httpServletRequest);

        return customProductRepository.getCartProductResponsesByUserId(userInfo);


    }

    public List<ProductResponse> getWish(HttpServletRequest httpServletRequest) {
        UserInfo userInfo = getUserInfo(httpServletRequest);

        List<ProductResponse> productResponses = new ArrayList<>();

        if (userInfo.getWish() != null) {
            productResponses = userInfo.getWish()
                    .getProducts()
                    .stream()
                    .map(ProductResponse::new)
                    .toList();
        }

        return productResponses;
    }

    @Transactional
    public boolean addProductInCart(HttpServletRequest httpServletRequest, Long id) {
        UserInfo userInfo = getUserInfo(httpServletRequest);
        Optional<Product> productById = productJpaRepository.findById(id);

        if (productById.isPresent()) {
            userInfo.addToCart(productById.get());
            return true;
        }

        return false;
    }

    @Transactional
    public boolean addProductInWish(HttpServletRequest httpServletRequest, Long id) {
        UserInfo userInfo = getUserInfo(httpServletRequest);

        Product productById = productJpaRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(ErrorCode.PRODUCT_FOUND_ERROR));

        userInfo.addProductInWish(productById);

        return true;

    }

    @Transactional
    public boolean deleteProductInCart(HttpServletRequest httpServletRequest, Long id) {

        UserInfo userInfo = getUserInfo(httpServletRequest);

        CartProduct cartProduct = cartProductJpaRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(ErrorCode.PRODUCT_FOUND_ERROR));

        userInfo.getCart().deleteProductInCart(cartProduct);
        cartProductJpaRepository.delete(cartProduct);

        return true;
    }

    @Transactional
    public boolean deleteProductInWish(HttpServletRequest httpServletRequest, Long id) {
        UserInfo userInfo = getUserInfo(httpServletRequest);

        Product product = productJpaRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(ErrorCode.PRODUCT_FOUND_ERROR));

        userInfo.getWish().deleteProductInWish(product);

        return true;

    }

    @Transactional
    public boolean increaseQuantity(HttpServletRequest httpServletRequest, Long id) {
        UserInfo userInfo = getUserInfo(httpServletRequest);

        CartProduct cartProduct = cartProductJpaRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(ErrorCode.PRODUCT_FOUND_ERROR));
        cartProduct.increaseQuantity();

        return true;

    }

    @Transactional
    public boolean decreaseQuantity(HttpServletRequest httpServletRequest, Long id) {
        UserInfo userInfo = getUserInfo(httpServletRequest);

        CartProduct cartProduct = cartProductJpaRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(ErrorCode.PRODUCT_FOUND_ERROR));
        cartProduct.decreaseQuantity();

        return true;
    }

    private UserInfo getUserInfo(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("Authorization");

        return userInfoJpaRepository.findById(
                        Long.valueOf(tokenProvider.getAuthentication(token).getName()))
                .orElseThrow(() -> new UserNotFoundException(ErrorCode.USER_ID_ERROR));
    }

    // getUserInfo 를 사용하니까 쓸데없는 쿼리문이 발생하는거같다.
    // userId 를 받아서 처리하면 쿼리문을 더 줄일수 있을거같다.

}
