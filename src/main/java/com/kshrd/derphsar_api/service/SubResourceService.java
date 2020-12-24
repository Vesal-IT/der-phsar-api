package com.kshrd.derphsar_api.service;

import com.kshrd.derphsar_api.repository.dto.ShopDto;
import com.kshrd.derphsar_api.repository.dto.UserDto;

import java.util.List;

public interface SubResourceService {

    List<ShopDto> getAllShopsByUserId (int userId);
    UserDto getUserByUserId(String userId);
}
