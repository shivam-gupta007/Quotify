package com.app.quotify.feature_quote.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.app.quotify.feature_quote.util.Constants.QUOTE_TABLE

@Entity(tableName = QUOTE_TABLE)
data class Quote(
    @PrimaryKey(autoGenerate = false)
    val quoteId: String,
    val quoteContent: String,
    val quoteAuthor: String,
    val quoteImgUrl: String
)

class InvalidQuoteException(message: String): Exception(message)