package com.hello.foreverpet;

import com.hello.foreverpet.domain.dto.request.NewProductRequest;
import com.hello.foreverpet.domain.dto.request.UpdateProductRequest;
import com.hello.foreverpet.domain.dto.response.ProductResponse;
import com.hello.foreverpet.repository.ProductJpaRepository;
import com.hello.foreverpet.service.ProductService;
import jakarta.transaction.Transactional;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@TestPropertySource(locations = "classpath:/application.yml")
@Transactional
@RunWith(SpringRunner.class)
public class ProductServiceTest {

    @Autowired
    private ProductJpaRepository productJpaRepository;

    @Autowired
    private ProductService productService;

    @Test
    @DisplayName("상품 등록")
    @Rollback(value = true)
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
    @Rollback(value = true)
    public void getAllProducts() {
        //given
        //when
        List<ProductResponse> allProducts = productService.getAllProducts();

        //then
        Assertions.assertEquals(allProducts.size(),0);
    }

    @Test
    @DisplayName("상품 수정")
    @Rollback(value = true)
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

        ProductResponse product = productService.createProduct(newProductRequest);

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
    @Rollback(value = true)
    public void deleteProduct (){
        // given
        // when
        Long deleteProductId = productService.deleteProduct(1L);
        // then
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            productService.findProductById(1L);
        });
        Assertions.assertEquals(deleteProductId,1L);
    }

    @Test
    @DisplayName("Id로 상품 검색")
    @Rollback(value = true)
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

}
