package com.app.quotify.feature_quote.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Src(
    val landscape: String,
    val large: String,
    val large2x: String,
    val medium: String,
    val original: String,
    val portrait: String,
    val small: String,
    val tiny: String
)