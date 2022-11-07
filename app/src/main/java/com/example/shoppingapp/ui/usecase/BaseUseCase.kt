package com.example.shoppingapp.ui.usecase

import kotlinx.coroutines.flow.Flow

interface BaseUseCase<in P, R : Any?> {
    fun invoke(params: P): Flow<R>
}