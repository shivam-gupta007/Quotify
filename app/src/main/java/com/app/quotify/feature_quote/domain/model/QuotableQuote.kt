package com.app.quotify.feature_quote.domain.model

import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QuotableQuote(
    @PrimaryKey(autoGenerate = false)
    @SerialName("_id")
    val id: String,
    val author: String,
    val content: String,
)