package com.app.quotify.feature_quote.data.data_source.local

import androidx.room.*
import com.app.quotify.feature_quote.domain.modal.Quote
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteQuoteDao {

    @Query("SELECT * FROM quote_table")
    fun getQuotes(): Flow<List<Quote>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuote(quote: Quote)

    @Delete
    suspend fun deleteQuote(quote: Quote)
}