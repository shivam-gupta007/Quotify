package com.app.quotify.feature_quote.presentation.quotes.components.screens

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.app.quotify.feature_quote.presentation.navigation.BottomBarScreen
import com.app.quotify.feature_quote.presentation.navigation.BottomNavGraph
import com.app.quotify.feature_quote.presentation.quotes.viewmodel.QuoteViewModel

@ExperimentalAnimationApi
@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()
    val quoteViewModel = viewModel<QuoteViewModel>()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = { MainTopBar() },
        bottomBar = { BottomBar(navController) }
    ) { innerPadding ->
        println(innerPadding)

        Log.d("AppStatus", "MainScreen:: Scaffold")

        Box(modifier = Modifier.padding(innerPadding)) {
            Log.d("AppStatus", "MainScreen:: Box")
            BottomNavGraph(
                navController = navController,
                viewModel = quoteViewModel
            )
        }
    }

}

@Composable
private fun MainTopBar() {
    SmallTopAppBar(
        title = {
            Text(text = "  Quotes")
        },
        colors = TopAppBarDefaults.smallTopAppBarColors()
    )
}

@Composable
private fun BottomBar(
    navController: NavHostController
) {
    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Favourite
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar {
        screens.forEach { screen ->
            AddItem(
                screen,
                currentDestination,
                navController
            )
        }
    }

}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {

    NavigationBarItem(
        label = {
            Text(text = screen.title)
        },
        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = "Navigation Icon"
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        onClick = {

            navController.navigate(screen.route) {

                //Pop up to a given destination before navigating.
                // Navigate the user to startDestination (Home)
                popUpTo(navController.graph.startDestinationId)

                //This will allow us to store multiple copies of same destination
                launchSingleTop = true
            }

        }
    )
}

