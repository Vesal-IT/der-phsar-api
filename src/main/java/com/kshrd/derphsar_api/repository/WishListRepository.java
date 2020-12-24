package com.kshrd.derphsar_api.repository;

import com.kshrd.derphsar_api.page.Pagination;
import com.kshrd.derphsar_api.repository.dto.ProductDto;
import com.kshrd.derphsar_api.repository.dto.UserDto;
import com.kshrd.derphsar_api.repository.dto.WishListDto;
import com.kshrd.derphsar_api.repository.provider.WishListProvider;
import com.kshrd.derphsar_api.rest.wishlist.response.WishListResponse;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishListRepository {

    //Insert wishlist
//    @Insert("INSERT INTO dp_wishlist(wishlist_id, fav_date, u_id, pro_id, status) "+
//            "VALUES (#{wishlistId}, #{favDate}, #{u_id}, #{pro_id}), TRUE")
//
    @InsertProvider(type = WishListProvider.class, method = "insertWishList")
    boolean insert(WishListDto wishListDto);


    //Get all wishlist
    @SelectProvider(value = WishListProvider.class, method = "getWishList")
    @Results({
            @Result(column = "wishlist_id" ,property = "wishlistId"),
            @Result(column = "fav_date" ,property = "favDate")
    })
    List<WishListDto> select();


    //Delete a wishlist
    @Delete("UPDATE dp_wishlist SET status = FALSE WHERE wishlist_id = #{wishlist_id}")
    void delete(String wishlist_id);



    //
//    @Select("SELECT pro.name, sh.name, promote.start_rank, promote.end_rank, w.fav_date FROM dp_wishlist w  \t\tINNER JOIN dp_products pro \n" +
//            "\t\tON pro.id = w.pro_id \n" +
//            "\t\tINNER JOIN dp_shops sh\n" +
//            "\t\tON pro.shop_id = sh.id\n" +
//            "\t\tINNER JOIN dp_promotion promote\n" +
//            "\t\tON promote.shop_id = sh.id\n" +
//            "\t\tWHERE w.u_id = #{uId}")
//        @Results({
//                @Result(column = "id", property = "id"),
//                @Result(column = "wishlish_id", property = "wishlistId"),
//                @Result(column = "fav_date", property = "favDate"),
//                @Result(column = "u_id", property = "user.id"),
//                @Result(column = "pro_id", property = "product.id"),
//        })
//    List<WishListDto> getAllShopsByUserId(int uId);

    @Select("SELECT id ,wishlist_id,fav_date, u_id, pro_id, status  FROM dp_wishlist  WHERE u_id = #{uId} AND status = TRUE")
            @Results({
                    @Result(column = "id", property = "id"),
                    @Result(column = "wishlist_id", property = "wishlistId"),
                    @Result(column = "fav_date", property = "favDate"),
                    @Result(column = "u_id", property = "u_id"),
                    //@Result(column = "pro_id", property = "pro_id"),
                    @Result(column = "pro_id", property = "product", many = @Many(select = "getProduct")),
            })
    List<WishListDto> getAllShopsByUserId(int uId);


    //find product by pro_id
//    @Select("SELECT pro.name FROM dp_products pro INNER JOIN dp_wishlist w ON pro.id = w.pro_id WHERE pro.id = #{pro_id}")
//    ProductDto getAllPro(int id);

    @Select("SELECT * FROM dp_products WHERE id = #{pro_id}")
//    @Results({
//            @Result(column = "id" ,property = "id"),
//            @Result(column = "shop_id" ,property = "shopId"),
//            @Result(column = "name" ,property = "name"),
//            @Result(column = "address" ,property = "address")
//    })
    ProductDto getProduct(int pro_id);



    @SelectProvider(type = WishListProvider.class, method = "getUserByUserId")
    UserDto getUserByUserId(String userId);


    @SelectProvider(type = WishListProvider.class, method = "getAllWishListByUserId")
    @Results({
            @Result(property = "wishlistId", column = "wishlist_id"),
            @Result(property = "favDate", column = "fav_date"),

            @Result(property = "user.name", column = "name"),
            @Result(property = "user.userId", column = "user_id"),

            @Result(property = "product.name", column = "proName"),
            @Result(property = "product.proId", column = "pro_id"),
            @Result(property = "product.price", column = "price"),
            @Result(property = "product.images", column = "images"),

            @Result(property = "product.shop.shopId", column = "shop_id"),
            @Result(property = "product.shop.name", column = "shopName"),


            @Result(property = "product.shop.promotion.promoId", column = "promo_id"),
            @Result(property = "product.shop.promotion.title", column = "proTitle"),
            @Result(property = "product.shop.promotion.startRank", column = "start_rank"),
            @Result(property = "product.shop.promotion.endRank", column = "end_rank"),
            //@Result(column = "u_id", property = "user", many = @Many(select = "getUserById"))

    })
    List<WishListDto> getAllWishListByUserId(int uId, @Param("pagination") Pagination pagination);



    @Select("SELECT * FROM dp_users WHERE id=#{u_id}")
    @Results({
            @Result(column = "id" ,property = "id"),
            @Result(column = "name" ,property = "name")
    })
    UserDto getUserById(int u_id);

    //count all orders
    @Select("SELECT COUNT(id) FROM dp_wishlist WHERE status = 'true'")
    int countId();

}
