package com.kshrd.derphsar_api.rest.restcontroller;

import com.kshrd.derphsar_api.page.Pagination;
import com.kshrd.derphsar_api.repository.ProductRepository;
import com.kshrd.derphsar_api.repository.dto.CategoryDto;
import com.kshrd.derphsar_api.repository.dto.ProductDto;
import com.kshrd.derphsar_api.repository.dto.ShopDto;
import com.kshrd.derphsar_api.repository.dto.UserDto;
import com.kshrd.derphsar_api.rest.BaseApiResponse;
import com.kshrd.derphsar_api.rest.message.MessageProperties;
import com.kshrd.derphsar_api.rest.product.request.ProductRequestModel;
import com.kshrd.derphsar_api.rest.product.response.ProductCreateFirstResponse;
import com.kshrd.derphsar_api.rest.product.response.ProductsOfAUserResponse;
import com.kshrd.derphsar_api.rest.product.response.ProductResponseModel;
import com.kshrd.derphsar_api.rest.user.response.UserResponseModel;
import com.kshrd.derphsar_api.rest.utils.BaseApiNoPaginationResponse;
import com.kshrd.derphsar_api.service.implement.ProductServiceImp;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kshrd.derphsar_api.service.implement.ShopServiceImp;
import com.kshrd.derphsar_api.service.implement.UserServiceImp;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


//@CrossOrigin(origins = "*", maxAge = 3600
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1")
public class ProductRestController {
    private UserServiceImp userServiceImp;
    private ProductServiceImp productServiceImp;
    private MessageProperties message;
    private ShopServiceImp shopServiceImp;
    private ProductRepository productRepository;


    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Autowired
    public void setShopServiceImp(ShopServiceImp shopServiceImp) {
        this.shopServiceImp = shopServiceImp;
    }

    @Autowired
    public void setMessage(MessageProperties message) {
        this.message = message;
    }

    @Autowired
    public void setProductServiceImp(ProductServiceImp productServiceImp) {
        this.productServiceImp = productServiceImp;
    }

    @Autowired
    public void setUserServiceImp(UserServiceImp userServiceImp) {
        this.userServiceImp = userServiceImp;
    }

    /**
     * Post a product
     *
     * @param productRequestModel - Product request model
     * @return - Return response message
     */
    @PostMapping("/products")
    @ApiOperation(value = "post product to shop", response = ProductCreateFirstResponse.class)
    public ResponseEntity<BaseApiNoPaginationResponse<ProductCreateFirstResponse>> createProduct(
            @RequestBody ProductRequestModel productRequestModel) {

        BaseApiNoPaginationResponse<ProductCreateFirstResponse> response = new BaseApiNoPaginationResponse<>();
        ModelMapper mapper = new ModelMapper();

        ProductDto productDto = mapper.map(productRequestModel, ProductDto.class);

        UUID uuid = UUID.randomUUID();

        if(productRequestModel.getPrice() != 0  && productRequestModel.getShop_id() !=0 && !productRequestModel.getName().isEmpty()){

            productDto.setProId("DP"+uuid.toString().substring(0,10));
            productDto.setStatus(true);

            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            Date date = new Date(timestamp.getTime());
            productDto.setPostDate(date);

            productDto.setSoldStatus(false);
            productDto.setViewCount(0);

            ProductDto result = productServiceImp.insert(productDto);
            ProductCreateFirstResponse responseModel = mapper.map(result, ProductCreateFirstResponse.class);
            response.setMessage(message.inserted("Product"));
            response.setData(responseModel);
            response.setStatus(HttpStatus.CREATED);

        }else {
            response.setMessage(message.insertError("Product"));
            response.setStatus(HttpStatus.BAD_REQUEST);
        }

        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }


    /**
     * Get products
     *
     * @param page  - Page of pagination
     * @param limit - Limit data of a pagination
     * @param totalPages - Total pages of data limited in a page
     * @param pagesToShow - Pages to show
     * @return - Return response message
     */
    @GetMapping("/products")
    @ApiOperation(value = "show all products", response = ProductResponseModel.class)
    public ResponseEntity<BaseApiResponse<List<ProductResponseModel>>> getProducts(
            //@RequestParam(value="shopId",required = false,defaultValue = " ") String shopId,
            @RequestParam(value = "page" , required = false , defaultValue = "1") int page,
            @RequestParam(value = "limit" , required = false , defaultValue = "10") int limit,
            @RequestParam(value = "totalPages" , required = false , defaultValue = "3") int totalPages,
            @RequestParam(value = "pagesToShow" , required = false , defaultValue = "3") int pagesToShow) {

        Pagination pagination = new Pagination(page, limit,totalPages,pagesToShow);
        pagination.setPage(page);
        pagination.setLimit(limit);
        pagination.nextPage();
        pagination.previousPage();


        pagination.setTotalCount(productServiceImp.countId());
        pagination.setTotalPages(pagination.getTotalPages());

        BaseApiResponse<List<ProductResponseModel>> response = new BaseApiResponse<>();

        ObjectMapper mapper = new ObjectMapper();
        //ShopDto shopDto = productServiceImp.getShopByShopId(shopId);
        List<ProductDto> productDtos = productServiceImp.getProducts(pagination);

        List<ProductResponseModel> productResponseModels = new ArrayList<>();

        for(ProductDto productDto : productDtos){
            try {
                Object details = mapper.readValue(productDto.getDetails().toString(), Object.class);
                Object images = mapper.readValue(productDto.getImages().toString(), Object.class);
                productDto.setDetails(details);
                productDto.setImages(images);

                ModelMapper modelMapper = new ModelMapper();
                ProductResponseModel productResponseModel = modelMapper .map(productDto, ProductResponseModel.class);
                productResponseModels.add(productResponseModel);

                if(!productDtos.isEmpty()){
                    response.setData(productResponseModels);
                    response.setMessage(message.selected("Products"));
                    response.setStatus(HttpStatus.OK);
                }else {
                    response.setMessage(message.hasNoRecord("Products"));
                    response.setStatus(HttpStatus.BAD_REQUEST);
                }
                }catch (JsonProcessingException e){
                    e.printStackTrace();
                }
            }


        response.setPagination(pagination);
        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }

    /**
     * Get a product
     *
     * @param proId - UUID of a product
     * @return - Return response message
     */
    @GetMapping("/products/{proId}")
    @ApiOperation(value = "find a product by proId", response = ProductResponseModel.class)
    public ResponseEntity<BaseApiNoPaginationResponse<List<ProductResponseModel>>> findById(@PathVariable("proId") String proId){

        BaseApiNoPaginationResponse<List<ProductResponseModel>> response = new BaseApiNoPaginationResponse<>();
        ObjectMapper objectMapper = new ObjectMapper();
        ProductDto productDto = productServiceImp.findById(proId);
        List<ProductResponseModel> products = new ArrayList<>();




        try {
            productServiceImp.updateViewCount(proId);
            productDto.setViewCount(productRepository.getViewCount(proId));
            Object details = objectMapper.readValue(productDto.getDetails().toString(), Object.class);
            Object images = objectMapper.readValue(productDto.getImages().toString(), Object.class);
            productDto.setDetails(details);
            productDto.setImages(images);

            ModelMapper modelMapper = new ModelMapper();
            ProductResponseModel productResponseModel = modelMapper .map(productDto, ProductResponseModel.class);
            products.add(productResponseModel);

            if(productDto != null){
                response.setData(products);
                response.setMessage(message.selected("Products"));
                response.setStatus(HttpStatus.OK);
            }else {
                response.setMessage(message.hasNoRecord("Products"));
                response.setStatus(HttpStatus.BAD_REQUEST);
            }
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }

        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }


    /**
     * Get Products
     * @param shopId - UUID of shop
     * @param page  - Page of pagination
     * @param limit - Limit data of a pagination
     * @param totalPages - Total pages of data limited in a page
     * @param pagesToShow - Pages to show
     * @return - Return response message
     */
    @GetMapping("products-in-shop/{shopId}")
    @ApiOperation(value = "show all products by shop id", response = ProductResponseModel.class)
    public ResponseEntity<BaseApiResponse<List<ProductResponseModel>>> getAllProductsByShopId(@PathVariable("shopId") String shopId,
                                                                                              @RequestParam(value = "page" , required = false , defaultValue = "1") int page,
                                                                                              @RequestParam(value = "limit" , required = false , defaultValue = "3") int limit,
                                                                                              @RequestParam(value = "totalPages" , required = false , defaultValue = "3") int totalPages,
                                                                                              @RequestParam(value = "pagesToShow" , required = false , defaultValue = "3") int pagesToShow) {

        Pagination pagination = new Pagination(page, limit,totalPages,pagesToShow);
        pagination.setPage(page);
        pagination.setLimit(limit);
        pagination.nextPage();
        pagination.previousPage();
        pagination.setTotalCount(productServiceImp.countId());
        pagination.setTotalPages(pagination.getTotalPages());

        BaseApiResponse<List<ProductResponseModel>> response = new BaseApiResponse<>();
        ModelMapper mapper = new ModelMapper();
        List<ProductResponseModel> productResponseModels = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            ShopDto shopDto = shopServiceImp.findById(shopId);
            List<ProductDto> products = productServiceImp.findProductByShopId(shopDto.getId(),pagination);
            for (ProductDto productDto : products) {

                Object images = objectMapper.readValue(productDto.getImages().toString(), Object.class);
                productDto.setImages(images);

                ProductResponseModel productResponseModel = mapper.map(productDto, ProductResponseModel.class);
                productResponseModels.add(productResponseModel);
            }

            if (!products.isEmpty()) {
                response.setData(productResponseModels);
                response.setStatus(HttpStatus.FOUND);
                response.setMessage(message.selected("Products"));
            }else {
                response.setStatus(HttpStatus.NOT_FOUND);
                response.setMessage(message.hasNoRecords("Products"));
            }

            response.setPagination(pagination);
            System.out.println("Product = " + response);
        }catch (Exception e){
            e.printStackTrace();
        }

        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }



    /**
     * Get new products for 12 records to show homepage
     *
     * @return - list of new products response
     */
    @GetMapping("/new-products")
    @ApiOperation(value = "show all new arrival products 12 records", response = ProductResponseModel.class)
    public ResponseEntity<BaseApiResponse<List<ProductResponseModel>>> getNewProducts() {

        BaseApiResponse<List<ProductResponseModel>> response = new BaseApiResponse<>();

        ObjectMapper mapper = new ObjectMapper();

        List<ProductResponseModel> productResponseModels = new ArrayList<>();

        try{
            List<ProductDto> productDtos = productServiceImp.getNewProducts();
            for(ProductDto productDto : productDtos){
                try {
                    Object details = mapper.readValue(productDto.getDetails().toString(), Object.class);
                    Object images = mapper.readValue(productDto.getImages().toString(), Object.class);
                    productDto.setDetails(details);
                    productDto.setImages(images);

                    ModelMapper modelMapper = new ModelMapper();
                    ProductResponseModel productResponseModel = modelMapper .map(productDto, ProductResponseModel.class);
                    productResponseModels.add(productResponseModel);
                }catch (JsonProcessingException e){
                    e.printStackTrace();
                }
            }

            if(!productDtos.isEmpty()){
                response.setMessage(message.selected("Products"));
                response.setData(productResponseModels);
                response.setStatus(HttpStatus.FOUND);
            }else {
                response.setMessage(message.hasNoRecords("Products"));
                response.setStatus(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }




    /**
     * Get new products for 12 records to show homepage
     *
     * @return - list of new products response
     */
    @GetMapping("/new-products-in-shop/{shopId}")
    @ApiOperation(value = "show all new arrival products 8 records in shop", response = ProductResponseModel.class)
    public ResponseEntity<BaseApiResponse<List<ProductResponseModel>>> getNewProductsByShopId(@PathVariable("shopId") String shopId) {

        BaseApiResponse<List<ProductResponseModel>> response = new BaseApiResponse<>();

        ObjectMapper mapper = new ObjectMapper();

        List<ProductResponseModel> productResponseModels = new ArrayList<>();

        try{
            ShopDto shopDto = shopServiceImp.findById(shopId);
            List<ProductDto> productDtos = productServiceImp.getNewProductsByShopId(shopDto.getId());
            for(ProductDto productDto : productDtos){
                try {
                    Object details = mapper.readValue(productDto.getDetails().toString(), Object.class);
                    Object images = mapper.readValue(productDto.getImages().toString(), Object.class);
                    productDto.setDetails(details);
                    productDto.setImages(images);

                    ModelMapper modelMapper = new ModelMapper();
                    ProductResponseModel productResponseModel = modelMapper .map(productDto, ProductResponseModel.class);
                    productResponseModels.add(productResponseModel);
                }catch (JsonProcessingException e){
                    e.printStackTrace();
                }
            }

            if(!productDtos.isEmpty()){
                response.setMessage(message.selected("Products"));
                response.setData(productResponseModels);
                response.setStatus(HttpStatus.FOUND);
            }else {
                response.setMessage(message.hasNoRecords("Products"));
                response.setStatus(HttpStatus.NOT_FOUND);
            }

        }catch (Exception e){
            e.printStackTrace();
        }


        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }




    /**
     * Get related products for 12 records to show homepage
     *
     * @return - list of related products response
     */
    @GetMapping("/related-products")
    @ApiOperation(value = "show all related products 12 records", response = ProductResponseModel.class)
    public ResponseEntity<BaseApiResponse<List<ProductResponseModel>>> getRelatedProducts(@RequestParam(value = "categoryId" , required = false , defaultValue = "1") String categoryId) {

        BaseApiResponse<List<ProductResponseModel>> response = new BaseApiResponse<>();
        ObjectMapper mapper = new ObjectMapper();
        List<ProductResponseModel> productResponseModels = new ArrayList<>();

        try{
            CategoryDto categoryDto = productServiceImp.getCategoryByCatId(categoryId);
            List<ProductDto> productDtos = productServiceImp.getRelatedProducts(categoryDto.getId());
            for(ProductDto productDto : productDtos){
                try {
                    Object details = mapper.readValue(productDto.getDetails().toString(), Object.class);
                    Object images = mapper.readValue(productDto.getImages().toString(), Object.class);
                    productDto.setDetails(details);
                    productDto.setImages(images);

                    ModelMapper modelMapper = new ModelMapper();
                    ProductResponseModel productResponseModel = modelMapper .map(productDto, ProductResponseModel.class);
                    productResponseModels.add(productResponseModel);
                }catch (JsonProcessingException e){
                    e.printStackTrace();
                }
            }

            if(!productDtos.isEmpty()){
                response.setMessage(message.selected("Products"));
                response.setData(productResponseModels);
                response.setStatus(HttpStatus.FOUND);
            }else {
                response.setMessage(message.hasNoRecords("Products"));
                response.setStatus(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }





    /**
     * Get new products for 12 records to show homepage
     *
     * @return - list of popular products response
     */
    @GetMapping("/popular-products")
    @ApiOperation(value = "show all popular products 12 records", response = ProductResponseModel.class)
    public ResponseEntity<BaseApiResponse<List<ProductResponseModel>>> getPopularProducts() {;

        BaseApiResponse<List<ProductResponseModel>> response = new BaseApiResponse<>();

        ObjectMapper mapper = new ObjectMapper();

        List<ProductResponseModel> productResponseModels = new ArrayList<>();

        try{
            List<ProductDto> productDtos = productServiceImp.getPopularProducts();
            for(ProductDto productDto : productDtos){
                try {
                    Object details = mapper.readValue(productDto.getDetails().toString(), Object.class);
                    Object images = mapper.readValue(productDto.getImages().toString(), Object.class);
                    productDto.setDetails(details);
                    productDto.setImages(images);

                    ModelMapper modelMapper = new ModelMapper();
                    ProductResponseModel productResponseModel = modelMapper .map(productDto, ProductResponseModel.class);
                    productResponseModels.add(productResponseModel);
                }catch (JsonProcessingException e){
                    e.printStackTrace();
                }
            }

            if(!productDtos.isEmpty()){
                response.setMessage(message.selected("Products"));
                response.setData(productResponseModels);
                response.setStatus(HttpStatus.FOUND);
            }else {
                response.setMessage(message.hasNoRecords("Products"));
                response.setStatus(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        response.setTime(new Timestamp(System.currentTimeMillis()));

        return ResponseEntity.ok(response);
    }


    /**
     * Delete a product
     *
     * @param proId - UUID of a product
     * @return - Return response message
     */
    @DeleteMapping("/products/{proId}")
    @ApiOperation(value = "delete a product", response = Void.class)
    public ResponseEntity<BaseApiNoPaginationResponse<Void>> deleteProduct(@PathVariable("proId") String proId){

        BaseApiNoPaginationResponse<Void> response = new BaseApiNoPaginationResponse<>();
        ProductDto productDto = productServiceImp.findById(proId);
        if(productDto != null){
            productServiceImp.deleteProduct(proId);
            response.setMessage(message.deleted("Product"));
            response.setStatus(HttpStatus.OK);
        }else {
            response.setMessage(message.deleteError("Shop"));
            response.setStatus(HttpStatus.BAD_REQUEST);
        }

        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }


    /**
     * Put a product
     *
     * @param proId - Id of a product UUID
     * @param productRequestModel - Product request model
     * @return - Return response message
     */
    @PutMapping("/products/{proId}")
    @ApiOperation(value = "update a product", response = ProductRequestModel.class)
    public ResponseEntity<BaseApiResponse<ProductRequestModel>> updateProduct(
            @PathVariable("proId") String proId,
            @RequestBody ProductRequestModel productRequestModel){
        ModelMapper modelMapper=new ModelMapper();
        ProductDto dto=modelMapper.map(productRequestModel,ProductDto.class);
        ProductRequestModel responeModel=modelMapper.map(productServiceImp.updateProduct(proId,dto),ProductRequestModel.class);

        BaseApiResponse<ProductRequestModel> respone=new BaseApiResponse <>();
        respone.setMessage(message.updated("Update"));
        respone.setStatus(HttpStatus.OK);
        respone.setData(responeModel);
        respone.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(respone);
    }









    /**
     * Get Products
     *
     * @param page  - Page of pagination
     * @param limit - Limit data of a pagination
     * @param totalPages - Total pages of data limited in a page
     * @param pagesToShow - Pages to show
     * @return - Return response message
     */
    @GetMapping("all-products/{userId}")
    @ApiOperation(value = "show all products by user id", response = Void.class)
    public ResponseEntity<BaseApiResponse<List<ProductsOfAUserResponse>>> getAllProductsByUserId(@PathVariable("userId") String userId,
                                                                                                 @RequestParam(value = "page" , required = false , defaultValue = "1") int page,
                                                                                                 @RequestParam(value = "limit" , required = false , defaultValue = "3") int limit,
                                                                                                 @RequestParam(value = "totalPages" , required = false , defaultValue = "3") int totalPages,
                                                                                                 @RequestParam(value = "pagesToShow" , required = false , defaultValue = "3") int pagesToShow) {

        Pagination pagination = new Pagination(page, limit,totalPages,pagesToShow);
        pagination.setPage(page);
        pagination.setLimit(limit);
        pagination.nextPage();
        pagination.previousPage();
        pagination.setTotalCount(productServiceImp.countId());
        pagination.setTotalPages(pagination.getTotalPages());

        BaseApiResponse<List<ProductsOfAUserResponse>> response = new BaseApiResponse<>();
        ModelMapper mapper = new ModelMapper();
        List<ProductsOfAUserResponse> productResponseModels = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            UserResponseModel userDto = userServiceImp.getOneUserById(userId);
            List<ProductDto> products = productServiceImp.getAllProductsByUserId(userDto.getId(),pagination);
            for (ProductDto productDto : products) {

                Object images = objectMapper.readValue(productDto.getImages().toString(), Object.class);
                productDto.setImages(images);

                ProductsOfAUserResponse productResponseModel = mapper.map(productDto, ProductsOfAUserResponse.class);
                productResponseModels.add(productResponseModel);
            }

            if (!products.isEmpty()) {
                response.setData(productResponseModels);
                response.setStatus(HttpStatus.FOUND);
                response.setMessage(message.selected("Products"));
            }else {
                response.setStatus(HttpStatus.NOT_FOUND);
                response.setMessage(message.hasNoRecords("Products"));
            }

            response.setPagination(pagination);
            System.out.println("Product = " + response);
        }catch (Exception e){
            e.printStackTrace();
        }

        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }


}
