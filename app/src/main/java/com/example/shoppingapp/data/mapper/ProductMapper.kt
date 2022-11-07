package com.example.shoppingapp.data.mapper

import com.example.shoppingapp.data.local.entity.ProductEntity
import com.example.shoppingapp.data.model.product.Product
import javax.inject.Inject

class ProductMapper @Inject constructor() : EntityMapper<List<ProductEntity>, List<Product>> {


    override fun fromEntity(entity: List<ProductEntity>): List<Product> {
        return entity.map {
            Product(
                id = it.id,
                title = it.title,
                price = it.price,
                category = it.category,
                description = it.description,
                image = it.image,
            )
        }
    }

    override fun toEntity(domainModel: List<Product>): List<ProductEntity> {
        return domainModel.map {
            ProductEntity(
                id = it.id,
                title = it.title,
                price = it.price,
                category = it.category,
                description = it.description,
                image = it.image
            )
        }
    }
}