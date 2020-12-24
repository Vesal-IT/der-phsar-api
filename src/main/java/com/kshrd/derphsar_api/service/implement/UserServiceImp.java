package com.kshrd.derphsar_api.service.implement;


import com.kshrd.derphsar_api.page.Pagination;
import com.kshrd.derphsar_api.repository.UserRepository;
import com.kshrd.derphsar_api.repository.dto.OrderDto;
import com.kshrd.derphsar_api.repository.dto.UserDto;
import com.kshrd.derphsar_api.repository.dto.UserRoleDto;
import com.kshrd.derphsar_api.rest.message.MessageProperties;
import com.kshrd.derphsar_api.rest.order.response.OrderUserResponse;
import com.kshrd.derphsar_api.rest.role.response.RoleResponse;
import com.kshrd.derphsar_api.rest.user.request.UserRequestModel;
import com.kshrd.derphsar_api.rest.user.response.UserResponseModel;
import com.kshrd.derphsar_api.rest.utils.BaseApiNoPaginationResponse;
import com.kshrd.derphsar_api.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImp implements UserService {
    private UserRepository userRepository;
    private MessageProperties message;



    @Autowired
    public void setMessage(MessageProperties message) {
        this.message = message;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserDto insertUser(UserDto userDto) {
//        ModelMapper mapper = new ModelMapper();
//        UserDto userDto1 = mapper.map(userRequest, UserDao.class);

        //BaseApiNoPaginationResponse<UserResponseModel> response = new BaseApiNoPaginationResponse<>();
        OrderDto orderDto = new OrderDto();
        UUID uuid = UUID.randomUUID();
        orderDto.setOrderId("DP"+uuid.toString().substring(0,10));

        userRepository.insertUser(userDto);
        userRepository.insertUserRole(userDto);
        userRepository.insertOrder(orderDto.getOrderId(), userDto);
        return userDto;
    }

    @Override
    public List<UserResponseModel> getAllUsers() {
        List<UserResponseModel> list = userRepository.getAllUsers();

        List<OrderUserResponse> orderUserResponseList;
        List<RoleResponse> roleResponse;
        for(int i=0 ;i<list.size(); i++){
            roleResponse = userRepository.role(list.get(i).getId());
            list.get(i).setRole(roleResponse);

            orderUserResponseList = userRepository.getOrderByUserId(list.get(i).getId());
            list.get(i).setOrder(orderUserResponseList);
        }



        return list;
    }

    @Override
    public UserResponseModel getOneUserById(String userId) {
        UserResponseModel userResponseModel = userRepository.getOneUserById(userId);

        List<RoleResponse> roleResponses;
        roleResponses = userRepository.role(userResponseModel.getId());
        userResponseModel.setRole(roleResponses);

        List<OrderUserResponse> orderUserResponse;
        orderUserResponse = userRepository.getOrderByUserId(userResponseModel.getId());
        userResponseModel.setOrder(orderUserResponse);

        return userResponseModel;
    }

    @Override
    public void deleteUserById(String userId) {
        userRepository.deleteOneUserById(userId);
    }

    @Override
    public UserDto updateUserById(String userId, UserDto userDto) {
        Boolean isUpdated = userRepository.updateUserById(userId, userDto);
        if(isUpdated)
            return userDto;
        else
            return null;
    }

    @Override
    public List<UserDto> getAllCustomersByRoleName(String roleName, Pagination pagination) {
//            if(shopId != 0 && !roleName.isEmpty())
//                return null;
//           if(shopId == 0)
                return userRepository.getAllCustomersByRoleName(roleName,pagination);
//           else
//                return userRepository.getAllCustomersByShopId(shopId);

    }

    @Override
    public List<UserDto> getAllCustomersByShopId(int shopId, Pagination pagination) {
                return userRepository.getAllCustomersByShopId(shopId);

    }

    @Override
    public int countId() {
        return userRepository.countId();
    }




    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserDto user = this.userRepository.findByEmail(email);
       //System.out.println(user);
        return user;
    }
}
