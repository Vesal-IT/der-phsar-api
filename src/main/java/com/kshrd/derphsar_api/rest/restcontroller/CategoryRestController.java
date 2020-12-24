package com.kshrd.derphsar_api.rest.restcontroller;

import com.kshrd.derphsar_api.page.Pagination;
import com.kshrd.derphsar_api.repository.dto.CategoryDto;
import com.kshrd.derphsar_api.rest.BaseApiResponse;
import com.kshrd.derphsar_api.rest.category.request.CategoryRequestModel;
import com.kshrd.derphsar_api.rest.category.response.CategoryResponseModel;
import com.kshrd.derphsar_api.rest.message.MessageProperties;
import com.kshrd.derphsar_api.service.implement.CategoryServiceImp;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


//@CrossOrigin(origins = "*", maxAge = 3600)
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("api/v1")
public class CategoryRestController {

    CategoryServiceImp categoryServiceImp;
    private MessageProperties message;

    @Autowired
    public void setMessage(MessageProperties message) {
        this.message = message;
    }

    @Autowired
    public void setCategoryService(CategoryServiceImp categoryServiceImp) {
        this.categoryServiceImp = categoryServiceImp;
    }

    /**
     * Get categories
     *
     * @return - Returns all categories
     */
    @GetMapping("/categories")
    @ApiOperation(value = "Show all categories", response = CategoryResponseModel.class)
    public ResponseEntity<BaseApiResponse<List<CategoryResponseModel>>> getCategories( @RequestParam(value = "page" , required = false , defaultValue = "1") int page,
                                                                                       @RequestParam(value = "limit" , required = false , defaultValue = "3") int limit,
                                                                                       @RequestParam(value = "totalPages" , required = false , defaultValue = "3") int totalPages,
                                                                                       @RequestParam(value = "pagesToShow" , required = false , defaultValue = "3") int pagesToShow){


        Pagination pagination = new Pagination(page, limit,totalPages,pagesToShow);
        pagination.setPage(page);
        pagination.setLimit(limit);
        pagination.nextPage();
        pagination.previousPage();


        pagination.setTotalCount(categoryServiceImp.countId());
        pagination.setTotalPages(pagination.getTotalPages());
        ModelMapper mapper = new ModelMapper();
        BaseApiResponse<List<CategoryResponseModel>> response =
                new BaseApiResponse<>();
        List<CategoryResponseModel> categories = new ArrayList<>();

        try{
            List<CategoryDto> categoryDtoList = categoryServiceImp.select(pagination);
            for (CategoryDto categoryDto : categoryDtoList) {
                categories.add(mapper.map(categoryDto, CategoryResponseModel.class));
            }

            if(!categoryDtoList.isEmpty()){
                response.setMessage(message.selected("Categories"));
                response.setData(categories);
                response.setStatus(HttpStatus.FOUND);
            }else {
                response.setMessage(message.hasNoRecords("Categories"));
                response.setStatus(HttpStatus.NOT_FOUND);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        response.setPagination(pagination);
        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }
}
