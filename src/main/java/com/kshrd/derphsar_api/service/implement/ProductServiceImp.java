package com.kshrd.derphsar_api.service.implement;

import com.kshrd.derphsar_api.page.Pagination;
import com.kshrd.derphsar_api.repository.ProductRepository;
import com.kshrd.derphsar_api.repository.dto.CategoryDto;
import com.kshrd.derphsar_api.repository.dto.ProductDto;
import com.kshrd.derphsar_api.repository.dto.ShopDto;
import com.kshrd.derphsar_api.repository.dto.WishListDto;
import com.kshrd.derphsar_api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImp implements ProductService {
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

//    // get all products
//    @Override
//    public List<ProductDto> getProducts(Pagination pagination) {
//        //if(shopId == 0)
//        return productRepository.getProducts(pagination);
//        //else
//        //return productRepository.findProductByShopId(shopId, pagination);
//    }
//
////    @Override
////    public List<ProductDto> getProducts(int shopId, Pagination pagination) {
////        return null;
////    }

    @Override
    public List<ProductDto> getProducts(Pagination pagination) {
        //if(shopId == 0)
            return productRepository.getProducts(pagination);
//        else
//            return productRepository.findProductByShopId(shopId,pagination);
    }

    @Override
    public List<ProductDto> findProductByShopId(int shopId, Pagination pagination) {
        return productRepository.findProductByShopId(shopId, pagination);
    }

    //insert a product
    @Override
    public ProductDto insert(ProductDto productDto) {
        System.out.println("Product"+productDto);
        boolean isInserted = productRepository.insert(productDto);
        if(isInserted)
            return productDto;
        else
            return null;
    }

    //delete a product
    @Override
    public void deleteProduct(String id) {
        productRepository.deleteProduct(id);
    }

    //update a product
    @Override
    public ProductDto updateProduct(String id, ProductDto productDto) {
        boolean isUpdated = productRepository.updateProduct(id,productDto);
        if(isUpdated){
            return productDto;
        }
        return null;
    }

    //find a product by id
    @Override
    public ProductDto findById(String proId) {
        return  productRepository.findById(proId);
    }

    @Override
    public int countId() {
        return productRepository.countId();
    }

    @Override
    public List<ProductDto> getNewProducts() {
        return productRepository.getNewProducts();
    }

    @Override
    public List<ProductDto> getNewProductsByShopId(int shopId) {
        return productRepository.getNewProductsByShopId(shopId);
    }

    @Override
    public List<ProductDto> getPopularProducts() {
        return productRepository.getPopularProducts();
    }


    @Override
    public List<ProductDto> getAllProductsByUserId(int userId, Pagination pagination) {
        return productRepository.getAllProductsByUserId(userId, pagination);
    }

    @Override
    public List<ProductDto> getRelatedProducts(int categoryId) {
        return productRepository.getRelatedProducts(categoryId);
    }

    @Override
    public ShopDto getShopByShopId(String shopId) {
        return productRepository.getShopByShopId(shopId);
    }

    @Override
    public CategoryDto getCategoryByCatId(String catId) {
        return productRepository.getCategoryByCatId(catId);
    }


    public void updateViewCount(String proId){
        int viewCount = productRepository.getViewCount(proId);
        productRepository.updateViewCount(proId,viewCount+1);
    }

}
