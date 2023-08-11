package com.hello.foreverpet;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.hello.foreverpet.domain.dto.Address;
import com.hello.foreverpet.domain.dto.request.NewProductRequest;
import com.hello.foreverpet.domain.dto.request.UpdateProductRequest;
import com.hello.foreverpet.domain.dto.request.UserSignupRequest;
import com.hello.foreverpet.domain.dto.response.ProductResponse;
import com.hello.foreverpet.jwt.JwtTokenProvider;
import com.hello.foreverpet.service.ProductService;
import com.hello.foreverpet.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;


@SpringBootTest
@TestPropertySource(locations = "classpath:/application.yml")
@Transactional
public class ProductServiceTest {

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Test
    @DisplayName("상품 등록")
    public void createProduct() {
        // given
        NewProductRequest newProductRequest = NewProductRequest.builder()
                .productName("테스트제품")
                .productDescription("제품 등록 테스트 중입니다.")
                .categories("SNACK")
                .productPrice(100000L)
                .productImage("")
                .brandName("test")
                .build();

        //when
        ProductResponse product = productService.createProduct(newProductRequest);

        //then
        Assertions.assertEquals(product.getProductName(),"테스트제품");
    }

    @Test
    @DisplayName("모든 상품 조회")
    public void getAllProducts() {
        //given
        //when
        List<ProductResponse> allProducts = productService.getAllProducts();

        //then
        Assertions.assertEquals(allProducts.size(),30);
    }

    @Test
    @DisplayName("상품 수정")
    public void updateProduct (){
        //given

        NewProductRequest newProductRequest = NewProductRequest.builder()
                .productName("제품")
                .productDescription("제품입니다.")
                .categories("BITA")
                .productPrice(9999L)
                .productImage("")
                .brandName("hello")
                .build();

        productService.createProduct(newProductRequest);

        UpdateProductRequest updateProductRequest = UpdateProductRequest.builder()
                .productName("테스트상품")
                .productDescription("테스트중입니다")
                .categories("SNACK")
                .productPrice(5000L)
                .productImage("aa")
                .brandName("test")
                .build();

        // when
        ProductResponse productResponse = productService.updateProduct(1L, updateProductRequest);

        // then
        Assertions.assertEquals(productResponse.getProductName(),"테스트상품");
    }

    @Test
    @DisplayName("상품 삭제")
    public void deleteProduct (){
        // given
        // when
        Long deleteProductId = productService.deleteProduct(1L);
        // then
        Assertions.assertThrows(IllegalArgumentException.class, () ->
            productService.findProductById(1L)
        );
        Assertions.assertEquals(deleteProductId,1L);
    }

    @Test
    @DisplayName("Id로 상품 검색")
    public void findProductById (){
        // given
        NewProductRequest newProductRequest = NewProductRequest.builder()
                .productName("테스트제품")
                .productDescription("제품 등록 테스트 중입니다.")
                .categories("SNACK")
                .productPrice(100000L)
                .productImage("")
                .brandName("test")
                .build();

        ProductResponse product = productService.createProduct(newProductRequest);

        // when
        ProductResponse productById = productService.findProductById(product.getId());

        // then
        Assertions.assertEquals(productById.getProductName(),"테스트제품");
    }

    @Test
    @DisplayName("장바구니 테스트")
    public void cartTest (){
        // given
        // 유저 회원가입
        UserSignupRequest userSignupRequest = UserSignupRequest.builder()
                .userNickName("testUser")
                .userEmail("test@gmail.com")
                .userPassword("123123")
                .userPhone("010-111-111")
                .userAddress(Address.builder()
                        .city("test")
                        .street("test")
                        .zipcode("test")
                        .build())
                .build();
        userService.userSignup(userSignupRequest);

        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        when(mockRequest.getHeader("Authorization")).thenReturn("Bearer mocktoken");
//        when(jwtTokenProvider.extractSubject("mocktoken")).thenReturn("1");

        // when
        boolean b = userService.addProductInCart(mockRequest, 1L);

        // then
    }

}
