package com.app.dailymotivation.feature_quote.domain.modal.api_response_class

data class PexelsApiResponse(
    val avg_color: String,
    val height: Int,
    val id: Int,
    val photographer: String,
    val photographer_id: Int,
    val photographer_url: String,
    val src: Src,
    val url: String,
    val width: Int
)