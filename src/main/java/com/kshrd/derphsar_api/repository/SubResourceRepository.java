package com.kshrd.derphsar_api.repository;


import com.kshrd.derphsar_api.repository.dto.ProductDto;
import com.kshrd.derphsar_api.repository.dto.ShopDto;
import com.kshrd.derphsar_api.repository.dto.UserDto;
import com.kshrd.derphsar_api.repository.provider.SubResourceProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubResourceRepository {


//    @SelectProvider(type = SubResourceProvider.class, method = "getAllShopsByUserId")
//    List<ShopDto> getAllShopsByUserId(int userId);


    //Search shop by user
    @SelectProvider(type = SubResourceProvider.class, method = "getAllShopsByUserId")
    @Results({
            @Result(column = "shop_id", property = "shopId"),
            @Result(column = "is_open", property = "isOpen"),
            @Result(column = "open_time", property = "openTime"),
            @Result(column = "close_time", property = "closeTime"),
          //  @Result(column = "u_id" ,property = "user.id",many = @Many(select = "selectOneUser")),
    })
    List<ShopDto> getAllShopsByUserId(int id);


    //select on shop
//    @Select("SELECT * FROM dp_users WHERE id = #{userId}")
//    UserDto selectOneUser(int userId);


    @SelectProvider(type = SubResourceProvider.class, method = "getUserByUserId")
    UserDto getUserByUserId(String userId);
}
