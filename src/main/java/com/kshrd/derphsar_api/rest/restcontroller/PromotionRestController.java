package com.kshrd.derphsar_api.rest.restcontroller;


import com.kshrd.derphsar_api.repository.dto.ProductDto;
import com.kshrd.derphsar_api.repository.dto.PromotionDto;
import com.kshrd.derphsar_api.repository.dto.ShopDto;
import com.kshrd.derphsar_api.rest.BaseApiResponse;
import com.kshrd.derphsar_api.rest.message.MessageProperties;
import com.kshrd.derphsar_api.rest.product.request.ProductUpdateDiscountModel;
import com.kshrd.derphsar_api.rest.promotion.request.PromotionRequestModel;
import com.kshrd.derphsar_api.rest.promotion.request.PromotionUpdateIsApplyModel;
import com.kshrd.derphsar_api.rest.promotion.response.PromotionCreateFirstResponse;
import com.kshrd.derphsar_api.rest.promotion.response.PromotionResponseModel;
import com.kshrd.derphsar_api.rest.shop.response.ShopCreateFirstResponse;
import com.kshrd.derphsar_api.rest.utils.BaseApiNoPaginationResponse;
import com.kshrd.derphsar_api.service.implement.PromotionServiceImp;
import com.kshrd.derphsar_api.service.implement.ShopServiceImp;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static net.bytebuddy.matcher.ElementMatchers.is;


//@CrossOrigin(origins = "*", maxAge = 3600)
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1")
public class PromotionRestController {

    private PromotionServiceImp promotionServiceImp;
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
    public void setPromotionServiceImp(PromotionServiceImp promotionServiceImp) {
        this.promotionServiceImp = promotionServiceImp;
    }





    /**
     * Get all promotions
     *
     * @return - Return response message
     */
    @GetMapping("/promotions")
    @ApiOperation(value = "show all promotions", response = PromotionResponseModel.class)
    public ResponseEntity<BaseApiNoPaginationResponse<List<PromotionResponseModel>>> getPromotions() {

        ModelMapper mapper = new ModelMapper();
        BaseApiNoPaginationResponse<List<PromotionResponseModel>> response = new BaseApiNoPaginationResponse<>();
        List<PromotionResponseModel> promotions = new ArrayList<>();

        try {
            List<PromotionDto> promotion = promotionServiceImp.getPromotions();
            for (PromotionDto promotionDto : promotion) {
                promotions.add(mapper.map(promotionDto, PromotionResponseModel.class));
            }


            if (!promotion.isEmpty()) {
                response.setMessage(message.selected("Promotions"));
                response.setData(promotions);
                response.setStatus(HttpStatus.FOUND);
            }else {
                response.setMessage(message.hasNoRecords("Promotions"));
                response.setStatus(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }



    /**
     * Get promotions
     *
     * @param shopId - Id of a shop
     * @return - Return response message
     */
    @GetMapping("/promotions-in-shop")
    @ApiOperation(value = "show all promotions by a shop id", response = PromotionResponseModel.class)
    public ResponseEntity<BaseApiNoPaginationResponse<List<PromotionResponseModel>>> getPromotionsInShop(
            @RequestParam(value="shopId",required = false,defaultValue = "") String shopId) {

        ModelMapper mapper = new ModelMapper();
        BaseApiNoPaginationResponse<List<PromotionResponseModel>> response = new BaseApiNoPaginationResponse<>();
        List<PromotionResponseModel> promotions = new ArrayList<>();

        try {
            ShopDto shopDto = shopServiceImp.findById(shopId);
            List<PromotionDto> promotion = promotionServiceImp.getPromotionsByShopId(shopDto.getId());
            for (PromotionDto promotionDto : promotion) {
                promotions.add(mapper.map(promotionDto, PromotionResponseModel.class));
            }


            if (!promotion.isEmpty()) {
                response.setMessage(message.selected("Promotions"));
                response.setData(promotions);
                response.setStatus(HttpStatus.FOUND);
            }else {
                response.setMessage(message.hasNoRecords("Promotions"));
                response.setStatus(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }


    /**
     * Delete a promotion
     *
     * @param promoId - Id of a promotion
     * @return - Return response message
     */
    @DeleteMapping("/promotions/{promoId}")
    @ApiOperation(value = "delete a promotion by promotion id", response = Void.class)
    public ResponseEntity<BaseApiNoPaginationResponse<Void>> deletePromotion(@PathVariable("promoId") String promoId) {

        BaseApiNoPaginationResponse<Void> response = new BaseApiNoPaginationResponse<>();
        PromotionDto promotionDto = promotionServiceImp.findById(promoId);

        if(promotionDto != null){
            promotionServiceImp.deletePromotion(promoId);
            response.setMessage(message.deleted("Promotion"));
            response.setStatus(HttpStatus.OK);
        }else {
            response.setMessage(message.deleteError("Promotion"));
            response.setStatus(HttpStatus.BAD_REQUEST);
        }
        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }


    /**
     * Put a promotion
     *
     * @param promoId - Id of a promotion
     * @param promotionRequestModel - Promotion request model
     * @return - Return response message
     */
    @PutMapping("/promotions/{promoId}")
    @ApiOperation(value = "update a promotion by a promotion id", response = PromotionResponseModel.class)
    public ResponseEntity<BaseApiResponse<PromotionResponseModel>> updatePromotion(@PathVariable("promoId") String promoId,
                                                                                  @RequestBody PromotionRequestModel promotionRequestModel) {

        ModelMapper modelMapper = new ModelMapper();
        BaseApiResponse<PromotionResponseModel> response = new BaseApiResponse<>();

        try {
            PromotionDto promotionDto = modelMapper.map(promotionRequestModel, PromotionDto.class);
            PromotionResponseModel responseModel = modelMapper.map(promotionServiceImp.updatePromotion(promoId, promotionDto), PromotionResponseModel.class);

            response.setMessage(message.updated("Promotion"));
            response.setStatus(HttpStatus.OK);
            response.setData(responseModel);
        }catch (Exception e){
            e.printStackTrace();
        }

        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }




    /**
     * Put a promotion
     *
     * @param promoId - Id of a promotion
     * @param promotionUpdateIsApplyModel - Promotion request model
     * @return - Return response message
     */
    @PatchMapping("/promotions/{promoId}")
    @ApiOperation(value = "update isApply", response = PromotionUpdateIsApplyModel.class)
    public ResponseEntity<BaseApiNoPaginationResponse<PromotionUpdateIsApplyModel>> updateIsApply(@PathVariable("promoId") String promoId,
                                                                                   @RequestBody PromotionUpdateIsApplyModel promotionUpdateIsApplyModel) {

        ModelMapper modelMapper = new ModelMapper();
        BaseApiNoPaginationResponse<PromotionUpdateIsApplyModel> response = new BaseApiNoPaginationResponse<>();

        try {
            PromotionDto promotionDto = modelMapper.map(promotionUpdateIsApplyModel, PromotionDto.class);

            PromotionUpdateIsApplyModel responseModel = modelMapper.map(promotionServiceImp.updateIsApply(promoId, promotionDto), PromotionUpdateIsApplyModel.class);

            response.setMessage(message.updated("Promotion"));
            response.setStatus(HttpStatus.OK);
            response.setData(responseModel);
        }catch (Exception e){
            e.printStackTrace();
        }

        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }


    /**
     * Put a promotion
     *
     * @param proId - Id of a promotion
     * @param productUpdateDiscountModel - Promotion request model
     * @return - Return response message
     */
    @PatchMapping("/apply-promotion/{proId}")
    @ApiOperation(value = "apply promotion", response = ProductUpdateDiscountModel.class)
    public ResponseEntity<BaseApiNoPaginationResponse<ProductUpdateDiscountModel>> applyPromotion(@PathVariable("proId") String proId,
                                                                                                  @RequestBody ProductUpdateDiscountModel productUpdateDiscountModel) {

        ModelMapper modelMapper = new ModelMapper();
        BaseApiNoPaginationResponse<ProductUpdateDiscountModel> response = new BaseApiNoPaginationResponse<>();

        try {
            ProductDto productDto = modelMapper.map(productUpdateDiscountModel, ProductDto.class);

            ProductUpdateDiscountModel responseModel = modelMapper.map(promotionServiceImp.applyPromotion(proId, productDto), ProductUpdateDiscountModel.class);

            response.setMessage(message.updated("Product"));
            response.setStatus(HttpStatus.OK);
            response.setData(responseModel);
        }catch (Exception e){
            e.printStackTrace();
        }

        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }






    /**
     * Post a promotion
     *
     * @param promotionRequestModel - Promotion request model
     * @return - Return response message
     */
    @PostMapping("/promotions")
    @ApiOperation(value = "create a promotion", response = PromotionCreateFirstResponse.class)
    public ResponseEntity<BaseApiNoPaginationResponse<PromotionCreateFirstResponse>> createPromotion(@RequestBody PromotionRequestModel promotionRequestModel) throws ParseException {

        ModelMapper mapper = new ModelMapper();
        UUID uuid = UUID.randomUUID();
        BaseApiNoPaginationResponse<PromotionCreateFirstResponse> response = new BaseApiNoPaginationResponse<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        PromotionDto promotionDto = mapper.map(promotionRequestModel,PromotionDto.class);


        if(!promotionRequestModel.getTitle().isEmpty() && promotionRequestModel.getStartRank() !=0 && promotionRequestModel.getEndRank() !=0 && promotionRequestModel.getStartDate() != null && promotionRequestModel.getEndDate() !=null && promotionRequestModel.getShop_id() !=0){
            if(promotionRequestModel.getStartRank() > promotionRequestModel.getEndRank() || promotionRequestModel.getStartDate().after(promotionRequestModel.getEndDate())){
                response.setMessage(message.insertError("Promotion"));
                response.setStatus(HttpStatus.BAD_REQUEST);
            }else {
                promotionDto.setPromoId("DP"+uuid.toString().substring(0,10));
                promotionDto.setStatus(true);
                PromotionDto promotionDto1 = promotionServiceImp.createPromotion(promotionDto);

                PromotionCreateFirstResponse promotionCreateFirstResponse = mapper.map(promotionDto1, PromotionCreateFirstResponse.class);
                response.setMessage(message.inserted("Promotion"));
                response.setData(promotionCreateFirstResponse);
                response.setStatus(HttpStatus.CREATED);
            }

        }else {
            response.setMessage(message.insertError("Promotion"));
            response.setStatus(HttpStatus.BAD_REQUEST);
        }
        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }


    /**
     * Get a promotion
     *
     * @param promoId - Id of a promotion
     * @return - Return response message
     */
    @GetMapping("/promotions/{promoId}")
    @ApiOperation(value = "get a promotion by a promotion id", response = PromotionResponseModel.class)
    public ResponseEntity<BaseApiNoPaginationResponse<List<PromotionResponseModel>>> findById(@PathVariable("promoId") String promoId){

        ModelMapper mapper = new ModelMapper();
        BaseApiNoPaginationResponse<List<PromotionResponseModel>> response =
                new BaseApiNoPaginationResponse<>();
        List<PromotionResponseModel> promotionRequestModels = new ArrayList<>();

        PromotionDto promotionDto = promotionServiceImp.findById(promoId);
        if(promotionDto != null){
            promotionRequestModels.add(mapper.map(promotionDto, PromotionResponseModel.class));

            response.setMessage(message.selectedOne("Promotion"));
            response.setData(promotionRequestModels);
            response.setStatus(HttpStatus.FOUND);
        }else {
            response.setMessage(message.hasNoRecord("Promotion"));
            response.setStatus(HttpStatus.NOT_FOUND);
        }

        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }
}