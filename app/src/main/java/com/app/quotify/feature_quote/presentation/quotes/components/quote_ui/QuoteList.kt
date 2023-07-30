package com.app.quotify.feature_quote.presentation.quotes.components.quote_ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.app.quotify.feature_quote.domain.model.Quote
import com.app.quotify.feature_quote.presentation.navigation.BottomBarScreen
import com.app.quotify.feature_quote.presentation.quotes.viewmodel.QuoteViewModel

@ExperimentalMaterial3Api
@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
fun QuoteList(
    modifier: Modifier = Modifier,
    quoteList: List<Quote>,
    viewModel: QuoteViewModel,
    screen: BottomBarScreen
) {

    LazyColumn(
        modifier = modifier.padding(bottom = 0.dp, start = 16.dp, end = 16.dp)
    ) {
        items(quoteList.size) { i ->
            if (quoteList.isNotEmpty()) {
                if (quoteList[i].quoteContent.isNotEmpty() &&
                    quoteList[i].quoteAuthor.isNotEmpty() &&
                    quoteList[i].quoteContent.isNotEmpty()
                ) {
                    QuoteCard(
                        modifier = Modifier.animateItemPlacement(),
                        Quote(
                            quoteId = quoteList[i].quoteId,
                            quoteContent = quoteList[i].quoteContent,
                            quoteAuthor = quoteList[i].quoteAuthor,
                            quoteImgUrl = quoteList[i].quoteImgUrl
                        ),
                        quoteViewModel = viewModel,
                        screen = screen
                    )
                }
            }
        }
    }
}