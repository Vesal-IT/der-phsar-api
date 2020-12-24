package com.kshrd.derphsar_api.service.implement;

import com.kshrd.derphsar_api.repository.SubResourceRepository;
import com.kshrd.derphsar_api.repository.dto.ShopDto;
import com.kshrd.derphsar_api.repository.dto.UserDto;
import com.kshrd.derphsar_api.service.SubResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SubResourceServiceImp  implements SubResourceService {

    private SubResourceRepository subResourceRepository;

    @Autowired
    public void setSubResourceRepository(SubResourceRepository subResourceRepository) {
        this.subResourceRepository = subResourceRepository;
    }

    @Override
    public List<ShopDto> getAllShopsByUserId(int userId) {
        return subResourceRepository.getAllShopsByUserId(userId);
    }

    @Override
    public UserDto getUserByUserId(String userId) {
        return subResourceRepository.getUserByUserId(userId);
    }


//    @Override
//    public List<ShopDto> getAllShopsByUserId(int userId) {
//        return subResourceRepository.getAllShopsByUserId(userId);
//    }


}
