package com.app.quotify.feature_quote.domain.use_cases

import com.app.quotify.feature_quote.data.repository.QuoteRepositoryImpl
import com.app.quotify.feature_quote.domain.model.InvalidQuoteException
import com.app.quotify.feature_quote.domain.model.Quote

class AddQuoteToFav(
    private val quoteRepositoryImpl: QuoteRepositoryImpl
) {
    suspend operator fun invoke(quote: Quote) {

        if(quote.quoteContent.isBlank()){
          throw InvalidQuoteException(message = "Quote text can't be empty")
        }

        if(quote.quoteAuthor.isBlank()){
            throw InvalidQuoteException(message = "Quote author can't be empty")
        }

        if(quote.quoteImgUrl.isBlank()){
            throw InvalidQuoteException(message = "Quote image url can't be empty")
        }

        quoteRepositoryImpl.insertQuote(quote)
    }
}