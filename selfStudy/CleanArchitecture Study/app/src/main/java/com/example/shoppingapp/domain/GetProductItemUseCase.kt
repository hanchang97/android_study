package com.example.shoppingapp.domain

import com.example.shoppingapp.data.entity.product.ProductEntity
import com.example.shoppingapp.data.repository.ProductRepository

internal class GetProductItemUseCase(
    private val productRepository: ProductRepository   // repository 주입
): UseCase {
    suspend operator fun invoke(productId: Long): ProductEntity?{
        return productRepository.getProductItem(productId)
    }
}