package com.hello.foreverpet.controller;

import com.hello.foreverpet.domain.dto.response.CartProductResponse;
import com.hello.foreverpet.domain.dto.response.ProductResponse;
import com.hello.foreverpet.service.CartAndWishService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Cart & Wish API", description = "장바구니와 찜목록 API 입니다.")
@RestController
@RequiredArgsConstructor
@Slf4j
public class CartAndWishController {

    private final CartAndWishService cartAndWishService;

    @Operation(summary = "장바구니에 상품 추가", description = "회원의 장바구니에 상품을 추가.")
    @PostMapping("/user/cart/{id}")
    public ResponseEntity<Boolean> addProductInCart(HttpServletRequest httpServletRequest,
                                                    @PathVariable("id") Long id) {
        boolean successProductAddCart = cartAndWishService.addProductInCart(httpServletRequest, id);
        return ResponseEntity.ok(successProductAddCart);
    }

    @Operation(summary = "찜목록에 상품 추가", description = "회원의 찜목록에 상품을 추가.")
    @PostMapping("/user/wish/{id}")
    public ResponseEntity<Boolean> addProductInWish(HttpServletRequest httpServletRequest,
                                                    @PathVariable("id") Long id) {
        boolean successProductAddWish = cartAndWishService.addProductInWish(httpServletRequest, id);
        return ResponseEntity.ok(successProductAddWish);
    }

    @Operation(summary = "로그인 유저의 장바구니 조회", description = "로그인한 유저의 장바구니 상품 리스트")
    @PostMapping("/user/cart")
    public ResponseEntity<List<CartProductResponse>> getLoginUserCart(HttpServletRequest httpServletRequest) {
        return ResponseEntity.ok(cartAndWishService.getCart(httpServletRequest));
    }

    @Operation(summary = "로그인 유저의 찜목록 조회", description = "로그인한 유저의 찜목록 상품 리스트")
    @PostMapping("/user/wish")
    public ResponseEntity<List<ProductResponse>> getLoginUserWish(HttpServletRequest httpServletRequest) {
        return ResponseEntity.ok(cartAndWishService.getWish(httpServletRequest));
    }

    @Operation(summary = "장바구니에서 상품 제거", description = "회원의 장바구니에서 상품을 제거.")
    @DeleteMapping("/user/cart/{id}")
    public ResponseEntity<Boolean> deleteProductInCart(HttpServletRequest httpServletRequest,
                                                       @PathVariable("id") Long id) {

        boolean removeProductInCart = cartAndWishService.deleteProductInCart(httpServletRequest, id);
        return ResponseEntity.ok(removeProductInCart);
    }

    @Operation(summary = "찜목록에서 상품 제거", description = "회원의 찜목록에서 상품을 제거.")
    @DeleteMapping("/user/wish/{id}")
    public ResponseEntity<Boolean> deleteProductInWish(HttpServletRequest httpServletRequest,
                                                       @PathVariable("id") Long id) {
        boolean removeProductInWish = cartAndWishService.deleteProductInWish(httpServletRequest, id);
        return ResponseEntity.ok(removeProductInWish);
    }

    @Operation(summary = "장바구니 수량 증가", description = "회원 장바구니의 상품 수량 증가")
    @PostMapping("/user/cart/{id}/increase-quantity")
    public boolean increaseQuantity(HttpServletRequest httpServletRequest, @PathVariable("id") Long id) {
        return cartAndWishService.increaseQuantity(httpServletRequest, id);
    }

    @Operation(summary = "장바구니 수량 감소", description = "회원 장바구니의 상품 수량 감소")
    @PostMapping("/user/cart/{id}/decrease-quantity")
    public boolean decreaseQuantity(HttpServletRequest httpServletRequest, @PathVariable("id") Long id) {
        return cartAndWishService.decreaseQuantity(httpServletRequest, id);
    }

}
