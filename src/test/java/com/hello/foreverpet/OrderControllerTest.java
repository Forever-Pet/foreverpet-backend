package com.hello.foreverpet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.hello.foreverpet.controller.OrderController;
import com.hello.foreverpet.domain.dto.Address;
import com.hello.foreverpet.domain.dto.OrderRequestBody;
import com.hello.foreverpet.domain.dto.request.PaymentRequest;
import com.hello.foreverpet.domain.dto.request.OrderRequest;
import com.hello.foreverpet.domain.dto.request.OrderProductListRequest;
import com.hello.foreverpet.domain.entity.UserInfo;
import com.hello.foreverpet.repository.UserInfoJpaRepository;
import com.hello.foreverpet.service.PaymentService;
import com.hello.foreverpet.service.OrderProductService;
import com.hello.foreverpet.service.OrderService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderControllerTest {


    @Mock
    private OrderService orderService;

    @Mock
    private PaymentService paymentService;

    @Mock
    private OrderProductService orderProductService;

    @InjectMocks
    private OrderController orderController;

    private UserInfoJpaRepository userInfoJpaRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    

    @Test
    void createOrder_shouldReturnCreatedOrderNo(){
        // Payment test data 
        PaymentRequest paymentInfoRequest = new PaymentRequest("paymentName", "paymentGateway","paymentMethod");
        
        // OrderProduct test data
        List < OrderProductListRequest > ProductIdList = new ArrayList<>();
        OrderProductListRequest orderProductRequest = new OrderProductListRequest();
        orderProductRequest.setOrderProductAmount(4);
        orderProductRequest.setOrderProductId(1L);
        ProductIdList.add(orderProductRequest);
        orderProductRequest.setOrderProductAmount(2);
        orderProductRequest.setOrderProductId(2L);
        ProductIdList.add(orderProductRequest);
        orderProductRequest.setOrderProductAmount(4);
        orderProductRequest.setOrderProductId(1L);
        ProductIdList.add(orderProductRequest);

        // OrderInfo test data
        Address address = new Address();
        address.setCity("test");
        address.setStreet("test");
        address.setZipcode("test");


        OrderRequest orderInfoRequest = new OrderRequest();
        orderInfoRequest.setAddress(address);
        UserInfo userInfo = userInfoJpaRepository.findById(1L).get();

        
        String expectedId = "성공";

        OrderRequestBody createOrderRequest = new OrderRequestBody();
        createOrderRequest.setPaymentRequest(paymentInfoRequest);
        createOrderRequest.setOrderRequest(orderInfoRequest);
        createOrderRequest.setOrderProductListRequest(ProductIdList);

        

        // 실행
        ResponseEntity<String> response = orderController.createOrder(createOrderRequest);

    

        // when(orderService.createOrder(orderInfoRequest)).thenReturn(expectedId);
        
        // 단언
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedId, response.getBody());
        verify(orderService, times(1)).createOrder(createOrderRequest);

    } 
    
}
