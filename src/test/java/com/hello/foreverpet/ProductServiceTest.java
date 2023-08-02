//package com.hello.foreverpet;
//
//import com.hello.foreverpet.domain.dto.request.NewProductRequest;
//import com.hello.foreverpet.domain.dto.request.UpdateProductRequest;
//import com.hello.foreverpet.domain.dto.response.ProductResponse;
//import com.hello.foreverpet.repository.ProductJpaRepository;
//import com.hello.foreverpet.service.ProductService;
//import java.util.List;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.TestPropertySource;
//
//@SpringBootTest
//@TestPropertySource(locations = "classpath:application-test.yml")
//public class ProductServiceTest {
//
//    @Autowired
//    private ProductJpaRepository productJpaRepository;
//
//    @Autowired
//    private ProductService productService;
//
//    @BeforeEach
//    public void setUp() {
//        productJpaRepository.deleteAll();
//    }
//
//    @Test
//    @DisplayName("상품 등록")
//    public void createProduct() {
//        // given
//        NewProductRequest newProductRequest = NewProductRequest.builder()
//                .productName("테스트제품")
//                .productDescription("제품 등록 테스트 중입니다.")
//                .categories("SNACK")
//                .productPrice(100000L)
//                .productImage("")
//                .brandName("test")
//                .build();
//
//        //when
//        Long product = productService.createProduct(newProductRequest);
//
//        System.out.println("product = " + product);
//
//        //then
//        Assertions.assertEquals(product,31L);
//    }
//
//    @Test
//    @DisplayName("모든 상품 조회")
//    public void getAllProducts() {
//        //given
//        NewProductRequest newProductRequest = NewProductRequest.builder()
//                .productName("테스트제품")
//                .productDescription("제품 등록 테스트 중입니다.")
//                .categories("SNACK")
//                .productPrice(100000L)
//                .productImage("")
//                .brandName("test")
//                .build();
//
//        //when
//        productService.createProduct(newProductRequest);
//        List<ProductResponse> allProducts = productService.getAllProducts();
//
//        //then
//        Assertions.assertEquals(allProducts.size(),1);
//    }
//
//    @Test
//    @DisplayName("상품 수정")
//    public void updateProduct (){
//        //given
//        UpdateProductRequest updateProductRequest = UpdateProductRequest.builder()
//                .productName("테스트상품")
//                .productDescription("테스트중입니다")
//                .categories("SNACK")
//                .productPrice(5000L)
//                .productImage("aa")
//                .brandName("test")
//                .build();
//
//        // when
//        productService.updateProduct(1L,updateProductRequest);
//
//        ProductResponse productById = productService.findProductById(1L);
//
//        System.out.println(productById.toString());
//
//        // then
//        Assertions.assertEquals(productService.findProductById(1L).getProductName(),"테스트상품");
//    }
//
//    @Test
//    @DisplayName("상품 삭제")
//    public void deleteProduct (){
//        // given
//        // when
//        Long deleteProductId = productService.deleteProduct(1L);
//        // then
//        Assertions.assertThrows(IllegalArgumentException.class, () -> {
//            productService.findProductById(1L);
//        });
//        Assertions.assertEquals(deleteProductId,1L);
//    }
//
//    @Test
//    @DisplayName("Id로 상품 검색")
//    public void findProductById (){
//        // given
//        NewProductRequest newProductRequest = NewProductRequest.builder()
//                .productName("테스트제품")
//                .productDescription("제품 등록 테스트 중입니다.")
//                .categories("SNACK")
//                .productPrice(100000L)
//                .productImage("")
//                .brandName("test")
//                .build();
//
//        productService.createProduct(newProductRequest);
//
//        // when
//        ProductResponse productById = productService.findProductById(1L);
//
//        // then
//        Assertions.assertEquals(productById.getProductName(),"테스트제품");
//    }
//
//}
