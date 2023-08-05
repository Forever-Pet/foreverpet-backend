package com.hello.foreverpet.service;

import com.hello.foreverpet.domain.dto.Categories;
import com.hello.foreverpet.domain.dto.request.NewProductRequest;
import com.hello.foreverpet.domain.dto.request.UpdateProductRequest;
import com.hello.foreverpet.domain.dto.response.ProductResponse;
import com.hello.foreverpet.domain.entity.Product;
import com.hello.foreverpet.jwt.JwtTokenProvider;
import com.hello.foreverpet.repository.CustomProductRepository;
import com.hello.foreverpet.repository.ProductJpaRepository;
import com.hello.foreverpet.repository.UserInfoJpaRepository;
import jakarta.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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
            throw new IllegalArgumentException("이미 존재하는 상품입니다.");
        }
        Product newProduct = newProductRequest.toEntity();
        productJpaRepository.save(newProduct);

        return new ProductResponse(newProduct);
    }


    // 현재는 ProductResponse 에 inCart , inWish 변수를 넣어서 처리 하였다.
    // 로그인 한 유저와 안한 유저 에게 같은 ProductResponse 로 값을 보내는 것이 맞을까 고민이 된다.
    // Controller 단에서 요청분리로 처리를 해야할지
    // 지금처럼 Service 에서 나눠서 처리해야할지 만약 Service 에서 나누어 처리하고 서로 다른 응답 객체로 값을 보내려고 한다면
    // 서비스 내에 두개의 메서드를 만들어서 controller 에서 토큰값을 확인하여 각각 다른값을 반환해줘야 할지
    public List<ProductResponse> getAllProducts(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("Authorization");

        boolean isLoggedIn = token != null;

        List<Product> cart = new ArrayList<>();
        List<Product> wish = new ArrayList<>();
        if (isLoggedIn) {
            String userId = jwtTokenProvider.extractSubject(token);
            userInfoJpaRepository.findById(Long.valueOf(userId)).ifPresent(userInfo -> {
                cart.addAll(userInfo.getCart());
                wish.addAll(userInfo.getWish());
            });
        }

        return productJpaRepository.findAll().stream()
                .map(product -> {
                    ProductResponse productResponse = new ProductResponse(product);

                    if (isLoggedIn && cart.contains(product)) {
                        productResponse.changeInCart();
                    }

                    if (isLoggedIn && wish.contains(product)) {
                        productResponse.changeInWish();
                    }
                    return productResponse;
                })
                .collect(Collectors.toList());

    }

    @Transactional
    public ProductResponse updateProduct(Long id, UpdateProductRequest updateProductRequest) {
        return productJpaRepository.findById(id)
                .map(product -> {
                    Product updateProduct = product.updateProductByUpdateRequest(updateProductRequest);
                    return new ProductResponse(updateProduct);
                })
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상품 ID 입니다."));
    }


    public Long deleteProduct(Long id) {
        return productJpaRepository.findById(id)
                .map(product -> {
                    productJpaRepository.delete(product);
                    return id;
                })
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상품 ID 입니다."));
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

    public List<ProductResponse> productByCategories(String categories) {
        Categories wantFindCategories = Categories.valueOf(categories);
        return customProductRepository.findProductByCategories(wantFindCategories).stream()
                .map(ProductResponse::new).collect(Collectors.toList());
    }
}
