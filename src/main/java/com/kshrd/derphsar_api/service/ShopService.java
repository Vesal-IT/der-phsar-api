package com.kshrd.derphsar_api.service;

import com.kshrd.derphsar_api.page.Pagination;
import com.kshrd.derphsar_api.repository.dto.ShopDto;
import com.kshrd.derphsar_api.repository.dto.UserDto;

import java.util.List;

public interface ShopService {

//    create shop
    ShopDto createShop(ShopDto shop);

//    get all shop
    List<ShopDto> getShops(Pagination pagination);

//    delete shop
    void deleteShop(String shop_id);

//    update shop
    ShopDto updateShop(String shop_id, ShopDto shop);

//    find by id
    ShopDto findById(String shopId);

    List<ShopDto> getAllShopsByUserId (int userId);

    UserDto getUserByUserId(String userId);

    //
    int countId();
}
