package com.app.quotify.feature_quote.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class QuotableQuoteList(
    val results: List<QuotableQuote>
)
