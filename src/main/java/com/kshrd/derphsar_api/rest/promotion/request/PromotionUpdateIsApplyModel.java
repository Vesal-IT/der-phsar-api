package com.kshrd.derphsar_api.rest.promotion.request;

public class PromotionUpdateIsApplyModel {
    private boolean isApply;

    public PromotionUpdateIsApplyModel(){}

    public PromotionUpdateIsApplyModel(boolean isApply) {
        this.isApply = isApply;
    }

    public boolean isApply() {
        return isApply;
    }

    public void setApply(boolean apply) {
        isApply = apply;
    }

    @Override
    public String toString() {
        return "PromotionUpdateIsApplyModel{" +
                "isApply=" + isApply +
                '}';
    }
}
