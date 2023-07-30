package com.app.quotify.feature_quote.domain.use_cases

import com.app.quotify.feature_quote.data.repository.QuoteRepositoryImpl
import com.app.quotify.feature_quote.domain.model.Quote
import kotlinx.coroutines.flow.Flow

class GetQuotesFromDb(
    private val quoteRepositoryImpl: QuoteRepositoryImpl
) {
    operator fun invoke(): Flow<List<Quote>> {
        return quoteRepositoryImpl.getQuotesFromDb()
    }
}