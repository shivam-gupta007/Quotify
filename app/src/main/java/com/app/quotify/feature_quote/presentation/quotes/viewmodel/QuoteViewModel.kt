package com.app.quotify.feature_quote.presentation.quotes.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.quotify.feature_quote.domain.use_cases.QuoteUseCases
import com.app.quotify.feature_quote.presentation.quotes.components.QuoteEvent
import com.app.quotify.feature_quote.presentation.quotes.components.QuoteState
import com.app.quotify.feature_quote.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuoteViewModel @Inject constructor(
    private val quoteUseCases: QuoteUseCases
) : ViewModel() {

    private var job: Job? = null
    //private val exceptionHandler = CoroutineExceptionHandler { _, throwable -> ("Exception Handled: ${throwable.localizedMessage}") }

    private val _state = mutableStateOf(QuoteState())
    val state get() = _state


    fun onEvent(event: QuoteEvent) {
        when (event) {
            is QuoteEvent.GetQuotesFromApi -> {
                getQuotesFromApi()
            }

            is QuoteEvent.GetQuotesFromDb -> {
                getQuotesFromDb()
            }

            is QuoteEvent.InsertQuote -> {
                job = viewModelScope.launch(Dispatchers.IO) {
                    quoteUseCases.addQuoteToFav.invoke(quote = event.quote)
                }
            }

            is QuoteEvent.DeleteQuote -> {
                job = viewModelScope.launch(Dispatchers.IO) {
                    quoteUseCases.removeQuoteFromFav.invoke(quote = event.quote)
                }
            }
        }
    }

    private fun getQuotesFromApi() {
        quoteUseCases.getQuotesFromApi().onEach {
            when(it){
                is Resource.Success -> {
                    _state.value = _state.value.copy(
                        quotesListFromApi = it.data ?: emptyList(),
                        isLoading = false
                    )
                    Log.d("ResourceResponse","Success")
                }

                is Resource.Error -> {
                    _state.value = _state.value.copy(
                        error = it.message?: "An unexpected error occurred"
                    )
                    Log.d("ResourceResponse","Error")
                }

                is Resource.Loading -> {
                    _state.value = _state.value.copy(
                        isLoading = true
                    )
                    Log.d("ResourceResponse","Loading")
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getQuotesFromDb() {
        quoteUseCases.getQuotesFromDb().onEach {
            _state.value = _state.value.copy(
                quotesListFromDb = it
            )
        }.launchIn(viewModelScope)
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}