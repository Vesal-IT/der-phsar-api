package com.kshrd.derphsar_api.repository;


import com.kshrd.derphsar_api.repository.dto.UserRoleDto;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository {


    @Select("SELECT * FROM dp_user_role WHERE user_id = #{id}")
    @Results({
            @Result(column = "user_id", property = "userId"),
            @Result(column = "role_id", property = "roleId"),
    })
    UserRoleDto findUserRoleById(int id);




    @Update("UPDATE dp_user_role SET role_id = 2 WHERE user_id = #{userId}")
    boolean updateRoleToShopkeeper(int userId, UserRoleDto userRoleDto);
}
