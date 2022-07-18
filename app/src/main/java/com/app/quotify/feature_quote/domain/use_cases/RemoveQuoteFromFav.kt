package com.app.quotify.feature_quote.domain.use_cases

import com.app.quotify.feature_quote.data.repository.QuoteRepositoryImpl
import com.app.quotify.feature_quote.domain.modal.Quote

class RemoveQuoteFromFav(
    private val quoteRepositoryImpl: QuoteRepositoryImpl
) {
    suspend operator fun invoke(quote: Quote){
        quoteRepositoryImpl.deleteQuote(quote)
    }
}