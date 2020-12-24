package com.kshrd.derphsar_api.repository;

import com.kshrd.derphsar_api.page.Pagination;
import com.kshrd.derphsar_api.repository.dto.PromotionDto;
import com.kshrd.derphsar_api.repository.dto.ShopDto;
import com.kshrd.derphsar_api.repository.dto.UserDto;
import com.kshrd.derphsar_api.repository.provider.ShopProvider;
import com.kshrd.derphsar_api.repository.provider.SubResourceProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ShopRepository {

    //Insert a shop
    @Insert("INSERT INTO dp_shops(shop_id, name, address, description, profile, cover, is_open, status, open_time, close_time, u_id, cat_id) "+
            "VALUES (#{shopId}, #{name}, #{address}, #{description}, #{profile}, #{cover}, #{openStatus}, #{status}, #{openTime}, #{closeTime}, #{u_id}, #{cat_id})")

    boolean insert(ShopDto shop);


    @Select("SELECT sh.*, sh.name AS shopName, sh.profile AS shopProfile, sh.status AS shopStatus, cat.*, cat.name AS catName, u.*,u.name AS userName, u.profile AS userProfile, u.status AS userStatus\n" +
            "\t\t\t\t\t\tFROM dp_shops AS sh\n" +
            "\t\t\t\t\t\tINNER JOIN dp_category AS cat ON sh.cat_id = cat.id\n" +
            "\t\t\t\t\t\tINNER JOIN dp_users AS u ON sh.u_id = u.id\n" +
            "\t\t\t\t\t\tWHERE sh.status = 'TRUE' LIMIT #{pagination.limit}  OFFSET #{pagination.offset}")
        @Results(id = "mapShop", value = {
                @Result(column = "shop_id" ,property = "shopId"),
                @Result(column = "is_open" ,property = "openStatus"),
                @Result(column = "shopName" ,property = "name"),
                @Result(column = "open_time" ,property = "openTime"),
                @Result(column = "close_time" ,property = "closeTime"),
                @Result(column = "shopProfile" ,property = "profile"),
                @Result(column = "shopStatus" ,property = "status"),
                @Result(column = "is_open" ,property = "openStatus"),

                @Result(column = "user_id" ,property = "user.userId"),
                @Result(column = "userName" ,property = "user.name"),
                @Result(column = "gender" ,property = "user.gender"),
                @Result(column = "age" ,property = "user.age"),
                @Result(column = "phone" ,property = "user.phone"),
                @Result(column = "email" ,property = "user.email"),
                @Result(column = "password" ,property = "user.password"),
                @Result(column = "status" ,property = "user.status"),
                @Result(column = "userProfile" ,property = "user.profile"),
                @Result(column = "userStatus" ,property = "user.status"),

                @Result(column = "cat_id" ,property = "category.catId"),
                @Result(column = "catName" ,property = "category.name"),

                //@Result(column = "id" ,property = "promotion.shop_id",many = @Many(select = "findPromotionByShopId")),
        })
    List<ShopDto> select(@Param("pagination") Pagination pagination);



    @Select("SELECT promo.* \n" +
            "\t\t\t\t\t\tFROM dp_promotion AS promo\n" +
            "\t\t\t\t\t\tINNER JOIN  dp_shops AS sh ON sh.id = promo.shop_id\n" +
            "\t\t\t\t\t\tWHERE sh.id = #{shopId} AND promo.status = 'TRUE'")
    List<PromotionDto> getAllpromotionsByShopId(int shopId);


    //Delete a shop
    @Delete("UPDATE dp_shops SET status = FALSE WHERE shop_id = #{shop_id}")
    void delete(String shop_id);



    //Update a shop
    @Update("UPDATE dp_shops SET name = #{shop.name}, address = #{shop.address}, description= #{shop.description} , profile= #{shop.profile} , cover= #{shop.cover} , status = #{shop.status}, is_open = #{shop.openStatus}, open_time= #{shop.openTime}, close_time = #{shop.closeTime} WHERE shop_id = #{shop_id}")
    boolean update(String shop_id, ShopDto shop);


    //find by id
    @Select("SELECT sh.*, sh.name AS shopName, sh.profile AS shopProfile, sh.status AS shopStatus, cat.*, cat.name AS catName, u.*,u.name AS userName, u.profile AS userProfile, u.status AS userStatus\n" +
            "\t\t\t\t\t\tFROM dp_shops AS sh\n" +
            "\t\t\t\t\t\tINNER JOIN dp_category AS cat ON sh.cat_id = cat.id\n" +
            "\t\t\t\t\t\tINNER JOIN dp_users AS u ON sh.u_id = u.id\n" +
            "\t\t\t\t\t\tWHERE sh.status = 'TRUE' AND  sh.shop_id = #{shopId}")

        @ResultMap("mapShop")
    ShopDto findById(String shopId);



    //Search shop by user
    @Select("SELECT sh.*, sh.name AS shopName, sh.profile AS shopProfile, sh.status AS shopStatus, cat.*, cat.name AS catName, u.*,u.name AS userName, u.profile AS userProfile, u.status AS userStatus\n" +
            "\t\t\t\t\t\tFROM dp_shops AS sh\n" +
            "\t\t\t\t\t\tINNER JOIN dp_category AS cat ON sh.cat_id = cat.id\n" +
            "\t\t\t\t\t\tINNER JOIN dp_users AS u ON sh.u_id = u.id\n" +
            "\t\t\t\t\t\tWHERE sh.u_id = #{id} AND sh.status = 'TRUE'")

    @ResultMap("mapShop")
    List<ShopDto> getAllShopsByUserId(int id);


    @SelectProvider(type = SubResourceProvider.class, method = "getUserByUserId")
    UserDto getUserByUserId(String userId);


    //count all shops
    @Select("SELECT COUNT(id) FROM dp_shops WHERE status = 'true'")
    int countId();

}
