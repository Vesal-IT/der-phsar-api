package com.kshrd.derphsar_api.service;

import com.kshrd.derphsar_api.page.Pagination;
import com.kshrd.derphsar_api.repository.dto.UserDto;
import com.kshrd.derphsar_api.repository.dto.UserRoleDto;
import com.kshrd.derphsar_api.rest.user.request.UserRequestModel;
import com.kshrd.derphsar_api.rest.user.response.UserResponseModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    UserDto findByEmail(String email);

    UserDto insertUser(UserDto userDto);

    List<UserResponseModel> getAllUsers();

    UserResponseModel getOneUserById(String userId);

    void deleteUserById(String userId);

    UserDto updateUserById(String userId, UserDto userDto);

    List<UserDto> getAllCustomersByRoleName (String roleName, Pagination pagination);

    List<UserDto> getAllCustomersByShopId(int shopId, Pagination pagination);

    int countId();

}
