package com.kshrd.derphsar_api.rest.restcontroller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kshrd.derphsar_api.page.Pagination;
import com.kshrd.derphsar_api.repository.dto.*;
import com.kshrd.derphsar_api.repository.filter.OrderDetailFilter;
import com.kshrd.derphsar_api.rest.BaseApiResponse;
import com.kshrd.derphsar_api.rest.message.MessageProperties;
import com.kshrd.derphsar_api.rest.order.request.OrderRequest;
import com.kshrd.derphsar_api.rest.order.response.OrderResponse;
import com.kshrd.derphsar_api.rest.orderdetail.OrderDetailFirstRequest;
import com.kshrd.derphsar_api.rest.orderdetail.request.OrderDetailUpdateIsCheckoutModel;
import com.kshrd.derphsar_api.rest.orderdetail.response.OrderDetailFilterResponse;
import com.kshrd.derphsar_api.rest.orderdetail.response.OrderDetailResponse;
import com.kshrd.derphsar_api.rest.promotion.request.PromotionUpdateIsApplyModel;
import com.kshrd.derphsar_api.rest.promotion.response.PromotionResponseModel;
import com.kshrd.derphsar_api.rest.shop.request.ShopRequestModel;
import com.kshrd.derphsar_api.rest.shop.response.ShopCreateFirstResponse;
import com.kshrd.derphsar_api.rest.user.response.UserResponseModel;
import com.kshrd.derphsar_api.rest.utils.BaseApiNoPaginationResponse;
import com.kshrd.derphsar_api.service.implement.OrderDetailServiceImp;
import com.kshrd.derphsar_api.service.implement.OrderServiceImp;
import com.kshrd.derphsar_api.service.implement.ShopServiceImp;
import com.kshrd.derphsar_api.service.implement.UserServiceImp;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


//@CrossOrigin(origins = "*", maxAge = 3600)
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1")
public class OrderDetailRestController {
    private OrderDetailServiceImp orderDetailServiceImp;
    private MessageProperties message;
    private ShopServiceImp shopServiceImp;
    private UserServiceImp userServiceImp;
    private OrderServiceImp orderServiceImp;



    @Autowired
    public void setOrderServiceImp(OrderServiceImp orderServiceImp) {
        this.orderServiceImp = orderServiceImp;
    }

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
    public void setOrderDetailServiceImp(OrderDetailServiceImp orderDetailServiceImp) {
        this.orderDetailServiceImp = orderDetailServiceImp;
    }





    /**
     * Get order details
     *
     * @return - Return response message
     */
    @GetMapping("/orderdetails")
    @ApiOperation(value = "show all order details", response = OrderDetailResponse.class)
    public ResponseEntity<BaseApiResponse<List<OrderDetailResponse>>> getOrderDetails(){

        BaseApiResponse<List<OrderDetailResponse>> response = new BaseApiResponse<>();

        ObjectMapper mapper = new ObjectMapper();
        List<OrderDetailDto> orderDetailDtos = orderDetailServiceImp.getOrderDetails();
        List<OrderDetailResponse> orderDetailResponseList = new ArrayList<>();

            for(OrderDetailDto orderDetailDto : orderDetailDtos){
                try{
                    Object detail = mapper.readValue(orderDetailDto.getDetail().toString(), Object.class);
                    Object image = mapper.readValue(orderDetailDto.getImage().toString(), Object.class);

                    orderDetailDto.setDetail(detail);
                    orderDetailDto.setImage(image);

                    ModelMapper modelMapper = new ModelMapper();
                    OrderDetailResponse orderDetailResponse = modelMapper.map(orderDetailDto, OrderDetailResponse.class);
                    orderDetailResponseList.add(orderDetailResponse);
                }catch (JsonProcessingException e){
                    e.printStackTrace();
                }
            }
            response.setData(orderDetailResponseList);
            response.setStatus(HttpStatus.FOUND);
            response.setTime(new Timestamp(System.currentTimeMillis()));
            response.setMessage(message.selected("OrderDetails"));

        return ResponseEntity.ok(response);
    }


    /**
     * Get order details
     *
     * @param userId - uuid of user
     * @param page  - Page of pagination
     * @param limit - Limit data of a pagination
     * @param totalPages - Total pages of data limited in a page
     * @param pagesToShow - Pages to show
     * @return - Return response message
     */
    @GetMapping("/orderdetail-by-userid/{userId}")
    @ApiOperation("show all OrderDetails  by userId")
    public ResponseEntity<BaseApiResponse<List<OrderDetailFilterResponse>>> FilterbyUserAndOrder(@PathVariable("userId") String userId,
                                                                                                 @RequestParam(value = "page" , required = false , defaultValue = "1") int page,
                                                                                                 @RequestParam(value = "limit" , required = false , defaultValue = "3") int limit,
                                                                                                 @RequestParam(value = "totalPages" , required = false , defaultValue = "3") int totalPages,
                                                                                                 @RequestParam(value = "pagesToShow" , required = false , defaultValue = "3") int pagesToShow) {

//        System.out.println("userId : "+ userId);
        Pagination pagination = new Pagination(page, limit,totalPages,pagesToShow);
        pagination.setPage(page);
        pagination.setLimit(limit);
        pagination.nextPage();
        pagination.previousPage();

        pagination.setTotalCount(orderDetailServiceImp.countId());
        pagination.setTotalPages(pagination.getTotalPages());

        BaseApiResponse<List<OrderDetailFilterResponse>> response = new BaseApiResponse<>();

        ObjectMapper mapper = new ObjectMapper();

        UserResponseModel userDto = userServiceImp.getOneUserById(userId);
//        System.out.println(userDto);
//        System.out.println(userDto.getId());
        List<OrderDetailDto> orderDetailDtos = orderDetailServiceImp.findAllWithFilter(userDto.getId(),pagination);
        List<OrderDetailFilterResponse> orderDetailResponseList = new ArrayList<>();

        for(OrderDetailDto orderDetailDto : orderDetailDtos){
            try{
                Object detail = mapper.readValue(orderDetailDto.getDetail().toString(), Object.class);
                Object image = mapper.readValue(orderDetailDto.getImage().toString(), Object.class);

                orderDetailDto.setDetail(detail);
                orderDetailDto.setImage(image);

                ModelMapper modelMapper = new ModelMapper();
                OrderDetailFilterResponse orderDetailResponse = modelMapper.map(orderDetailDto, OrderDetailFilterResponse.class);
                orderDetailResponseList.add(orderDetailResponse);
            }catch (JsonProcessingException e){
                e.printStackTrace();
            }
        }
        response.setPagination(pagination);
        response.setData(orderDetailResponseList);
        response.setStatus(HttpStatus.FOUND);
        response.setTime(new Timestamp(System.currentTimeMillis()));
        response.setMessage(message.selected("OrderDetails"));

        return ResponseEntity.ok(response);
    }




    /**
     * Get order details
     *
     * @param orderId - uuid of order
     * @param page  - Page of pagination
     * @param limit - Limit data of a pagination
     * @param totalPages - Total pages of data limited in a page
     * @param pagesToShow - Pages to show
     * @return - Return response message
     */
    @GetMapping("/orderdetail-by-orderId/{orderId}")
    @ApiOperation("show all OrderDetails  by orderId")
    public ResponseEntity<BaseApiResponse<List<OrderDetailFilterResponse>>> findOrderDetailByOrderId(@PathVariable("orderId") String orderId,
                                                                                                 @RequestParam(value = "page" , required = false , defaultValue = "1") int page,
                                                                                                 @RequestParam(value = "limit" , required = false , defaultValue = "3") int limit,
                                                                                                 @RequestParam(value = "totalPages" , required = false , defaultValue = "3") int totalPages,
                                                                                                 @RequestParam(value = "pagesToShow" , required = false , defaultValue = "3") int pagesToShow) {

        Pagination pagination = new Pagination(page, limit,totalPages,pagesToShow);
        pagination.setPage(page);
        pagination.setLimit(limit);
        pagination.nextPage();
        pagination.previousPage();


        pagination.setTotalCount(orderDetailServiceImp.countId());
        pagination.setTotalPages(pagination.getTotalPages());

        BaseApiResponse<List<OrderDetailFilterResponse>> response = new BaseApiResponse<>();

        ObjectMapper mapper = new ObjectMapper();

        OrderDto orderDto = orderServiceImp.getOrderByOrderId(orderId);

        List<OrderDetailDto> orderDetailDtos = orderDetailServiceImp.findOrderDetailByOrderId(orderDto.getId(),pagination);
        List<OrderDetailFilterResponse> orderDetailResponseList = new ArrayList<>();

        for(OrderDetailDto orderDetailDto : orderDetailDtos){
            try{
                Object detail = mapper.readValue(orderDetailDto.getDetail().toString(), Object.class);
                Object image = mapper.readValue(orderDetailDto.getImage().toString(), Object.class);

                orderDetailDto.setDetail(detail);
                orderDetailDto.setImage(image);

                ModelMapper modelMapper = new ModelMapper();
                OrderDetailFilterResponse orderDetailResponse = modelMapper.map(orderDetailDto, OrderDetailFilterResponse.class);
                orderDetailResponseList.add(orderDetailResponse);
            }catch (JsonProcessingException e){
                e.printStackTrace();
            }
        }
        response.setPagination(pagination);
        response.setData(orderDetailResponseList);
        response.setStatus(HttpStatus.FOUND);
        response.setTime(new Timestamp(System.currentTimeMillis()));
        response.setMessage(message.selected("OrderDetails"));

        return ResponseEntity.ok(response);
    }





    /**
     * Delete an order detail
     *
     * @param orderDetailId - Id of order detail
     * @return - Return response message
     */
    @DeleteMapping("/orderdetails/{orderDetailId}")
    @ApiOperation("Delete OrderDetail by OrderDetailId")
    public ResponseEntity<BaseApiNoPaginationResponse<String>> delete(@PathVariable String orderDetailId)
    {
        BaseApiNoPaginationResponse<String> response = new BaseApiNoPaginationResponse<>();
        try{
            Boolean isDeleted = orderDetailServiceImp.deleteOrderdetail(orderDetailId);
            if(isDeleted)
            {
                response.setMessage(message.deleted("OrderDetail"));
                response.setStatus(HttpStatus.OK);
            }
            else
            {
                response.setMessage(message.deleteError("OrderDetail"));
                response.setStatus(HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e)
        {
            response.setMessage(message.deleteError("OrderDetails"));
            response.setStatus(HttpStatus.BAD_REQUEST);
        }
        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }




    /**
     * Post a shop
     *
     * @param orderDetailFirstRequest - Shop request model
     * @return - Return response message
     */
    @PostMapping("/orderdetails")
    @ApiOperation(value = "add products to cart", response = OrderDetailFilterResponse.class)
    public ResponseEntity<BaseApiNoPaginationResponse<OrderDetailFilterResponse>> addProductToCart(@RequestBody OrderDetailFirstRequest orderDetailFirstRequest){

        ModelMapper mapper = new ModelMapper();
        UUID uuid = UUID.randomUUID();
        BaseApiNoPaginationResponse<OrderDetailFilterResponse> response = new BaseApiNoPaginationResponse<>();

        OrderDetailDto orderDetailDto = mapper.map(orderDetailFirstRequest,OrderDetailDto.class);

        if(orderDetailFirstRequest.getQuatity() != 0 ){
            orderDetailDto.setItemId("DP"+uuid.toString().substring(0,10));
            orderDetailDto.setStatus(true);

            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            Date date = new Date(timestamp.getTime());
           // System.out.println(date);

            orderDetailDto.setOrderDate(date);
            OrderDetailDto orderDetailDto1 = orderDetailServiceImp.addProductToCart(orderDetailDto);

            OrderDetailFilterResponse orderDetailFilterResponse = mapper.map(orderDetailDto1, OrderDetailFilterResponse.class);
            response.setMessage(message.inserted("OrderDetail"));
            response.setData(orderDetailFilterResponse);
            response.setStatus(HttpStatus.CREATED);

        }else {
            response.setMessage(message.insertError("OrderDetail"));
            response.setStatus(HttpStatus.BAD_REQUEST);
        }
        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }


    /**
     * Put a promotion
     *
     * @param orderId - Id of a promotion
     * @param orderDetailUpdateIsCheckoutModel - Promotion request model
     * @return - Return response message
     */
    @PatchMapping("/orderDetails/{orderId}")
    @ApiOperation(value = "checkout", response = OrderDetailUpdateIsCheckoutModel.class)
    public ResponseEntity<BaseApiNoPaginationResponse<List<OrderDetailUpdateIsCheckoutModel>>> updateIsCheckout(@PathVariable("orderId") String orderId,
                                                                                                  @RequestBody OrderDetailUpdateIsCheckoutModel orderDetailUpdateIsCheckoutModel) {

        ModelMapper modelMapper = new ModelMapper();
        BaseApiNoPaginationResponse<List<OrderDetailUpdateIsCheckoutModel>> response = new BaseApiNoPaginationResponse<>();

        List<OrderDetailUpdateIsCheckoutModel> orderDetailUpdateIsCheckoutModelArrayList = new ArrayList<>();
        try {
            OrderDetailDto orderDetailDto = modelMapper.map(orderDetailUpdateIsCheckoutModel, OrderDetailDto.class);
            OrderDto findOrderByOrderId = orderServiceImp.getOrderByOrderId(orderId);
            System.out.println(findOrderByOrderId);
            List<OrderDetailDto> result = orderDetailServiceImp.updateIsCheckout(findOrderByOrderId.getId(), orderDetailDto);
            for(OrderDetailDto orderDetailDto1 : result){
                orderDetailUpdateIsCheckoutModelArrayList.add(modelMapper.map(orderDetailDto1, OrderDetailUpdateIsCheckoutModel.class));
            }

            response.setMessage(message.updated("OrderDetail"));
            response.setStatus(HttpStatus.OK);
            response.setData(orderDetailUpdateIsCheckoutModelArrayList);
        }catch (Exception e){
            e.printStackTrace();
        }

        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }



}
