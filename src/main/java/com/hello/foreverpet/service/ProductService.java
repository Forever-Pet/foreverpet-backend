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

    public Long createProduct(NewProductRequest newProductRequest) {
        Product newProduct = newProductRequest.toEntity();
        productJpaRepository.save(newProduct);

        return newProduct.getProductId();
    }

    public List<ProductResponse> getAllProducts() {
        return productJpaRepository.findAll().stream().map(ProductResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateProduct(Long id, UpdateProductRequest updateProductRequest) {
        productJpaRepository.findById(id).ifPresent(product -> {
            Product updatedProduct = product.updateProductByUpdateRequest(updateProductRequest);
        });
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
