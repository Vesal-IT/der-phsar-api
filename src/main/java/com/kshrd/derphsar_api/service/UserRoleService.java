package com.kshrd.derphsar_api.service;

import com.kshrd.derphsar_api.repository.dto.UserRoleDto;

public interface UserRoleService {

    UserRoleDto findUserRoleById(int id);

    UserRoleDto updateRoleToShopkeeper(int userId, UserRoleDto userRoleDto);


}
