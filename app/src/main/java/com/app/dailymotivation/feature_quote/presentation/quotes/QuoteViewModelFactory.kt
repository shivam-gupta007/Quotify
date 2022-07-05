package com.app.dailymotivation.feature_quote.presentation.quotes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.dailymotivation.feature_quote.domain.repository.QuoteRepositoryImpl

class QuoteViewModelFactory(private val quoteRepositoryImpl: QuoteRepositoryImpl) :
    ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuoteViewModel::class.java)) {
            return QuoteViewModel(quoteRepositoryImpl) as T
        }
        throw IllegalStateException("ViewModel not found")
    }
}