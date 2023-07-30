package com.app.quotify.feature_quote.data.data_source.remote.adapter

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/*
* [NetworkResultCall] class has one argument which is the Retrofit Call class instance of the type of T.
* */
class NetworkResultCall<T : Any>(
    private val proxy: Call<T>
) : Call<NetworkResult<T>> {

    override fun clone(): Call<NetworkResult<T>> = NetworkResultCall(proxy.clone())

    override fun execute(): Response<NetworkResult<T>> = throw NotImplementedError()

    override fun enqueue(callback: Callback<NetworkResult<T>>) {
        proxy.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                CoroutineScope(Dispatchers.IO).launch{
                    val networkResult = handleApiResponse(response)
                    callback.onResponse(this@NetworkResultCall, Response.success(networkResult))
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                callback.onResponse(
                    this@NetworkResultCall,
                    Response.success(NetworkResult.Error("An unexpected error occurred"))
                )
            }

        })
    }

    override fun isExecuted(): Boolean = proxy.isExecuted

    override fun cancel() {
        proxy.cancel()
    }

    override fun isCanceled(): Boolean = proxy.isExecuted

    override fun request(): Request = proxy.request()

    override fun timeout(): Timeout = proxy.timeout()

}

fun <T> handleApiResponse(okHttpResponse: Response<T>): NetworkResult<T> {
    return try {
        if (okHttpResponse.isSuccessful) {
            val data = okHttpResponse.body()!!
            NetworkResult.Success(data)
        } else {
            NetworkResult.Error("An unexpected error occurred.")
        }
    } catch (exception: Exception) {
        when (exception) {
            is NoInternetException  -> NetworkResult.Error("No internet connection")
            is ServerException -> NetworkResult.Error("Server error occurred")
            else -> NetworkResult.Exception(exception)
        }
    }
}