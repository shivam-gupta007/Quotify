package com.app.quotify.feature_quote.presentation.quotes.components.screens

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberSwipeableState
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
fun HomeScreen(
    modifier: Modifier = Modifier,
    quoteViewModel: QuoteViewModel
) {

    val state = quoteViewModel.state.value
    val quoteList = state.quotesListFromApi


    if (quoteList.isNotEmpty()) {
        QuoteList(
            modifier = modifier,
            quoteList = quoteList,
            viewModel = quoteViewModel,
            screen = BottomBarScreen.Home
        )
    } else {
        EmptyScreen(displayProgressIndicator = true)
    }

    if (state.isLoading) {
        EmptyScreen(displayProgressIndicator = true)
        Log.d("AppStatus", "HomeScreen:: isLoading: ${state.isLoading}")
    }

    if (state.error.isNotBlank()) {
        Log.d("AppStatus", "HomeScreen:: error: ${state.error}")
        EmptyScreen(text = stringResource(id = R.string.home_screen_error_msg))
    }

}





