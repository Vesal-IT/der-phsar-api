package com.kshrd.derphsar_api.service;

import com.kshrd.derphsar_api.page.Pagination;
import com.kshrd.derphsar_api.repository.dto.UserDto;
import com.kshrd.derphsar_api.repository.dto.WishListDto;
import com.kshrd.derphsar_api.rest.wishlist.response.WishListResponse;

import java.util.List;

public interface WishLishService {
    //create wishlist
    WishListDto createWishList(WishListDto wishListDto);

    //get wishlist
    List<WishListDto> getWishLists();

    //delete wishlist
    void deleteWishList(String wishlist_id);

    //get wishlist by id
    List<WishListDto> getAllWishListByUserId(int userId, Pagination pagination);

    
    UserDto getUserByUserId(String userId);

    //count all id
    int countId();
}
