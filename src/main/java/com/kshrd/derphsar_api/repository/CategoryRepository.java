package com.kshrd.derphsar_api.repository;

import com.kshrd.derphsar_api.mybatis.JSONTypeHandlerPg;
import com.kshrd.derphsar_api.page.Pagination;
import com.kshrd.derphsar_api.repository.dto.CategoryDto;
import com.kshrd.derphsar_api.repository.provider.CategoryProvider;
import com.kshrd.derphsar_api.repository.provider.ProductProvider;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository {

    //select all categories
    @SelectProvider(type = CategoryProvider.class, method = "select")
    @Results({
            @Result(column = "id", property ="id"),
            @Result(column = "cat_id" ,property = "catId"),
            @Result(column = "name" ,property = "name")
    })
    public List<CategoryDto> select(@Param("pagination") Pagination pagination);


    @SelectProvider(type = CategoryProvider.class, method = "countId")
    int countId();
}
