@copyright DerPhsar
## Group01, class SR

describe what functionality works

ClassName : "ProductRestController"
MethodsNames :
        1. createProduct() : used for insert new product to DB
            /api/v1/products

        2. getProducts() : used for select all products and filter all product in a shop by shopId
            /api/v1/products
            /api/v1/products?shopId={id}

        3. deleteProduct(): used for delete a product by pro_id from DB
            /api/v1/products/{id}

        4. updateProduct() : used for update a product by pro_id in DB
            /api/v1/products/{id}



ClassName : "ShopRestController"
MethodNames :
        1. createShop() : used for insert new shop to DB
            /api/v1/shops

        2. getShops() : used for select all shops
            /api/v1/shops

        3. deleteShop(): used for delete a shop by shopId from DB
            /api/v1/shops/{id}

        4. updateShop() : used for update a shop by shopId in DB
            /api/v1/shops/{id}


ClassName : "PromotionRestController"
MethodNames :
        1. createPromotion() : used for insert new promotion to DB
            /api/v1/promotions

        2. getPromotions() : used for select all promotions and filter all promotions in a shop by shopId
            /api/v1/promotions
            /api/v1/promotions?shopId={id}

        3. deletePromotion(): used for delete a promotion by promotion Id from DB
            /api/v1/promotions/{id}

        4. updatePromotion() : used for update a promotion by promotionId in DB
            /api/v1/promotions/{id}



ClassName : "WishListRestController"
MethodNames :
        1. createWishList() : used for insert new wishlist to DB
            /api/v1/wishlists

        2. getWishLists() : used for select all wishlists
            /api/v1/wishlists

        3. deleteWishList(): used for delete a wishlists by wishlists Id from DB
            /api/v1/wishlists/{id}



ClassName : "CategoryRestController"
MethodNames :
        1. getCategories() : used for insert new categories from DB
            /api/v1/categories
