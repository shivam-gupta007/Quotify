package com.app.quotify.feature_quote.domain.repository

import com.app.quotify.feature_quote.domain.modal.PexelsImageList
import com.app.quotify.feature_quote.domain.modal.QuotableQuoteList
import com.app.quotify.feature_quote.domain.modal.Quote
import kotlinx.coroutines.flow.Flow

interface QuoteRepository {

    fun getQuotesFromDb(): Flow<List<Quote>>

    suspend fun insertQuote(quote: Quote)

    suspend fun deleteQuote(quote: Quote)

    suspend fun getQuotesText(): QuotableQuoteList

    suspend fun getImages(): PexelsImageList
}