package com.hello.foreverpet;


import com.hello.foreverpet.domain.dto.Address;
import com.hello.foreverpet.domain.dto.request.NewProductRequest;
import com.hello.foreverpet.domain.dto.request.UserSignupRequest;
import com.hello.foreverpet.domain.entity.Product;
import com.hello.foreverpet.domain.entity.UserInfo;
import com.hello.foreverpet.repository.ProductJpaRepository;
import com.hello.foreverpet.repository.UserInfoJpaRepository;
import com.hello.foreverpet.service.ProductService;
import com.hello.foreverpet.service.UserService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@TestPropertySource(locations = "classpath:/application.yml")
@SpringBootTest
@Transactional
public class CartTest {

    @Autowired
    UserService userService;

    @Autowired
    UserInfoJpaRepository userInfoJpaRepository;

    @Autowired
    ProductService productService;

    @Autowired
    ProductJpaRepository productJpaRepository;

    @Test
    @DisplayName("장바구니 추가")
    public void cartTest (){
        // given
        // addUser
        UserSignupRequest userSignupRequest = UserSignupRequest.builder()
                .userNickName("cartTester")
                .userEmail("cartTester@aa.com")
                .userPassword("12341234")
                .userPhone("11-11-111")
                .userAddress(Address
                        .builder()
                        .city("tcity")
                        .street("tstreet")
                        .zipcode("t1234")
                        .build())
                .build();

        userService.userSignup(userSignupRequest);

        // addProduct
        NewProductRequest newProductRequest = NewProductRequest.builder()
                .productName("테스트제품")
                .productDescription("제품 등록 테스트 중입니다.")
                .categories("SNACK")
                .productPrice(100000L)
                .productImage("")
                .brandName("test")
                .build();

        productService.createProduct(newProductRequest);

        Product productById = productJpaRepository.findById(1L).get();

        UserInfo cartTester = userInfoJpaRepository.findByUserNickname("cartTester").get();

        // when

        cartTester.addProductInCart(productById);

        // then
        Assertions.assertEquals(cartTester.getCart().size(),1);
        Assertions.assertEquals(cartTester.getCart().get(0).getProductName(),productById.getProductName());
    }

    @Test
    @DisplayName("찜목록 추가")
    public void wishTest (){
        // given
        // addUser
        UserSignupRequest userSignupRequest = UserSignupRequest.builder()
                .userNickName("cartTester")
                .userEmail("cartTester@aa.com")
                .userPassword("12341234")
                .userPhone("11-11-111")
                .userAddress(Address
                        .builder()
                        .city("tcity")
                        .street("tstreet")
                        .zipcode("t1234")
                        .build())
                .build();

        userService.userSignup(userSignupRequest);

        // addProduct
        NewProductRequest newProductRequest = NewProductRequest.builder()
                .productName("테스트제품")
                .productDescription("제품 등록 테스트 중입니다.")
                .categories("SNACK")
                .productPrice(100000L)
                .productImage("")
                .brandName("test")
                .build();

        productService.createProduct(newProductRequest);

        Product productById = productJpaRepository.findById(1L).get();

        UserInfo cartTester = userInfoJpaRepository.findByUserNickname("cartTester").get();

        // when

        cartTester.addProductInWish(productById);

        // then
        Assertions.assertEquals(cartTester.getWish().size(),1);
        Assertions.assertEquals(cartTester.getWish().get(0).getProductName(),productById.getProductName());
    }
}
