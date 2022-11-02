package com.example.shoppingapp.data.mapper

import com.example.shoppingapp.data.model.product.ProductDTO

interface EntityMapper<Entity, DomainModel> {
    fun mapFromEntity(entity: Entity): ProductDTO
    fun mapToEntity(domainModel: ProductDTO): Entity
}