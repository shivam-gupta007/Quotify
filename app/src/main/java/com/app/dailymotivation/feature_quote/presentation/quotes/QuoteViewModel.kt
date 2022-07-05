package com.app.dailymotivation.feature_quote.presentation.quotes

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.dailymotivation.feature_quote.domain.modal.api_response_class.PexelsApiResponse
import com.app.dailymotivation.feature_quote.domain.modal.api_response_class.QuoteApiResponse
import com.app.dailymotivation.feature_quote.domain.repository.QuoteRepositoryImpl
import kotlinx.coroutines.*

class QuoteViewModel(private val quoteRepositoryImpl: QuoteRepositoryImpl) : ViewModel() {

    private val imageListLiveData: MutableLiveData<List<PexelsApiResponse>> = MutableLiveData()
    private val quoteListLiveData: MutableLiveData<List<QuoteApiResponse>> = MutableLiveData()
    private var job: Job? = null
    private val exceptionHandler =
        CoroutineExceptionHandler { _, throwable -> ("Exception Handled: ${throwable.localizedMessage}") }

    fun getImages(): MutableLiveData<List<PexelsApiResponse>> {
        job = viewModelScope.launch(Dispatchers.IO + exceptionHandler) {

            val response = quoteRepositoryImpl.getImages()

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    imageListLiveData.postValue(response.body()?.photos)
                    Log.d("Success", "Image response $response")
                } else {
                    Log.d("Error", "Image response failure ${response.code()}")
                }

            }
        }

        return imageListLiveData
    }

    fun getQuote(): MutableLiveData<List<QuoteApiResponse>> {

        job = viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            val response = quoteRepositoryImpl.getQuote()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    quoteListLiveData.postValue(response.body()?.results)
                    Log.d("Success", "Quote response success RESPONSE: $response")
                } else {
                    Log.d("Error", "Quote response failure")
                }
            }
        }

        return quoteListLiveData
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}