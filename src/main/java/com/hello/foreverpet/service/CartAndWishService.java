package com.hello.foreverpet.service;

import com.hello.foreverpet.domain.dto.response.CartProductResponse;
import com.hello.foreverpet.domain.dto.response.ProductResponse;
import com.hello.foreverpet.domain.entity.CartProduct;
import com.hello.foreverpet.domain.entity.Product;
import com.hello.foreverpet.domain.entity.UserInfo;
import com.hello.foreverpet.domain.exception.user.ProductNotFoundException;
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
        Optional<UserInfo> userInfo = getUserInfo(httpServletRequest);

//        List<CartProductResponse> cartProductResponses = new ArrayList<>();

//        if (userInfo.isPresent() && userInfo.get().getCart() != null) {
//            cartProductResponses = userInfo.get().getCart().getCartProducts()
//                    .stream()
//                    .map(CartProductResponse::new)
//                    .toList();
//        }
        return customProductRepository.getCartProductResponsesByUserId(userInfo.get());


    }

    public List<ProductResponse> getWish(HttpServletRequest httpServletRequest) {
        Optional<UserInfo> userInfo = getUserInfo(httpServletRequest);

        List<ProductResponse> productResponses = new ArrayList<>();

        if (userInfo.isPresent() && userInfo.get().getWish() != null) {
            productResponses = userInfo.get().getWish()
                    .getProducts()
                    .stream()
                    .map(ProductResponse::new)
                    .toList();
        }

        return productResponses;
    }

    @Transactional
    public boolean addProductInCart(HttpServletRequest httpServletRequest, Long id) {
        Optional<UserInfo> userInfo = getUserInfo(httpServletRequest);

        if (userInfo.isPresent()) {

            Product productById = productJpaRepository.findById(id)
                    .orElseThrow(() -> new ProductNotFoundException(ErrorCode.PRODUCT_FOUND_ERROR));

            CartProduct cartProduct = new CartProduct(productById);

            productById.setCartProduct(cartProduct);

            userInfo.get().addProductInCart(cartProduct);

            return true;
        }

        return false;
    }

    @Transactional
    public boolean addProductInWish(HttpServletRequest httpServletRequest, Long id) {
        Optional<UserInfo> userInfo = getUserInfo(httpServletRequest);

        if (userInfo.isPresent()) {

            Product productById = productJpaRepository.findById(id)
                    .orElseThrow(() -> new ProductNotFoundException(ErrorCode.PRODUCT_FOUND_ERROR));

            userInfo.get().addProductInWish(productById);

            return true;
        }

        return false;
    }

    @Transactional
    public boolean deleteProductInCart(HttpServletRequest httpServletRequest, Long id) {

        Optional<UserInfo> userInfo = getUserInfo(httpServletRequest);

        if (userInfo.isPresent()) {
            Product product = productJpaRepository.findById(id)
                    .orElseThrow(() -> new ProductNotFoundException(ErrorCode.PRODUCT_FOUND_ERROR));

            CartProduct cartProduct = product.getCartProduct();

            userInfo.get().getCart().deleteProductInCart(cartProduct);

            return true;
        }

        return false;
    }

    @Transactional
    public boolean deleteProductInWish(HttpServletRequest httpServletRequest, Long id) {
        Optional<UserInfo> userInfo = getUserInfo(httpServletRequest);

        if (userInfo.isPresent()) {
            Product product = productJpaRepository.findById(id)
                    .orElseThrow(() -> new ProductNotFoundException(ErrorCode.PRODUCT_FOUND_ERROR));

            userInfo.get().getWish().deleteProductInWish(product);

            return true;
        }

        return false;
    }

    @Transactional
    public boolean increaseQuantity(HttpServletRequest httpServletRequest, Long id) {
        Optional<UserInfo> userInfo = getUserInfo(httpServletRequest);

        if (userInfo.isPresent()) {
            CartProduct cartProduct = cartProductJpaRepository.findById(id)
                    .orElseThrow(() -> new ProductNotFoundException(ErrorCode.PRODUCT_FOUND_ERROR));
            cartProduct.increaseQuantity();

            return true;
        }

        return false;
    }

    @Transactional
    public boolean decreaseQuantity(HttpServletRequest httpServletRequest, Long id) {
        Optional<UserInfo> userInfo = getUserInfo(httpServletRequest);

        if (userInfo.isPresent()) {
            CartProduct cartProduct = cartProductJpaRepository.findById(id)
                    .orElseThrow(() -> new ProductNotFoundException(ErrorCode.PRODUCT_FOUND_ERROR));
            cartProduct.decreaseQuantity();

            return true;
        }

        return false;
    }

    private Optional<UserInfo> getUserInfo(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("Authorization");

        return userInfoJpaRepository.findById(
                Long.valueOf(tokenProvider.getAuthentication(token).getName()));
    }

    // getUserInfo 를 사용하니까 쓸데없는 쿼리문이 발생하는거같다.
    // userId 를 받아서 처리하면 쿼리문을 더 줄일수 있을거같다.

}
