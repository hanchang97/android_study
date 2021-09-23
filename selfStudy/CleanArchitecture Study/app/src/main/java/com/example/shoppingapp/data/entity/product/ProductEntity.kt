package com.example.shoppingapp.data.entity.product

import java.util.*

/*
*  넘어오는 데이터 형식
*   {
*   "id": 1,
*   "createdAt": "2021-04-23T19:44:11.102Z",
*   "product_name": "~",
*   "prduct_price": "~"
*   "product_image": "http//~",
*   "product": "Bike",
*   "product_introduction_image": "~"
* }
*
* */


data class ProductEntity(
    val id: Long,
    val createdAt: Date,
    val productName: String,
    val productPrice: Int,
    val productImage: String,
    val productType: String,
    val productIntroductionImage: String

    )
