package com.kshrd.derphsar_api.service;

import com.kshrd.derphsar_api.repository.dto.ProductDto;
import com.kshrd.derphsar_api.repository.dto.PromotionDto;

import java.util.List;

public interface PromotionService {

    //get all promotion
    List<PromotionDto> getPromotions();


    //get all promotion by shopId
    List<PromotionDto> getPromotionsByShopId(int shopId);

    //delete a promotion
    void deletePromotion(String id);

    //update a promotion
    PromotionDto updatePromotion(String id, PromotionDto promotionDto);

    PromotionDto updateIsApply(String id, PromotionDto promotionDto);

    ProductDto applyPromotion(String id, ProductDto productDto);

    //create a promotion
    PromotionDto createPromotion(PromotionDto promotionDto);

    //find by id
    PromotionDto findById(String promoId);
}
