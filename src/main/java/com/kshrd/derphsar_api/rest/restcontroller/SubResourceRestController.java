//package com.kshrd.derphsar_api.rest.restcontroller;
//
//
//import com.kshrd.derphsar_api.repository.dto.ShopDto;
//import com.kshrd.derphsar_api.repository.dto.UserDto;
//import com.kshrd.derphsar_api.rest.BaseApiResponse;
//import com.kshrd.derphsar_api.rest.message.MessageProperties;
//import com.kshrd.derphsar_api.rest.shop.request.ShopRequestModel;
//import com.kshrd.derphsar_api.rest.shop.respone.BaseAPIResponse;
//import com.kshrd.derphsar_api.service.implement.SubResourceServiceImp;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.sql.Timestamp;
//import java.util.ArrayList;
//import java.util.List;
//
//@RestController
//@RequestMapping("api/v1")
//public class SubResourceRestController {
//
//    private SubResourceServiceImp subResourceServiceImp;
//    private MessageProperties message;
//
//
//    @Autowired
//    public void setMessage(MessageProperties message) {
//        this.message = message;
//    }
//
//    @Autowired
//    public void setSubResourceServiceImp(SubResourceServiceImp subResourceServiceImp) {
//        this.subResourceServiceImp = subResourceServiceImp;
//    }
//
//    @GetMapping("users/{userId}/shops")
//    public ResponseEntity<BaseApiResponse<List<ShopRequestModel>>> getShops(@PathVariable("userId") String userId) {
//
//        ModelMapper mapper = new ModelMapper();
//        BaseApiResponse<List<ShopRequestModel>> response =
//                new BaseApiResponse<>();
//
//        UserDto userDto = subResourceServiceImp.getUserByUserId(userId);
//        List<ShopDto> shopDtoList = subResourceServiceImp.getAllShopsByUserId(userDto.getId());
//        List<ShopRequestModel> shops = new ArrayList<>();
//        for (ShopDto shopDto : shopDtoList) {
//            shops.add(mapper.map(shopDto, ShopRequestModel.class));
//        }
//        response.setMessage(message.selected("Shops"));
//        response.setData(shops);
//        response.setStatus(HttpStatus.OK);
//        response.setTime(new Timestamp(System.currentTimeMillis()));
//        return ResponseEntity.ok(response);
//    }
//}
