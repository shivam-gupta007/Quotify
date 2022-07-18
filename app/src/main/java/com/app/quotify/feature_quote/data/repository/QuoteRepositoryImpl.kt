package com.app.quotify.feature_quote.data.repository

import com.app.quotify.feature_quote.data.data_source.local.QuoteDatabase
import com.app.quotify.feature_quote.data.data_source.remote.PexelsImgApi
import com.app.quotify.feature_quote.data.data_source.remote.QuoteApi
import com.app.quotify.feature_quote.domain.modal.PexelsImageList
import com.app.quotify.feature_quote.domain.modal.QuotableQuoteList
import com.app.quotify.feature_quote.domain.modal.Quote
import com.app.quotify.feature_quote.domain.repository.QuoteRepository
import com.app.quotify.feature_quote.util.Constants.imgCategoryList
import kotlinx.coroutines.flow.Flow
import java.util.*
import javax.inject.Inject

class QuoteRepositoryImpl @Inject constructor(
    quoteDatabase: QuoteDatabase,
    private val quoteApi: QuoteApi,
    private val pexelsImgApi: PexelsImgApi
) : QuoteRepository {

    private val favoriteQuoteDao = quoteDatabase.getFavoriteQuoteDao()

    override suspend fun getImages(): PexelsImageList  {
        return pexelsImgApi.getImages(
            query = imgCategoryList[Random().nextInt(16)]
        ).body()!!
    }

    override suspend fun getQuotesText(): QuotableQuoteList {
        return quoteApi.getQuotes(
            Random().nextInt(95)
        ).body()!!
    }

    override fun getQuotesFromDb(): Flow<List<Quote>> {
        return favoriteQuoteDao.getQuotes()
    }

    override suspend fun insertQuote(quote: Quote) {
        favoriteQuoteDao.insertQuote(quote)
    }

    override suspend fun deleteQuote(quote: Quote) {
        favoriteQuoteDao.deleteQuote(quote)
    }

}


