package com.kshrd.derphsar_api.rest.restcontroller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kshrd.derphsar_api.page.Pagination;
import com.kshrd.derphsar_api.repository.dto.ProductDto;
import com.kshrd.derphsar_api.repository.dto.WishListDto;
import com.kshrd.derphsar_api.rest.BaseApiResponse;
import com.kshrd.derphsar_api.rest.message.MessageProperties;
import com.kshrd.derphsar_api.rest.user.response.UserResponseModel;
import com.kshrd.derphsar_api.rest.utils.BaseApiNoPaginationResponse;
import com.kshrd.derphsar_api.rest.wishlist.request.WishListRequestModel;
import com.kshrd.derphsar_api.rest.wishlist.response.WishListFirstCreateResponse;
import com.kshrd.derphsar_api.rest.wishlist.response.WishListResponse;
import com.kshrd.derphsar_api.service.implement.UserServiceImp;
import com.kshrd.derphsar_api.service.implement.WishListServiceImp;
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
public class WishListRestController {

    private WishListServiceImp wishListServiceImp;
    private MessageProperties message;
    private UserServiceImp userServiceImp;

    @Autowired
    public void setUserServiceImp(UserServiceImp userServiceImp) {
        this.userServiceImp = userServiceImp;
    }

    @Autowired
    public void setMessage(MessageProperties message) {
        this.message = message;
    }

    @Autowired
    public void setWishListServiceImp(WishListServiceImp wishListServiceImp) {
        this.wishListServiceImp = wishListServiceImp;
    }






    /**
     * Post wishlists
     *
     * @param wishListRequestModel - Wishlist request model
     * @return - Return response message
     */
    @PostMapping("/wishlists")
    @ApiOperation(value = "create a wishlist", response = WishListFirstCreateResponse.class)
    public ResponseEntity<BaseApiNoPaginationResponse<WishListFirstCreateResponse>> createWishList(@RequestBody WishListRequestModel wishListRequestModel) {

        BaseApiNoPaginationResponse<WishListFirstCreateResponse> response = new BaseApiNoPaginationResponse<>();
        ModelMapper mapper = new ModelMapper();
        UUID uuid = UUID.randomUUID();
        WishListDto wishListDto = mapper.map(wishListRequestModel, WishListDto.class);
        wishListDto.setWishlistId("DP" + uuid.toString().substring(0, 10));

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Date date = new Date(timestamp.getTime());


        wishListDto.setFavDate(date);
        //wishListDto.setFavDate(new Timestamp(System.currentTimeMillis()));
        wishListDto.setStatus(true);

        try {
            WishListDto result = wishListServiceImp.createWishList(wishListDto);
            WishListFirstCreateResponse wishListResponse = mapper.map(result, WishListFirstCreateResponse.class);

            response.setMessage(message.inserted("Wishlist"));
            response.setData(wishListResponse);
            response.setStatus(HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
        }

        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }





    /**
     * Get wishlists
     *
     * @return - Return response message
     */
    @GetMapping("/wishlists")
    @ApiOperation(value = "show all wishlists", response = WishListResponse.class)
    public ResponseEntity<BaseApiResponse<List<WishListResponse>>> getWishLists() {

        ModelMapper mapper = new ModelMapper();
        BaseApiResponse<List<WishListResponse>> response = new BaseApiResponse<>();
        List<WishListResponse> wishListResponse = new ArrayList<>();

        try {
            List<WishListDto> wishList = wishListServiceImp.getWishLists();
            for (WishListDto wishListDto : wishList) {
                wishListResponse.add(mapper.map(wishListDto, WishListResponse.class));
            }
            if (!wishList.isEmpty()) {
                response.setMessage(message.selected("Wishlists"));
                response.setData(wishListResponse);
                response.setStatus(HttpStatus.FOUND);
            }else {
                response.setMessage(message.hasNoRecords("Wishlists"));
                response.setStatus(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }





    /**
     * Delete a wishlist
     *
     * @param wishlistId - Id of a wishlist
     * @return - Return response message
     */
    @DeleteMapping("/wishlists/{wishlistId}")
    @ApiOperation(value = "delete a wishlist", response = Void.class)
    public ResponseEntity<BaseApiNoPaginationResponse<Void>> deleteWishList(@PathVariable("wishlistId") String wishlistId) {
        BaseApiNoPaginationResponse<Void> response = new BaseApiNoPaginationResponse<>();

        try {
            wishListServiceImp.deleteWishList(wishlistId);
            response.setMessage(message.deleted("Wishlist"));
            response.setStatus(HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }

        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }


    /**
     * Get wishlists
     *
     * @param page  - Page of pagination
     * @param limit - Limit data of a pagination
     * @param totalPages - Total pages of data limited in a page
     * @param pagesToShow - Pages to show
     * @return - Return response message
     */
    @GetMapping("wishlists/{userId}")
    @ApiOperation(value = "show all wishlist by user id", response = Void.class)
    public ResponseEntity<BaseApiResponse<List<WishListResponse>>> getAllWishlistByUserId(@PathVariable("userId") String userId,
                                                                                          @RequestParam(value = "page" , required = false , defaultValue = "1") int page,
                                                                                          @RequestParam(value = "limit" , required = false , defaultValue = "3") int limit,
                                                                                          @RequestParam(value = "totalPages" , required = false , defaultValue = "3") int totalPages,
                                                                                          @RequestParam(value = "pagesToShow" , required = false , defaultValue = "3") int pagesToShow) {

        Pagination pagination = new Pagination(page, limit,totalPages,pagesToShow);
        pagination.setPage(page);
        pagination.setLimit(limit);
        pagination.nextPage();
        pagination.previousPage();
        pagination.setTotalCount(wishListServiceImp.countId());
        pagination.setTotalPages(pagination.getTotalPages());

        BaseApiResponse<List<WishListResponse>> response = new BaseApiResponse<>();
        ModelMapper mapper = new ModelMapper();
        List<WishListResponse> wishListResponses = new ArrayList<>();

        try {
            UserResponseModel userDto = userServiceImp.getOneUserById(userId);
            System.out.println(userDto);
            List<WishListDto> wishList = wishListServiceImp.getAllWishListByUserId(userDto.getId(),pagination);

            for (WishListDto wishListDto : wishList) {

                    WishListResponse wishListResponse = mapper.map(wishListDto, WishListResponse.class);
                    wishListResponses.add(wishListResponse);

            }

            if (!wishList.isEmpty()) {
                response.setData(wishListResponses);
                response.setStatus(HttpStatus.FOUND);
                response.setMessage(message.selected("WishLists"));
            }else {
                response.setStatus(HttpStatus.NOT_FOUND);
                response.setMessage(message.hasNoRecords("WishLists"));
            }

            response.setPagination(pagination);
        }catch (Exception e){
            e.printStackTrace();
        }

        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }
}