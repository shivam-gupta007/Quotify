package com.app.quotify.feature_quote.data.data_source.remote.adapter

sealed class NetworkResult<out T> {
    data class Success<out T>(val data: T) : NetworkResult<T>()
    data class Error(val message: String) : NetworkResult<Nothing>()
    data class Exception(val exception: kotlin.Exception) : NetworkResult<Nothing>()
    object Loading : NetworkResult<Nothing>()
}
