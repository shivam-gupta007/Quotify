package com.app.quotify.feature_quote.presentation.quotes.components.screens

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.app.quotify.R
import com.app.quotify.feature_quote.presentation.navigation.BottomBarScreen
import com.app.quotify.feature_quote.presentation.quotes.components.quote_ui.QuoteList
import com.app.quotify.feature_quote.presentation.quotes.viewmodel.QuoteViewModel

@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier,
    quoteViewModel: QuoteViewModel
) {

    val state = quoteViewModel.state.value

    if (state.quotesListFromDb.isNotEmpty()) {
        QuoteList(
            modifier = modifier,
            quoteList = state.quotesListFromDb.reversed(),
            viewModel = quoteViewModel,
            screen = BottomBarScreen.Favourite
        )
    } else {
        EmptyScreen(text = stringResource(id = R.string.favorite_screen_msg))
    }

    if (state.error.isNotBlank()) {
        EmptyScreen(text = state.error)
    }

    //val quotesList = quoteViewModel.state.value.quotesListFromDb

//    Log.d("AppStatus", "FavoriteScreen:: quoteList $quotesList")

    /* when (val quoteResult = quoteViewModel.result.value) {
         is QuoteResult.Success<*> -> {
             QuoteList(
                 modifier = modifier,
                 quoteData = (quoteResult.value as List<Quote>).reversed(),
                 viewModel = quoteViewModel,
                 screen = BottomBarScreen.Favourite
             )
         }

         is QuoteResult.Loading -> {
             EmptyScreen(displayProgressIndicator = true)
         }

         is QuoteResult.Failure<*> -> {
             if (quoteResult.value == "QuotesList is empty") {
                 EmptyScreen(text = stringResource(id = R.string.favorite_screen_msg))
             }
         }
     }*/

/*    if (quotesList.isNotEmpty()) {
        QuoteList(
            modifier = modifier,
            quoteData = quotesList.reversed(),
            viewModel = quoteViewModel,
            screen = BottomBarScreen.Favourite
        )
    } else {
        EmptyScreen(text = stringResource(id = R.string.favorite_screen_msg))
    }*/
}
