package com.hello.foreverpet.service;

import com.hello.foreverpet.domain.dto.Categories;
import com.hello.foreverpet.domain.dto.request.NewProductRequest;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InitDataService {

    private final ProductService productService;

    @PostConstruct
    public void init() {
        for (int i = 0; i < 10; i++) {
            NewProductRequest newProductRequest = new NewProductRequest(
                    "testProduct"+i,
                    "testProduct"+ i +"에 대한 설명입니다",
                    Categories.CATEGORY1.name(),
                    9400L,
                    "https://velog.velcdn.com/images/winnieworld/post/8f90c882-286d-429d-aba7-0e30bd34c64c/image.png",
                    "포에버펫");
            productService.createProduct(newProductRequest);
        }
    }
}
