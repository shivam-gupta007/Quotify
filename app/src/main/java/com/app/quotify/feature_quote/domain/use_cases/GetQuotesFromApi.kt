package com.app.quotify.feature_quote.domain.use_cases

import android.util.Log
import com.app.quotify.feature_quote.data.repository.QuoteRepositoryImpl
import com.app.quotify.feature_quote.domain.modal.Quote
import com.app.quotify.feature_quote.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetQuotesFromApi(
    private val quoteRepositoryImpl: QuoteRepositoryImpl
) {
    operator fun invoke(): Flow<Resource<List<Quote>>> = flow {
        val pexelsApiResponse = quoteRepositoryImpl.getImages()
        val quotesApiResponse = quoteRepositoryImpl.getQuotesText()
        val quoteList = ArrayList<Quote>()

        try {
            emit(Resource.Loading())

            pexelsApiResponse.photos.let { pexelsApiResponseList ->

                quotesApiResponse.results.let { quotesApiResponseList ->

                    if(pexelsApiResponseList.isNotEmpty() && quotesApiResponseList.isNotEmpty()) {
                        for (i in 0..19) {
                            val quote = Quote(
                                quoteId = quotesApiResponseList[i].id,
                                quoteContent = quotesApiResponseList[i].content,
                                quoteAuthor = quotesApiResponseList[i].author,
                                quoteImgUrl = pexelsApiResponseList[i].src.medium
                            )
                            quoteList.add(quote)
                        }
                    }
                }
            }

            emit(Resource.Success(quoteList))

        } catch (e: HttpException) {
            Log.d("HttpException", e.message())
            emit(Resource.Error(message = "An unexpected Error occurred"))
        } catch (ioException: IOException) {
            Log.d("IOException", "Error ${ioException.message}")
            emit(Resource.Error(message = "Check your internet connection"))
        }
    }
}