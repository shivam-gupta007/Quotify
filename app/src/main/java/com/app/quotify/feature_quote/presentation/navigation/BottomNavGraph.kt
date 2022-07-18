package com.app.quotify.feature_quote.presentation.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.app.quotify.feature_quote.presentation.quotes.components.QuoteEvent
import com.app.quotify.feature_quote.presentation.quotes.components.screens.FavoriteScreen
import com.app.quotify.feature_quote.presentation.quotes.components.screens.HomeScreen
import com.app.quotify.feature_quote.presentation.quotes.viewmodel.QuoteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
fun BottomNavGraph(
    navController: NavHostController,
    viewModel: QuoteViewModel
) {
    var loadOnlyOnce by remember {
        mutableStateOf(true)
    }

    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route
    ) {

        composable(
            route = BottomBarScreen.Home.route
        ) {

            if (loadOnlyOnce) {
                viewModel.onEvent(QuoteEvent.GetQuotesFromApi)
                loadOnlyOnce = false
            }
            HomeScreen(quoteViewModel = viewModel)
        }

        composable(
            route = BottomBarScreen.Favourite.route
        ) {
            viewModel.onEvent(QuoteEvent.GetQuotesFromDb)
            FavoriteScreen(quoteViewModel = viewModel)
        }
    }

}