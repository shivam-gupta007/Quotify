package com.app.quotify.feature_quote.domain.use_cases

data class QuoteUseCases(
    val addQuoteToFav: AddQuoteToFav,
    val removeQuoteFromFav: RemoveQuoteFromFav,
    val getQuotesFromApi: GetQuotesFromApi,
    val getQuotesFromDb: GetQuotesFromDb
)