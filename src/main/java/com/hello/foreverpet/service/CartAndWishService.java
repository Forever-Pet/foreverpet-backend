package com.hello.foreverpet.service;

import com.hello.foreverpet.domain.dto.response.ProductResponse;
import com.hello.foreverpet.domain.entity.Product;
import com.hello.foreverpet.domain.entity.UserInfo;
import com.hello.foreverpet.domain.exception.user.ProductNotFoundException;
import com.hello.foreverpet.handler.ErrorCode;
import com.hello.foreverpet.jwt.TokenProvider;
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

    public List<ProductResponse> getCart(HttpServletRequest httpServletRequest) {
        Optional<UserInfo> userInfo = getUserInfo(httpServletRequest);

        List<ProductResponse> productResponses = new ArrayList<>();

        if (userInfo.isPresent() && userInfo.get().getCart() != null) {
            productResponses = userInfo.get().getCart()
                    .getProducts()
                    .stream()
                    .map(ProductResponse::new)
                    .toList();
        }

        return productResponses;
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

            userInfo.get().addProductInCart(productById);

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

            log.info(product.getProductName());

            userInfo.get().getCart().deleteProductInCart(product);

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

    private Optional<UserInfo> getUserInfo(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("Authorization");

        return userInfoJpaRepository.findById(
                Long.valueOf(tokenProvider.getAuthentication(token).getName()));
    }
}
