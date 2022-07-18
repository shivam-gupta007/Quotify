package com.app.quotify.feature_quote.domain.modal

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class QuotableQuote(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("_id")
    val id: String,
    val author: String,
    val content: String,
)