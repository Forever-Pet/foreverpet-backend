package com.hello.foreverpet.service;

import com.hello.foreverpet.domain.dto.Categories;
import com.hello.foreverpet.domain.dto.request.NewProductRequest;
import com.hello.foreverpet.domain.dto.request.UpdateProductRequest;
import com.hello.foreverpet.domain.dto.response.LoginUserProductResponse;
import com.hello.foreverpet.domain.dto.response.ProductResponse;
import com.hello.foreverpet.domain.entity.Product;
import com.hello.foreverpet.domain.exception.user.AlreadyExistProductException;
import com.hello.foreverpet.domain.exception.user.ProductNotFoundException;
import com.hello.foreverpet.handler.ErrorCode;
import com.hello.foreverpet.jwt.JwtTokenProvider;
import com.hello.foreverpet.repository.CustomProductRepository;
import com.hello.foreverpet.repository.ProductJpaRepository;
import com.hello.foreverpet.repository.UserInfoJpaRepository;
import jakarta.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductJpaRepository productJpaRepository;
    private final CustomProductRepository customProductRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserInfoJpaRepository userInfoJpaRepository;

    @Transactional
    public ProductResponse createProduct(NewProductRequest newProductRequest) {
        if (productJpaRepository.findByProductName(newProductRequest.getProductName()).isPresent()) {
            throw new AlreadyExistProductException(ErrorCode.ALREADY_EXIST_PRODUCT_EXCEPTION);
        }
        Product newProduct = newProductRequest.toEntity();
        productJpaRepository.save(newProduct);

        return new ProductResponse(newProduct);
    }

    public List<ProductResponse> getAllProducts() {
        return productJpaRepository.findAll().stream().map(ProductResponse::new).toList();
    }

    @Transactional
    public ProductResponse updateProduct(Long id, UpdateProductRequest updateProductRequest) {
        return productJpaRepository.findById(id)
                .map(product -> {
                    Product updateProduct = product.updateProductByUpdateRequest(updateProductRequest);
                    return new ProductResponse(updateProduct);
                })
                .orElseThrow(() -> new ProductNotFoundException(ErrorCode.PRODUCT_FOUND_ERROR));
    }


    public Long deleteProduct(Long id) {
        return productJpaRepository.findById(id)
                .map(product -> {
                    productJpaRepository.delete(product);
                    return id;
                })
                .orElseThrow(() -> new ProductNotFoundException(ErrorCode.PRODUCT_FOUND_ERROR));
    }

    public ProductResponse findProductById(Long id) {
        return productJpaRepository.findById(id).map(ProductResponse::new).orElseThrow(IllegalArgumentException::new);
    }

    public List<ProductResponse> searchProduct(String search) {
        return customProductRepository.findProductBySearch(search).stream()
                .map(ProductResponse::new).toList();
    }

    public List<ProductResponse> orderBySold() {
        return customProductRepository.findProductOrderBySold().stream()
                .map(ProductResponse::new).toList();
    }

    public List<ProductResponse> orderByNew() {
        return customProductRepository.findProductOrderByNew().stream()
                .map(ProductResponse::new).toList();
    }

    public List<ProductResponse> productByCategories(String categories) {
        Categories wantFindCategories = Categories.valueOf(categories);
        return customProductRepository.findProductByCategories(wantFindCategories).stream()
                .map(ProductResponse::new).toList();
    }

    public List<LoginUserProductResponse> loginUserGetAllProducts(HttpServletRequest httpServletRequest) {

        String token = httpServletRequest.getHeader("Authorization");
        boolean isLoggedIn = token != null;

        List<Product> cart = new ArrayList<>();
        List<Product> wish = new ArrayList<>();

        if (isLoggedIn) {
            String userId = jwtTokenProvider.extractSubject(token);
            userInfoJpaRepository.findById(Long.valueOf(userId)).ifPresent(userInfo -> {
                cart.addAll(userInfo.getCart().getProducts());
                wish.addAll(userInfo.getWish().getProducts());
            });
        }

        return productJpaRepository.findAll().stream()
                .map(product -> {
                    LoginUserProductResponse loginUserProductResponse = new LoginUserProductResponse(product);

                    if (isLoggedIn && cart.contains(product)) {
                        loginUserProductResponse.reverseInCart();
                    }

                    if (isLoggedIn && wish.contains(product)) {
                        loginUserProductResponse.reverseInWish();
                    }
                    return loginUserProductResponse;
                })
                .toList();
    }
}