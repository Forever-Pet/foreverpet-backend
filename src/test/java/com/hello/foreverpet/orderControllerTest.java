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
import com.hello.foreverpet.domain.dto.CreateOrderRequest;
import com.hello.foreverpet.domain.dto.request.PaymentInfoRequest;
import com.hello.foreverpet.domain.dto.request.OrderInfoRequest;
import com.hello.foreverpet.domain.dto.request.OrderProductRequest;
import com.hello.foreverpet.domain.entity.PaymentInfo;
import com.hello.foreverpet.domain.entity.OrderProduct;
import com.hello.foreverpet.domain.entity.Product;
import com.hello.foreverpet.service.PaymentService;
import com.hello.foreverpet.service.OrderProductService;
import com.hello.foreverpet.service.OrderService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class orderControllerTest {


    @Mock
    private OrderService orderService;

    @Mock
    private PaymentService paymentService;

    @Mock
    private OrderProductService orderProductService;

    @InjectMocks
    private OrderController orderController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    

    @Test
    void createOrder_shouldReturnCreatedOrderNo(){
        // Payment test data 
        PaymentInfoRequest paymentInfoRequest = new PaymentInfoRequest("paymentName", "paymentGateway","paymentMethod");
        
        // OrderProduct test data
        List < OrderProductRequest > ProductIdList = new ArrayList<>();
        OrderProductRequest orderProductRequest = new OrderProductRequest();
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


        OrderInfoRequest orderInfoRequest = new OrderInfoRequest();
        orderInfoRequest.setAddress(address);
        orderInfoRequest.setUserNo(1L);

        
        Long expectedId = 0L;

        CreateOrderRequest createOrderRequest = new CreateOrderRequest();
        createOrderRequest.setPaymentInfoRequest(paymentInfoRequest);
        createOrderRequest.setOrderInfoRequest(orderInfoRequest);
        createOrderRequest.setOrderProductRequest(ProductIdList);

        

        // 실행
        ResponseEntity<Long> response = orderController.createOrder(createOrderRequest);

        

        orderInfoRequest.setPaymentId(paymentService.createPayment(paymentInfoRequest));
        orderInfoRequest.setOrderProductList(orderProductService.createOrderProductList(ProductIdList));

        when(orderService.createOrder(orderInfoRequest)).thenReturn(expectedId);
        
        // 단언
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedId, response.getBody());
        verify(orderService, times(1)).createOrder(orderInfoRequest);

    }

}
