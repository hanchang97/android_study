package com.example.shoppingapp.domain

import com.example.shoppingapp.data.entity.product.ProductEntity
import com.example.shoppingapp.data.repository.ProductRepository

internal class GetProductListUseCase(
    private val productRepository: ProductRepository   // repository 주입
): UseCase {
    suspend operator fun invoke(): List<ProductEntity>{
        return productRepository.getProductList()
    }
}