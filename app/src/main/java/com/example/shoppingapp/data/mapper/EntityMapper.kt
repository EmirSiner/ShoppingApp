package com.example.shoppingapp.data.mapper

interface EntityMapper<E, D> {
    fun fromEntity(entity: E): D
    fun toEntity(domainModel: D): E
}
