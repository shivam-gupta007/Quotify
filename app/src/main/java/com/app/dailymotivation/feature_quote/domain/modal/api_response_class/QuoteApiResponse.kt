package com.app.dailymotivation.feature_quote.domain.modal.api_response_class

data class QuoteApiResponse(
    val _id: String,
    val author: String,
    val authorSlug: String,
    val content: String,
    val dateAdded: String,
    val dateModified: String,
    val length: Int,
    val tags: List<String>
)