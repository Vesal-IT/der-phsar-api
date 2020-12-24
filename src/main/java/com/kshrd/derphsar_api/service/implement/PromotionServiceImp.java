package com.kshrd.derphsar_api.service.implement;

import com.kshrd.derphsar_api.repository.PromotionRepository;
import com.kshrd.derphsar_api.repository.dto.ProductDto;
import com.kshrd.derphsar_api.repository.dto.PromotionDto;
import com.kshrd.derphsar_api.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PromotionServiceImp implements PromotionService {
    private PromotionRepository promotionRepository;

    @Autowired
    public void setPromotionRepository(PromotionRepository promotionRepository) {
        this.promotionRepository = promotionRepository;
    }

    //select all promotions
    @Override
    public List<PromotionDto> getPromotions() {
//        if (shopId == 0)
            return promotionRepository.getPromotions();
//        else
//            return promotionRepository.findPromotionByShopId(shopId);
    }

    @Override
    public List<PromotionDto> getPromotionsByShopId(int shopId) {
        return promotionRepository.findPromotionByShopId(shopId);
    }


    //delete a promotion
    @Override
    public void deletePromotion(String id) {
        promotionRepository.deletePromotion(id);
    }

    //update a promotion
    @Override
    public PromotionDto updatePromotion(String id, PromotionDto promotionDto) {
        boolean isUpdated = promotionRepository.updatePromotion(id,promotionDto);
        if(isUpdated){
            return promotionDto;
        }
        return null;
    }

    @Override
    public PromotionDto updateIsApply(String id, PromotionDto promotionDto) {
        boolean isUpdated = promotionRepository.updateIsApply(id,promotionDto);
        if(isUpdated){
            return promotionDto;
        }
        return null;
    }

    @Override
    public ProductDto applyPromotion(String id, ProductDto productDto) {
        boolean isUpdated = promotionRepository.applyPromotion(id,productDto);
        if(isUpdated){
            return productDto;
        }
        return null;
    }

    //create a promotion
    @Override
    public PromotionDto createPromotion(PromotionDto promotionDto) {
        //System.out.println("promotion"+promotionDto);
        boolean isInserted = promotionRepository.createPromotion(promotionDto);
        if(isInserted)
            return promotionDto;
        else
            return null;
    }

    //find a promotion by id
    @Override
    public PromotionDto findById(String promoId) {
        return  promotionRepository.findById(promoId);
    }
}
