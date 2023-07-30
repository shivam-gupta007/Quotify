package com.app.quotify.feature_quote.domain.model

import androidx.room.Embedded
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
data class PexelsImage(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    @Embedded
    val src: Src
)