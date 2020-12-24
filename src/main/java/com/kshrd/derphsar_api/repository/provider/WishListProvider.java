package com.kshrd.derphsar_api.repository.provider;

import com.kshrd.derphsar_api.page.Pagination;
import com.kshrd.derphsar_api.repository.dto.UserDto;
import com.kshrd.derphsar_api.repository.dto.WishListDto;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class WishListProvider {

    //get all wishlists
    public String getWishList() {
        return new SQL(){{
            SELECT("*");
            FROM("dp_wishlist");
        }}.toString();
    }

    //insert new wishlist
    public String insertWishList(WishListDto wishListDto){
        return new SQL(){{
            INSERT_INTO("dp_wishlist");
            VALUES("wishlist_id, fav_date, u_id, pro_id, status", "#{wishlistId}, #{favDate}, #{u_id}, #{pro_id}, TRUE");
        }}.toString();
    }



    public String getUserByUserId(){
        return new SQL(){{
            SELECT("*");
            FROM("dp_users");
            WHERE("user_id = #{userId}");
        }}.toString();
    }

    public String getAllWishListByUserId(int uId, @Param("pagination") Pagination pagination){
        return new SQL(){{
            SELECT("w.wishlist_id, w.fav_date, w.status, u.name, u.user_id, pro.name AS proName, pro.pro_id, pro.price, pro.images, sh.name AS shopName, sh.shop_id, promo.title AS proTitle, promo.promo_id, promo.start_rank, promo.end_rank");
            FROM("dp_wishlist as w");
            INNER_JOIN("dp_users as u ON w.u_id = u.id");
            INNER_JOIN("dp_products as pro ON w.pro_id = pro.id");
            INNER_JOIN("dp_shops as sh ON pro.shop_id = sh.id");
            INNER_JOIN("dp_promotion AS promo ON promo.id = pro.promo_id");
            WHERE("w.u_id = #{uId} AND w.status = TRUE");
            LIMIT(pagination.getLimit());
            OFFSET(pagination.getOffset());
        }}.toString();
    }


}
