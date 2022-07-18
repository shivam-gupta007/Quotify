package com.app.quotify.feature_quote.data.data_source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.quotify.feature_quote.domain.modal.Quote

@Database(
    entities = [Quote::class],
    version = 1,
    exportSchema = false,
)

abstract class QuoteDatabase : RoomDatabase() {

    abstract fun getFavoriteQuoteDao(): FavoriteQuoteDao
}