package com.app.quotify.feature_quote.presentation.quotes.components

import com.app.quotify.feature_quote.domain.model.Quote

data class QuoteState(
    var quotesListFromDb: List<Quote> = emptyList(),
    var quotesListFromApi: List<Quote> = emptyList(),
    var isLoading: Boolean = true,
    var error: String = ""
)

