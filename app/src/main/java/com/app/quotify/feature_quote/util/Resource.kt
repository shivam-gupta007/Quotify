package com.app.quotify.feature_quote.util

sealed class Resource<out T> {
    data class Success<out T>(val data: T) : Resource<T>()
    data class Error(val message: String) : Resource<Nothing>()
    data class Exception(val exception: kotlin.Exception) : Resource<Nothing>()
    object Loading : Resource<Nothing>()
}
