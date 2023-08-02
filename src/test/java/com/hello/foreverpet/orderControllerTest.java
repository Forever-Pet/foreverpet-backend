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
import com.hello.foreverpet.domain.dto.request.BillingInfoRequest;
import com.hello.foreverpet.domain.dto.request.OrderInfoRequest;

import com.hello.foreverpet.domain.entity.BillingInfo;
import com.hello.foreverpet.domain.entity.Product;
import com.hello.foreverpet.service.BillingService;
import com.hello.foreverpet.service.OrderService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class orderControllerTest {


    @Mock
    private OrderService orderService;

    @Mock
    private BillingService billingService;

    @InjectMocks
    private OrderController orderController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    

    @Test
    void createOrder_shouldReturnCreatedOrderNo(){
        Address address = new Address();
        List<Product> list = new ArrayList();
        BillingInfoRequest billingInfoRequest = new BillingInfoRequest("abc", "abc", "abc"); 
        OrderInfoRequest orderInfoRequest = new OrderInfoRequest(address, 1L, list
        );
        List<Long>productNoList = new ArrayList();
        Long expectedId = 1L;
        BillingInfo billingInfo = billingService.createBilling(billingInfoRequest);
        
        CreateOrderRequest orderRequest = new CreateOrderRequest();
        orderRequest.setBillingInfoRequest(billingInfoRequest);
        orderRequest.setOrderInfoRequest(orderInfoRequest);
        orderRequest.setProductNoList(productNoList);

        
        // when(orderService.createOrder(orderInfoRequest,billingInfo,productNoList)).thenReturn(expectedId);

        // 실행
        ResponseEntity<Long> response = orderController.createOrder(orderRequest);

        log.info(null);  
        
        // 단언
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedId, response.getBody());
        // verify(orderService, times(1)).createOrder(orderInfoRequest,billingInfo,productNoList);

    }

}
