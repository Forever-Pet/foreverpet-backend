package com.hello.foreverpet;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.hello.foreverpet.controller.ProductController;
import com.hello.foreverpet.domain.dto.request.NewProductRequest;
import com.hello.foreverpet.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createProduct_shouldReturnCreatedProductId() {
        // 준비
        NewProductRequest newProductRequest = new NewProductRequest(
                "test",
                "테스트중임",
                "CATEGORIES1",
                5000L,
                "https://e7.pngegg.com/pngimages/981/872/png-clipart-computer-terminal-computer-icons-linux-console-terminal-emulator-linux-logo-desktop-wallpaper.png",
                "포에버펫");
        Long expectedProductId = 11L;
        when(productService.createProduct(newProductRequest)).thenReturn(expectedProductId);

        // 실행
        ResponseEntity<Long> response = productController.createProduct(newProductRequest);

        // 단언
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedProductId, response.getBody());
        verify(productService, times(1)).createProduct(newProductRequest);
    }
}
