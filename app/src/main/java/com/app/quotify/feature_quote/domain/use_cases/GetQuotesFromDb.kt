package com.app.quotify.feature_quote.domain.use_cases

import androidx.compose.ui.res.stringResource
import com.app.quotify.R
import com.app.quotify.feature_quote.data.repository.QuoteRepositoryImpl
import com.app.quotify.feature_quote.domain.modal.Quote
import com.app.quotify.feature_quote.presentation.quotes.components.screens.EmptyScreen
import kotlinx.coroutines.flow.Flow

class GetQuotesFromDb(
    private val quoteRepositoryImpl: QuoteRepositoryImpl
) {
    operator fun invoke(): Flow<List<Quote>> {
        return quoteRepositoryImpl.getQuotesFromDb()
    }
}