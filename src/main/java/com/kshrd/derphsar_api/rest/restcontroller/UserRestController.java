package com.kshrd.derphsar_api.rest.restcontroller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kshrd.derphsar_api.page.Pagination;
import com.kshrd.derphsar_api.repository.dto.ProductDto;
import com.kshrd.derphsar_api.repository.dto.ShopDto;
import com.kshrd.derphsar_api.repository.dto.UserDto;
import com.kshrd.derphsar_api.rest.BaseApiResponse;
import com.kshrd.derphsar_api.rest.message.MessageProperties;
import com.kshrd.derphsar_api.rest.product.response.ProductResponseModel;
import com.kshrd.derphsar_api.rest.shop.request.ShopRequestModel;
import com.kshrd.derphsar_api.rest.user.request.UserRequestModel;
import com.kshrd.derphsar_api.rest.user.response.UserByShopResponse;
import com.kshrd.derphsar_api.rest.user.response.UserResponseModel;
import com.kshrd.derphsar_api.rest.utils.BaseApiNoPaginationResponse;
import com.kshrd.derphsar_api.service.implement.ShopServiceImp;
import com.kshrd.derphsar_api.service.implement.UserServiceImp;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//@CrossOrigin(origins = "*", maxAge = 3600)
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("api/v1")
public class UserRestController {

    private UserServiceImp userServiceImp;
    private BCryptPasswordEncoder encoder;
    private MessageProperties message;
    private ShopServiceImp shopServiceImp;

    @Autowired
    public void setShopServiceImp(ShopServiceImp shopServiceImp) {
        this.shopServiceImp = shopServiceImp;
    }

    @Autowired
    public void setMessage(MessageProperties message) {
        this.message = message;
    }

    @Autowired
    public void setUserServiceImp(UserServiceImp userServiceImp) {
        this.userServiceImp = userServiceImp;
    }

    @Autowired
    public void setEncoder(BCryptPasswordEncoder encoder) {
        this.encoder = encoder;
    }


    /**
     * Post a user
     *
     * @param userRequestModel - User request model
     * @return - Return response message
     */
    @PostMapping("/register")
    @ApiOperation(value = "user register", response = UserResponseModel.class)
    public ResponseEntity<BaseApiNoPaginationResponse<UserResponseModel>> createUser(
            @RequestBody UserRequestModel userRequestModel) {

        BaseApiNoPaginationResponse<UserResponseModel> response = new BaseApiNoPaginationResponse<>();
        ModelMapper mapper = new ModelMapper();

        UserDto userDto = mapper.map(userRequestModel, UserDto.class);

        try{
            if(!userRequestModel.getName().isEmpty() && !userRequestModel.getEmail().isEmpty() && userRequestModel.getAge() != 0 && !userRequestModel.getPassword().isEmpty()  && !userRequestModel.getPhone().isEmpty()){
                userDto.setPassword(encoder.encode(userDto.getPassword()));
                userDto.setStatus(true);

                UUID uuid = UUID.randomUUID();
                userDto.setUserId("DP"+uuid.toString().substring(0,10));
                userDto.setStatus(true);

                UserDto result = userServiceImp.insertUser(userDto);
                UserResponseModel lastResponse = userServiceImp.getOneUserById(result.getUserId());
                UserResponseModel result2 = mapper.map(lastResponse, UserResponseModel.class);
                response.setMessage(message.inserted("User"));
                response.setData(result2);
                response.setStatus(HttpStatus.OK);
                response.setTime(new Timestamp(System.currentTimeMillis()));
            }else {
                response.setMessage(message.insertError("User"));
                response.setStatus(HttpStatus.BAD_REQUEST);
            }

        }catch (Exception e){
            response.setMessage(e.getCause().getMessage());
            response.setStatus(HttpStatus.BAD_REQUEST);
            response.setData(null);
        }
        return ResponseEntity.ok(response);
    }


    /**
     * Get users
     *
     * @return - Return response message
     */
    @GetMapping("/users")
    @ApiOperation(value = "show all users", response = UserResponseModel.class)
    public ResponseEntity<BaseApiResponse<List<UserResponseModel>>> getAllUsers(){
        BaseApiResponse<List<UserResponseModel>> response = new BaseApiResponse<>();

        try{
            List<UserResponseModel> users = userServiceImp.getAllUsers();
            if(!users.isEmpty()){
                response.setMessage(message.selected("Users"));
                response.setStatus(HttpStatus.FOUND);
                response.setData(users);
            }else {
                response.setMessage(message.hasNoRecord("Users"));
                response.setStatus(HttpStatus.NOT_FOUND);
                response.setData(null);
            }

            response.setTime(new Timestamp(System.currentTimeMillis()));
        }catch (Exception e){
            response.setMessage(e.getCause().getMessage());
        }
        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }





    /**
     * Get a user
     *
     * @param userId - Id of a user
     * @return - Return response message
     */
    @GetMapping("/users/{userId}")
    @ApiOperation(value = "show a user by userId", response = UserResponseModel.class)
    public ResponseEntity<BaseApiNoPaginationResponse<UserResponseModel>> getOneUserById(@PathVariable String userId){
        BaseApiNoPaginationResponse<UserResponseModel> response = new BaseApiNoPaginationResponse<>();
        try {
            UserResponseModel userResponse = userServiceImp.getOneUserById(userId);
            response.setMessage(message.selectedOne("User"));
            response.setStatus(HttpStatus.FOUND);
            response.setData(userResponse);
            response.setTime(new Timestamp(System.currentTimeMillis()));
        }catch (Exception ex){
            response.setMessage(message.hasNoRecords("User"));
            response.setStatus(HttpStatus.NOT_FOUND);
            response.setData(null);
            response.setTime(new Timestamp(System.currentTimeMillis()));
        }
        return ResponseEntity.ok(response);
    }



    /**
     * Get all Customer by role buyer
     *
     * @return - list of customers response
     */
    @GetMapping("/customers")
    @ApiOperation(value = "show all customers by roleName", response = UserByShopResponse.class)
    public ResponseEntity<BaseApiResponse<List<UserByShopResponse>>> getAllCustomersByShopIdOrRoleName(
            //@RequestParam(value="shopId",required = false,defaultValue = "0") String shopId,
            @RequestParam(value="roleName",required = false,defaultValue = " ") String roleName,
            @RequestParam(value = "page" , required = false , defaultValue = "1") int page,
            @RequestParam(value = "limit" , required = false , defaultValue = "3") int limit,
            @RequestParam(value = "totalPages" , required = false , defaultValue = "3") int totalPages,
            @RequestParam(value = "pagesToShow" , required = false , defaultValue = "3") int pagesToShow) {


        Pagination pagination = new Pagination(page, limit,totalPages,pagesToShow);
        pagination.setPage(page);
        pagination.setLimit(limit);
        pagination.nextPage();
        pagination.previousPage();

        pagination.setTotalCount(userServiceImp.countId());
        pagination.setTotalPages(pagination.getTotalPages());


        BaseApiResponse<List<UserByShopResponse>> response = new BaseApiResponse<>();
        ModelMapper mapper = new ModelMapper();
        List<UserByShopResponse> userResponseModelist = new ArrayList<>();

        try{
//            ShopDto shopDto = shopServiceImp.findById(shopId);
            List<UserDto> userDtos = userServiceImp.getAllCustomersByRoleName(roleName,pagination);
            for(UserDto userDto : userDtos){
                userResponseModelist.add(mapper.map(userDto, UserByShopResponse.class));
            }

            if(!userDtos.isEmpty()){
                response.setMessage(message.selected("Users"));
                response.setData(userResponseModelist);
                response.setStatus(HttpStatus.FOUND);
            }else {
                response.setMessage(message.hasNoRecord("User"));
                response.setStatus(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        response.setPagination(pagination);
        response.setTime(new Timestamp(System.currentTimeMillis()));

        return ResponseEntity.ok(response);
    }


    /**
     * Get all Customer by shop id
     *
     * @return - list of customers response
     */
    @GetMapping("/customers-in-shop")
    @ApiOperation(value = "show all customers by shopId", response = UserByShopResponse.class)
    public ResponseEntity<BaseApiResponse<List<UserByShopResponse>>> getAllCustomersByShopId(
            @RequestParam(value="shopId",required = false,defaultValue = "0") String shopId,
            @RequestParam(value = "page" , required = false , defaultValue = "1") int page,
            @RequestParam(value = "limit" , required = false , defaultValue = "3") int limit,
            @RequestParam(value = "totalPages" , required = false , defaultValue = "3") int totalPages,
            @RequestParam(value = "pagesToShow" , required = false , defaultValue = "3") int pagesToShow) {


        Pagination pagination = new Pagination(page, limit,totalPages,pagesToShow);
        pagination.setPage(page);
        pagination.setLimit(limit);
        pagination.nextPage();
        pagination.previousPage();

        pagination.setTotalCount(userServiceImp.countId());
        pagination.setTotalPages(pagination.getTotalPages());


        BaseApiResponse<List<UserByShopResponse>> response = new BaseApiResponse<>();
        ModelMapper mapper = new ModelMapper();
        List<UserByShopResponse> userResponseModelist = new ArrayList<>();

        try{
            ShopDto shopDto = shopServiceImp.findById(shopId);
            List<UserDto> userDtos = userServiceImp.getAllCustomersByShopId(shopDto.getId(),pagination);
            for(UserDto userDto : userDtos){
                userResponseModelist.add(mapper.map(userDto, UserByShopResponse.class));
            }

            if(!userDtos.isEmpty()){
                response.setMessage(message.selected("Users"));
                response.setData(userResponseModelist);
                response.setStatus(HttpStatus.FOUND);
            }else {
                response.setMessage(message.hasNoRecord("User"));
                response.setStatus(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        response.setPagination(pagination);
        response.setTime(new Timestamp(System.currentTimeMillis()));

        return ResponseEntity.ok(response);
    }



    /**
     * Delete a user
     *
     * @param userId - Id of a user
     * @return - Return response message
     */
    @DeleteMapping("admin/users/{userId}")
    @ApiOperation(value = "delete a user", response = UserResponseModel.class)
    public ResponseEntity<BaseApiNoPaginationResponse<Void>> deleteUser(@PathVariable("userId") String userId){

        BaseApiNoPaginationResponse<Void> response = new BaseApiNoPaginationResponse<>();

        try{
            userServiceImp.deleteUserById(userId);
            response.setMessage(message.deleted("User"));
            response.setStatus(HttpStatus.OK);
            response.setTime(new Timestamp(System.currentTimeMillis()));
        }catch (Exception e){
            response.setMessage(message.deleteError("User"));
            response.setData(null);
            response.setStatus(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(response);
    }





    /**
     * Put a user
     *
     * @param userId - Id of a user
     * @param userRequestModel - User request model
     * @return - Return response message
     */
    @PutMapping("/users/{userId}")
    @ApiOperation(value = "update a user by Id", response = UserResponseModel.class)
    public ResponseEntity<BaseApiNoPaginationResponse<UserResponseModel>> updateUserById(
            @PathVariable("userId") String userId,
            @RequestBody UserRequestModel userRequestModel){

        BaseApiNoPaginationResponse<UserResponseModel> response = new BaseApiNoPaginationResponse <>();
        ModelMapper modelMapper = new ModelMapper();

        try {
            UserDto dto = modelMapper.map(userRequestModel, UserDto.class);
            UserResponseModel responseModel = modelMapper.map(userServiceImp.updateUserById(userId,dto),UserResponseModel.class);

            response.setMessage(message.updated("User"));
            response.setStatus(HttpStatus.OK);
            response.setData(responseModel);
        }catch (Exception e){
            e.printStackTrace();
        }

        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }
}
