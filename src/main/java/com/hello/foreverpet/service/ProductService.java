package com.hello.foreverpet.service;

import com.hello.foreverpet.domain.dto.request.NewProductRequest;
import com.hello.foreverpet.domain.dto.request.UpdateProductRequest;
import com.hello.foreverpet.domain.dto.response.ProductResponse;
import com.hello.foreverpet.domain.entity.Product;
import com.hello.foreverpet.repository.CustomProductRepository;
import com.hello.foreverpet.repository.ProductJpaRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductJpaRepository productJpaRepository;
    private final CustomProductRepository customProductRepository;

    @Transactional
    public ProductResponse createProduct(NewProductRequest newProductRequest) {
        if (productJpaRepository.findByProductName(newProductRequest.getProductName()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 상품입니다.");
        }
        Product newProduct = newProductRequest.toEntity();
        productJpaRepository.save(newProduct);

        return new ProductResponse(newProduct);
    }

    public List<ProductResponse> getAllProducts() {
        return productJpaRepository.findAll().stream().map(ProductResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public ProductResponse updateProduct(Long id, UpdateProductRequest updateProductRequest) {

        Optional<Product> productById = productJpaRepository.findById(id);

        if (productById.isPresent()) {
            Product updateProduct = productById.get().updateProductByUpdateRequest(updateProductRequest);
            return new ProductResponse(updateProduct);
        } else {
            throw new IllegalArgumentException("존재하지 않는 상품 ID 입니다.");
        }

    }

    public Long deleteProduct(Long id) {
        Optional<Product> wantDeleteProduct = productJpaRepository.findById(id);
        wantDeleteProduct.ifPresent(productJpaRepository::delete);
        return id;
    }

    public ProductResponse findProductById(Long id) {
        return productJpaRepository.findById(id).map(ProductResponse::new).orElseThrow(IllegalArgumentException::new);
    }

    public List<ProductResponse> searchProduct(String search) {
        return customProductRepository.findProductBySearch(search).stream()
                .map(ProductResponse::new).collect(Collectors.toList());
    }

    public List<ProductResponse> orderBySold() {
        return customProductRepository.findProductOrderBySold().stream()
                .map(ProductResponse::new).collect(Collectors.toList());
    }

    public List<ProductResponse> orderByNew() {
        return customProductRepository.findProductOrderByNew().stream()
                .map(ProductResponse::new).collect(Collectors.toList());
    }
}
