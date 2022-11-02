package com.example.shoppingapp.data.mapper

import android.os.Parcel
import android.os.Parcelable
import com.example.shoppingapp.data.local.entity.ProductEntity
import com.example.shoppingapp.data.model.product.Product
import com.example.shoppingapp.data.model.product.ProductDTO

class ProductMapper() : EntityMapper<ProductEntity, Product>, Parcelable{
    constructor(parcel: Parcel) : this() {
    }

    override fun mapFromEntity(entity: ProductEntity): ProductDTO {
        return ProductDTO(
            id = entity.id,
            title = entity.title,
            price = entity.price,
            category = entity.category,
            description = entity.description,
            image = entity.image,

        )
    }

    override fun mapToEntity(domainModel: ProductDTO): ProductEntity {
        return ProductEntity(
            id=domainModel.id,
            title = domainModel.title,
            price = domainModel.price,
            category = domainModel.category,
            description = domainModel.description,
            image = domainModel.image
        )
    }

    fun fromEntityList(initial: List<ProductEntity>): List<ProductDTO> {
        return initial.map { mapFromEntity(it) }
    }

    fun toEntityList(initial: List<ProductDTO>): List<ProductEntity> {
        return initial.map { mapToEntity(it) }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ProductMapper> {
        override fun createFromParcel(parcel: Parcel): ProductMapper {
            return ProductMapper(parcel)
        }

        override fun newArray(size: Int): Array<ProductMapper?> {
            return arrayOfNulls(size)
        }
    }
}