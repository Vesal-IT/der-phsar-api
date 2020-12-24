package com.kshrd.derphsar_api.rest.restcontroller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.kshrd.derphsar_api.page.Pagination;
import com.kshrd.derphsar_api.repository.dto.*;
import com.kshrd.derphsar_api.rest.BaseApiResponse;
import com.kshrd.derphsar_api.rest.message.MessageProperties;
import com.kshrd.derphsar_api.rest.order.response.OrderHistoryOfAUserResponse;
import com.kshrd.derphsar_api.rest.order.response.OrderOneResponse;
import com.kshrd.derphsar_api.rest.order.response.OrderResponse;
import com.kshrd.derphsar_api.rest.product.response.ProductsOfAUserResponse;
import com.kshrd.derphsar_api.rest.promotion.response.PromotionResponseModel;
import com.kshrd.derphsar_api.rest.user.response.UserResponseModel;
import com.kshrd.derphsar_api.rest.utils.BaseApiNoPaginationResponse;
import com.kshrd.derphsar_api.service.implement.OrderServiceImp;
import com.kshrd.derphsar_api.service.implement.ShopServiceImp;
import com.kshrd.derphsar_api.service.implement.UserServiceImp;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


//@CrossOrigin(origins = "*", maxAge = 3600)
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1")
public class OrderRestController {

    private OrderServiceImp orderServiceImp;
    private MessageProperties message;
    private ShopServiceImp shopServiceImp;
    private UserServiceImp userServiceImp;

    @Autowired
    public void setUserServiceImp(UserServiceImp userServiceImp) {
        this.userServiceImp = userServiceImp;
    }

    @Autowired
    public void setShopServiceImp(ShopServiceImp shopServiceImp) {
        this.shopServiceImp = shopServiceImp;
    }

    @Autowired
    public void setMessage(MessageProperties message) {
        this.message = message;
    }


    @Autowired
    public void setOrderServiceImp(OrderServiceImp orderServiceImp) {
        this.orderServiceImp = orderServiceImp;
    }





    /**
     * get all orders by shop id
     *
     * @param shopId -  id of shop
     * @return - list of orders
     */

    @GetMapping("orders/{shopId}")
    @ApiOperation(value = "show all orders by shopId", response = Void.class)
    public ResponseEntity<BaseApiResponse<List<OrderResponse>>> getAllOrderByShopId(@PathVariable("shopId") String shopId) {

        BaseApiResponse<List<OrderResponse>> baseApiResponse = new BaseApiResponse<>();
        ModelMapper mapper = new ModelMapper();
        List<OrderResponse> orderResponses = new ArrayList<>();

        try{
            ShopDto shopDto = shopServiceImp.findById(shopId);
            List<OrderDto> orderDtos = orderServiceImp.getAllOrderByShopId(shopDto.getId());
            for (OrderDto wishListDto : orderDtos) {
                OrderResponse wishListResponse = mapper.map(wishListDto, OrderResponse.class);
                orderResponses.add(wishListResponse);
            }

            if(!orderDtos.isEmpty()){
                baseApiResponse.setMessage(message.selected("Orders"));
                baseApiResponse.setData(orderResponses);
                baseApiResponse.setStatus(HttpStatus.FOUND);
            }else {
                baseApiResponse.setMessage(message.inserted("Orders"));
                baseApiResponse.setStatus(HttpStatus.NOT_FOUND);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        baseApiResponse.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(baseApiResponse);
    }



    /**
     * show all latest 5 records of orders
     *
     * @return - list of orders
     */
    @GetMapping("orders")
    @ApiOperation(value = "show all recent 5 records of orders", response = Void.class)
    public ResponseEntity<BaseApiNoPaginationResponse<List<OrderResponse>>> getOrdersLatestFiveRecords() {

        BaseApiNoPaginationResponse<List<OrderResponse>> baseApiResponse = new BaseApiNoPaginationResponse<>();
        ModelMapper mapper = new ModelMapper();
        List<OrderResponse> orderResponses = new ArrayList<>();

        try{
            List<OrderDto> orderDtos = orderServiceImp.getOrdersLatestFiveRecords();
            for (OrderDto wishListDto : orderDtos) {
                OrderResponse wishListResponse = mapper.map(wishListDto, OrderResponse.class);
                orderResponses.add(wishListResponse);
            }

            if(!orderDtos.isEmpty()){
                baseApiResponse.setMessage(message.selected("Orders"));
                baseApiResponse.setData(orderResponses);
                baseApiResponse.setStatus(HttpStatus.FOUND);
            }else {
                baseApiResponse.setMessage(message.inserted("Orders"));
                baseApiResponse.setStatus(HttpStatus.NOT_FOUND);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        baseApiResponse.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(baseApiResponse);
    }





    /**
     * Get Order History
     *
     * @param page  - Page of pagination
     * @param limit - Limit data of a pagination
     * @param totalPages - Total pages of data limited in a page
     * @param pagesToShow - Pages to show
     * @return - Return response message
     */
    @GetMapping("order-history/{userId}")
    @ApiOperation(value = "show all order history by user id", response = Void.class)
    public ResponseEntity<BaseApiResponse<List<OrderHistoryOfAUserResponse>>> getAllOrdersHistoryByUserId(@PathVariable("userId") String userId,
                                                                                                          @RequestParam(value = "page" , required = false , defaultValue = "1") int page,
                                                                                                          @RequestParam(value = "limit" , required = false , defaultValue = "3") int limit,
                                                                                                          @RequestParam(value = "totalPages" , required = false , defaultValue = "3") int totalPages,
                                                                                                          @RequestParam(value = "pagesToShow" , required = false , defaultValue = "3") int pagesToShow) {

        Pagination pagination = new Pagination(page, limit,totalPages,pagesToShow);
        pagination.setPage(page);
        pagination.setLimit(limit);
        pagination.nextPage();
        pagination.previousPage();
        pagination.setTotalCount(orderServiceImp.countId());
        pagination.setTotalPages(pagination.getTotalPages());

        BaseApiResponse<List<OrderHistoryOfAUserResponse>> response = new BaseApiResponse<>();
        ModelMapper mapper = new ModelMapper();
        List<OrderHistoryOfAUserResponse> productResponseModels = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            UserResponseModel userDto = userServiceImp.getOneUserById(userId);
            List<OrderDto> orders = orderServiceImp.getAllOrdersHistoryByUserId(userDto.getId(),pagination);
            for (OrderDto orderDto : orders) {

//                Object images = objectMapper.readValue(orderDto.getImage().toString(), Object.class);
//                orderDto.setImage(images);

                //Object details = objectMapper.readValue(orderDto.get.toString(), Object.class);

                OrderHistoryOfAUserResponse productResponseModel = mapper.map(orderDto, OrderHistoryOfAUserResponse.class);
                productResponseModels.add(productResponseModel);
            }

            if (!orders.isEmpty()) {
                response.setData(productResponseModels);
                response.setStatus(HttpStatus.FOUND);
                response.setMessage(message.selected("Orders"));
            }else {
                response.setStatus(HttpStatus.NOT_FOUND);
                response.setMessage(message.hasNoRecords("Orders"));
            }

            response.setPagination(pagination);
        }catch (Exception e){
            e.printStackTrace();
        }

        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }




    /**
     * Get a promotion
     *
     * @param orderId - Id of a orderId
     * @return - Return response message
     */
    @GetMapping("/one-orders/{orderId}")
    @ApiOperation(value = "get a order by a order id", response = OrderOneResponse.class)
    public ResponseEntity<BaseApiNoPaginationResponse<List<OrderOneResponse>>> getOrderByOrderId(@PathVariable("orderId") String orderId){

        ModelMapper mapper = new ModelMapper();
        BaseApiNoPaginationResponse<List<OrderOneResponse>> response =
                new BaseApiNoPaginationResponse<>();
        List<OrderOneResponse> orderResponses = new ArrayList<>();

        OrderDto orderDto = orderServiceImp.getOrderByOrderId(orderId);
        if(orderDto != null){
            orderResponses.add(mapper.map(orderDto, OrderOneResponse.class));

            response.setMessage(message.selectedOne("Order"));
            response.setData(orderResponses);
            response.setStatus(HttpStatus.FOUND);
        }else {
            response.setMessage(message.hasNoRecord("Order"));
            response.setStatus(HttpStatus.NOT_FOUND);
        }

        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }
}
