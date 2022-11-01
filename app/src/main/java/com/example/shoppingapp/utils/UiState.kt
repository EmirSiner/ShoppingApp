package com.example.shoppingapp.utils

import retrofit2.Response

sealed class UiState<out T> {
    object Loading : UiState<Nothing>()
    data class Success<T>(val data: T) : UiState<T>()
    data class Failure(val messageRes: Int) : UiState<Nothing>()
    data class Error(val message: String) : UiState<Nothing>()
}

suspend fun <T> apiCall(errorMessageRes: Int, block: suspend () -> Response<T>): UiState<T> {
    return try {
        val response = block()
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                UiState.Success(body)
            } else {
                UiState.Failure(errorMessageRes)
            }
        } else {
            UiState.Failure(errorMessageRes)
        }
    } catch (ex: Exception) {
        UiState.Error(ex.message.orEmpty())
    }
}