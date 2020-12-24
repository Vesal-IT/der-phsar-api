package com.kshrd.derphsar_api.service;

import com.kshrd.derphsar_api.page.Pagination;
import com.kshrd.derphsar_api.repository.dto.OrderDto;

import java.util.List;

public interface OrderService {

    List<OrderDto> getAllOrderByShopId(int shopId);
    List<OrderDto> getOrdersLatestFiveRecords();

    int countId();

    //get order history by user id
    List<OrderDto> getAllOrdersHistoryByUserId(int userId, Pagination pagination);

    OrderDto getOrderByOrderId(String orderId);
}
