package com.app.dailymotivation.feature_quote.presentation

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.app.dailymotivation.feature_quote.domain.repository.QuoteRepositoryImpl
import com.app.dailymotivation.feature_quote.presentation.quotes.QuoteViewModel
import com.app.dailymotivation.feature_quote.presentation.quotes.QuoteViewModelFactory
import com.app.dailymotivation.feature_quote.presentation.quotes.components.HomeScreen
import com.app.dailymotivation.feature_quote.presentation.util.Utilities.Companion.displayToast
import com.app.dailymotivation.ui.theme.DailyMotivationTheme
import com.app.dailymotivation.ui.theme.Grey
import com.app.dailymotivation.ui.theme.blackShade

class MainActivity : ComponentActivity() {

    private val quoteImgUrlList = ArrayList<String>()
    private val quoteTextList = ArrayList<String>()
    private val quoteAuthorList = ArrayList<String>()
    private lateinit var quoteViewModelFactory: QuoteViewModelFactory
    private lateinit var viewModel: QuoteViewModel

    @OptIn(ExperimentalMaterial3Api::class)
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //createNotificationChannel(applicationContext)
        //scheduleNotification(applicationContext)

        quoteViewModelFactory = QuoteViewModelFactory(QuoteRepositoryImpl())
        viewModel = ViewModelProvider(this, quoteViewModelFactory)[QuoteViewModel::class.java]


        setContent {

            var showProgressBar by remember {
                mutableStateOf(true)
            }

            viewModel.getImages().observe(this@MainActivity) {
                if (it != null) {
                    for (i in it) {
                        quoteImgUrlList.add(i.src.medium)
                    }

                    if (quoteTextList.isNotEmpty()) {
                        showProgressBar = false
                    }
                }
            }

            viewModel.getQuote().observe(this@MainActivity) {
                if (it != null) {
                    for (i in it) {
                        quoteTextList.add(i.content)
                        quoteAuthorList.add(i.author)
                    }

                    if (quoteImgUrlList.isNotEmpty()) {
                        showProgressBar = false
                    }
                }
            }

            DailyMotivationTheme {
                Scaffold(
                    topBar = {
                        SmallTopAppBar(
                            title = {
                                Text(text = "Motivational Quotes")
                            },
                            colors = TopAppBarDefaults.smallTopAppBarColors()
                        )
                    },
                    bottomBar = {
                        AppBottomNavigation(
                            modifier = Modifier.padding(0.dp)
                        )
                    }
                ) {

                    Surface(
                        color = Grey,
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(
                                    top = it.calculateTopPadding(),
                                    start = 16.dp,
                                    end = 16.dp
                                ),
                            contentAlignment = Alignment.Center,
                        ) {
                            if (showProgressBar) {
                                LinearProgressIndicator()
                            } else {
                                HomeScreen(
                                    quoteData = Triple(
                                        first = quoteTextList,
                                        second = quoteAuthorList,
                                        third = quoteImgUrlList
                                    )
                                )
                            }
                        }

                    }
                }

            }
        }
    }


    @Composable
    fun AppBottomNavigation(
        modifier: Modifier = Modifier
    ) {

        BottomNavigation(
            modifier = modifier,
            backgroundColor = blackShade
        ) {
            BottomNavigationItem(
                selected = false,
                onClick = {
                    displayToast(text = "Home Screen", context = applicationContext)
                },
                icon = {
                    Icon(
                        imageVector = Icons.Rounded.Home,
                        contentDescription = "home_screen",
                    )
                },
                label = {
                    Text(text = "Home", fontSize = 14.sp)
                }
            )
            BottomNavigationItem(
                selected = false,
                onClick = {
                    displayToast(text = "Favorite Screen", context = applicationContext)
                },
                icon = {
                    Icon(
                        imageVector = Icons.Rounded.Favorite,
                        contentDescription = "favorite_screen",
                    )
                },
                label = {
                    Text(text = "Favorite", fontSize = 14.sp)
                }
            )

        }
    }


    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        DailyMotivationTheme {
            HomeScreen(
                quoteData = Triple(
                    first = quoteTextList,
                    second = quoteAuthorList,
                    third = quoteImgUrlList
                )
            )
        }
    }

}