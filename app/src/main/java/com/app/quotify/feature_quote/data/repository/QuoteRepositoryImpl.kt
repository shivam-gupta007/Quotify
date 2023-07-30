package com.app.quotify.feature_quote.data.repository

import com.app.quotify.feature_quote.data.data_source.local.QuoteDatabase
import com.app.quotify.feature_quote.data.data_source.remote.adapter.NetworkResult
import com.app.quotify.feature_quote.data.data_source.remote.service.PexelsImgApi
import com.app.quotify.feature_quote.data.data_source.remote.service.QuotableApi
import com.app.quotify.feature_quote.domain.model.PexelsImageList
import com.app.quotify.feature_quote.domain.model.QuotableQuoteList
import com.app.quotify.feature_quote.domain.model.Quote
import com.app.quotify.feature_quote.domain.repository.QuoteRepository
import com.app.quotify.feature_quote.util.Constants.imgCategoryList
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import java.util.*
import javax.inject.Inject

class QuoteRepositoryImpl @Inject constructor(
    quoteDatabase: QuoteDatabase,
    private val quotableApi: QuotableApi,
    private val pexelsImgApi: PexelsImgApi
) : QuoteRepository {

    private val favoriteQuoteDao = quoteDatabase.getFavoriteQuoteDao()

    override suspend fun getImages(): PexelsImageList  {
        return pexelsImgApi.getImages(
            query = imgCategoryList[Random().nextInt(16)]
        ).body()!!
    }

    override suspend fun getQuotesText(): QuotableQuoteList {
        return quotableApi.getQuotes(
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


