package com.app.quotify.feature_quote.domain.repository

import com.app.quotify.feature_quote.data.data_source.remote.adapter.NetworkResult
import com.app.quotify.feature_quote.domain.model.PexelsImageList
import com.app.quotify.feature_quote.domain.model.QuotableQuoteList
import com.app.quotify.feature_quote.domain.model.Quote
import kotlinx.coroutines.flow.Flow

interface QuoteRepository {

    fun getQuotesFromDb(): Flow<List<Quote>>

    suspend fun insertQuote(quote: Quote)

    suspend fun deleteQuote(quote: Quote)

    suspend fun getQuotesText(): QuotableQuoteList

    suspend fun getImages(): PexelsImageList
}