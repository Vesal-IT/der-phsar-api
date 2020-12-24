package com.kshrd.derphsar_api.service.implement;


import com.kshrd.derphsar_api.repository.UserRoleRepository;
import com.kshrd.derphsar_api.repository.dto.UserRoleDto;
import com.kshrd.derphsar_api.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImp implements UserRoleService {

    private UserRoleRepository userRoleRepository;

    @Autowired
    public void setUserRoleRepository(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }


    @Override
    public UserRoleDto findUserRoleById(int id) {
        return userRoleRepository.findUserRoleById(id);
    }

    @Override
    public UserRoleDto updateRoleToShopkeeper(int userId, UserRoleDto userRoleDto) {
        boolean isUpdated =  userRoleRepository.updateRoleToShopkeeper(userId,userRoleDto);
        if(isUpdated)
            return userRoleDto;
        else
            return null;
    }

}
