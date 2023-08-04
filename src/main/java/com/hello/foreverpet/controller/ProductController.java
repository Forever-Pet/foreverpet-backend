package com.hello.foreverpet.controller;

import com.hello.foreverpet.domain.dto.request.NewProductRequest;
import com.hello.foreverpet.domain.dto.request.UpdateProductRequest;
import com.hello.foreverpet.domain.dto.response.ProductResponse;
import com.hello.foreverpet.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Product API",description = "상품관리 API 입니다")
@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // 상품 등록
    // 상품 등록은 관리자만 가능해야 한다.
    @Operation(summary = "상품 등록",description = "상품을 등록합니다.")
    @PostMapping("/products")
    public ResponseEntity<ProductResponse> createProduct(@RequestBody @Valid NewProductRequest newProductRequest) {
        ProductResponse product = productService.createProduct(newProductRequest);

        return ResponseEntity.ok(product);
    }

    // 모든 상품 조회
    @Operation(summary = "모든 상품 조회",description = "id 순으로 모든 상품을 조회합니다.")
    @GetMapping("/products")
    public ResponseEntity<List<ProductResponse>> allProducts() {

        return ResponseEntity.ok(productService.getAllProducts());
    }

    @Operation(summary = "id로 상품 조회",description = "id 값으로 특정 상품을 찾습니다.")
    @GetMapping("/products/{id}")
    public ResponseEntity<ProductResponse> findProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.findProductById(id));
    }

    // 상품 수정의 경우 관리자만 가능해야 한다.
    @Operation(summary = "상품 수정",description = "id 로 원하는 상품을 선택하고 수정합니다.")
    @PutMapping("/products/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long id, @RequestBody @Valid UpdateProductRequest updateProductRequest) {
        ProductResponse productResponse = productService.updateProduct(id, updateProductRequest);
        return ResponseEntity.ok(productResponse);
    }
    // 상품 삭제의 경우 관리자만 가능해야한다.
    @Operation(summary = "상품 삭제",description = "id 로 상품을 삭제합니다.")
    @DeleteMapping("/products/{id}")
    public ResponseEntity<Long> deleteProduct(@PathVariable Long id) {
        Long deletedId = productService.deleteProduct(id);
        return ResponseEntity.ok(deletedId);
    }

    @Operation(summary = "상품 검색", description = "검색어를 포함하는 결과 반환")
    @GetMapping("/products/search")
    public ResponseEntity<List<ProductResponse>> searchProduct(@RequestParam("search") String search) {
        return ResponseEntity.ok(productService.searchProduct(search));
    }

    @Operation(summary = "베스트 상품", description = "많이 팔린 상품 순으로 정렬합니다.")
    @GetMapping("/products/best")
    public ResponseEntity<List<ProductResponse>> bestProducts() {
        return ResponseEntity.ok(productService.orderBySold());
    }

    @Operation(summary = "새로운 상품", description = "신규 등록 상품 순으로 정렬합니다.")
    @GetMapping("/products/new")
    public ResponseEntity<List<ProductResponse>> newProducts() {
        return ResponseEntity.ok(productService.orderByNew());
    }

    @Operation(summary = "카테고리 검색", description = "상품을 카테고리별로 반환합니다.")
    @GetMapping("/products/categories")
    public ResponseEntity<List<ProductResponse>> searchProductByCategories(@RequestParam("categories") String categories) {
        return ResponseEntity.ok(productService.productByCategories(categories));
    }

}
