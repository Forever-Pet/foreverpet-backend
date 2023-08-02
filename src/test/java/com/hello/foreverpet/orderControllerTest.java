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
import com.hello.foreverpet.domain.entity.OrderProductList;
import com.hello.foreverpet.domain.entity.Product;
import com.hello.foreverpet.service.BillingService;
import com.hello.foreverpet.service.OrderProductListService;
import com.hello.foreverpet.service.OrderService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class orderControllerTest {


    @Mock
    private OrderService orderService;

    @Mock
    private BillingService billingService;

    @Mock
    private OrderProductListService orderProductListService;

    @InjectMocks
    private OrderController orderController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    

    @Test
    void createOrder_shouldReturnCreatedOrderNo(){
        BillingInfoRequest billingInfoRequest = new BillingInfoRequest("billingNm", "paymentGateway","paymentMethod");
        // BillingInfo billing = billingService.createBilling(billingInfoRequest);
        List < Long > ProductIdList = new ArrayList<>();
        ProductIdList.add(1L);
        ProductIdList.add(2L);
        ProductIdList.add(1L);
        // List<OrderProductList> products = orderProductListService.createOrderProductList(ProductIdList);
        Address address = new Address();
        OrderInfoRequest orderInfoRequest = new OrderInfoRequest();
        orderInfoRequest.setAddress(address);
        orderInfoRequest.setUserNo(1L);
        
        Long expectedId = 0L;

        CreateOrderRequest createOrderRequest = new CreateOrderRequest();
        createOrderRequest.setBillingInfoRequest(billingInfoRequest);
        createOrderRequest.setOrderInfoRequest(orderInfoRequest);
        createOrderRequest.setProductNoList(ProductIdList);

        

        // 실행
        ResponseEntity<Long> response = orderController.createOrder(createOrderRequest);

        

        orderInfoRequest.setBillingId(billingService.createBilling(billingInfoRequest));
        orderInfoRequest.setOrderproducts(orderProductListService.createOrderProductList(ProductIdList));

        when(orderService.createOrder(orderInfoRequest)).thenReturn(expectedId);
        
        // 단언
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedId, response.getBody());
        verify(orderService, times(1)).createOrder(orderInfoRequest);

    }

}
