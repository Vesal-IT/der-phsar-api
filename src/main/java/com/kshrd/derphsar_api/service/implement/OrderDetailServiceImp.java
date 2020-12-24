package com.kshrd.derphsar_api.service.implement;


import com.kshrd.derphsar_api.page.Pagination;
import com.kshrd.derphsar_api.repository.OrderDetailRepository;
import com.kshrd.derphsar_api.repository.dto.OrderDetailDto;
import com.kshrd.derphsar_api.repository.dto.OrderDto;
import com.kshrd.derphsar_api.repository.filter.OrderDetailFilter;
import com.kshrd.derphsar_api.rest.BaseApiResponse;
import com.kshrd.derphsar_api.rest.orderdetail.response.OrderDetailResponse;
import com.kshrd.derphsar_api.service.OrderDetailService;
//import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailServiceImp implements OrderDetailService {

    private OrderDetailRepository orderDetailRepository;

    @Autowired
    public void setOrderDetailRepository(OrderDetailRepository orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
    }

    @Override
    public List<OrderDetailDto> getOrderDetails(){
        List<OrderDetailDto> data = orderDetailRepository.getOrderDetails();
        try{
          if(!data.isEmpty())
              return data;
        }catch (Exception e){
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public List<OrderDetailDto> findAllWithFilter(int userId, Pagination pagination) {
        List<OrderDetailDto> data = orderDetailRepository.findAllWithFilter(userId,pagination);
        try {
            if(!data.isEmpty())
                return data;
        }catch (Exception e){
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public List<OrderDetailDto> findOrderDetailByOrderId(int orderId, Pagination pagination) {
        List<OrderDetailDto> data = orderDetailRepository.findOrderDetailByOrderId(orderId,pagination);
        try {
            if(!data.isEmpty())
                return data;
        }catch (Exception e){
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public boolean deleteOrderdetail(String order_detail_id) {
        boolean isDeleted = orderDetailRepository.deleteOrderDetail(order_detail_id);
        if(isDeleted){
            return true;
        }else
            return false;
    }

    //count all id
    @Override
    public int countId() {
        return orderDetailRepository.countId();
    }

    @Override
    public OrderDetailDto addProductToCart(OrderDetailDto orderDetailDto) {
        boolean isInsertOrderDetail = orderDetailRepository.insertOrderDetail(orderDetailDto);
        if(isInsertOrderDetail)
            return orderDetailDto;
        else
            return null;
    }

    @Override
    public OrderDetailDto getOrderDetailByItemId(String itemId) {
        return orderDetailRepository.getOrderDetailByItemId(itemId);
    }

    @Override
    public List<OrderDetailDto> updateIsCheckout(int orderId, OrderDetailDto orderDetailDto) {

        Pagination pagination = new Pagination();
        List<OrderDetailDto> orderDetailDtoList = findOrderDetailByOrderId(orderId,pagination);
        boolean orderDetailDtos = false;

        for(OrderDetailDto orderDetailDto1 : orderDetailDtoList){
            orderDetailDtos = orderDetailRepository.updateIsCheckout(orderDetailDto1.getOrder_id(),orderDetailDto);
            System.out.println(orderDetailDtos);
        }
        return orderDetailDtoList;
    }
}
