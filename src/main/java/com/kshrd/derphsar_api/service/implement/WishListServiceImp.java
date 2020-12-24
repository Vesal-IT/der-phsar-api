package com.kshrd.derphsar_api.service.implement;

import com.kshrd.derphsar_api.page.Pagination;
import com.kshrd.derphsar_api.repository.WishListRepository;
import com.kshrd.derphsar_api.repository.dto.UserDto;
import com.kshrd.derphsar_api.repository.dto.WishListDto;
import com.kshrd.derphsar_api.rest.wishlist.response.WishListResponse;
import com.kshrd.derphsar_api.service.WishLishService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class WishListServiceImp implements WishLishService{

    private WishListRepository wishListRepository;
    ModelMapper mapper = new ModelMapper();

    @Autowired
    public void setWishListRepository(WishListRepository wishListRepository) {
        this.wishListRepository = wishListRepository;
    }

    //create wishlist
    @Override
    public WishListDto createWishList(WishListDto wishListDto) {
        boolean isInserted = wishListRepository.insert(wishListDto);
        if (isInserted)
            return wishListDto;
        else
            return null;
    }

    //get all wishlist
    @Override
    public List<WishListDto> getWishLists(){
        return wishListRepository.select();
    }

    //delete  a wishlist
    @Override
    public void deleteWishList(String wishlist_id) {
        wishListRepository.delete(wishlist_id);
    }

    @Override
    public List<WishListDto> getAllWishListByUserId(int userId, Pagination pagination) {
        return wishListRepository.getAllWishListByUserId(userId, pagination);
    }


    @Override
    public UserDto getUserByUserId(String userId) {
        return wishListRepository.getUserByUserId(userId);
    }


    //count all id
    @Override
    public int countId() {
        return wishListRepository.countId();
    }
}
