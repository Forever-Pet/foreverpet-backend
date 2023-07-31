package com.hello.foreverpet.domain.dto;

import java.util.List;

import com.hello.foreverpet.domain.dto.request.BillingInfoRequest;
import com.hello.foreverpet.domain.dto.request.OrderInfoRequest;

import lombok.Data;

@Data
public class OrderRequest {

    private BillingInfoRequest billingInfoRequest;

    private OrderInfoRequest orderInfoRequest;

    private List<Long> productNoList;
}
