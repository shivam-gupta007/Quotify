package com.app.dailymotivation.feature_quote.presentation.quotes.components

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.app.dailymotivation.feature_quote.domain.modal.Quote
import com.app.dailymotivation.feature_quote.presentation.util.Utilities.Companion.displayToast
import com.app.dailymotivation.feature_quote.presentation.util.Utilities.Companion.shareImage
import com.app.dailymotivation.feature_quote.presentation.util.captureBitmap

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    quoteData: Triple<ArrayList<String>, ArrayList<String>, ArrayList<String>>
) {

    val (quoteTextList, quoteAuthorList, quoteImgUrlList) = quoteData

    LazyColumn(
        modifier = modifier.padding(bottom = 50.dp)
    ) {
        items(14) {
            if (quoteTextList.size > 0 && quoteImgUrlList.size > 0) {
                QuoteCard(
                    Quote(
                        quoteText = quoteTextList[it],
                        quoteAuthor = quoteAuthorList[it],
                        quoteImgUrl = quoteImgUrlList[it]
                    )
                )
            }
        }
    }
}

@Composable
fun QuoteCard(
    quote: Quote
) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .padding(bottom = 10.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        var visible by remember {
            mutableStateOf(false)
        }

        val snapShot = captureBitmap {
            QuoteItem(
                modifier = Modifier.padding(8.dp),
                quote = Quote(
                    quoteText = quote.quoteText,
                    quoteAuthor = quote.quoteAuthor,
                    quoteImgUrl = quote.quoteImgUrl
                )
            ) {
                visible = !visible
            }
        }

        AnimatedVisibility(
            visible = visible,
            enter = expandVertically(),
            exit = shrinkVertically(),

            )
        {
            ButtonsGroup(
                onClick = {
                    Log.d("ICON", "ICON NAME: ${it.name}")
                    when (it.name) {
                        "Outlined.FavoriteBorder" -> {
                            visible = false
                        }
                        "Outlined.Share" -> {
                            displayToast(text = "Sharing quote.......", context = context)
                            shareImage(bitmap = snapShot.invoke(), context = context)
                            visible = false
                        }
                        "Filled.ContentCopy" -> {
                            displayToast(text = "Image copied to clipboard !", context = context)
                            visible = false
                        }
                    }
                }
            )
        }

    }
}