package com.app.quotify.feature_quote.presentation.quotes.components.quote_ui

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.app.quotify.feature_quote.domain.model.Quote
import com.app.quotify.feature_quote.domain.use_cases.copyImageToClipboard
import com.app.quotify.feature_quote.domain.use_cases.shareImage
import com.app.quotify.feature_quote.presentation.navigation.BottomBarScreen
import com.app.quotify.feature_quote.presentation.quotes.components.QuoteEvent
import com.app.quotify.feature_quote.presentation.quotes.viewmodel.QuoteViewModel
import com.app.quotify.feature_quote.presentation.util.captureBitmap


@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@Composable
fun QuoteCard(
    modifier: Modifier = Modifier,
    quote: Quote,
    quoteViewModel: QuoteViewModel,
    screen: BottomBarScreen
) {
    val context = LocalContext.current

    Box(
        modifier = modifier
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
                    quoteId = quote.quoteId,
                    quoteContent = quote.quoteContent,
                    quoteAuthor = quote.quoteAuthor,
                    quoteImgUrl = quote.quoteImgUrl,
                )
            ) {
                visible = !visible
            }
        }

        AnimatedVisibility(
            visible = visible,
            enter = scaleIn(),
            exit = fadeOut()
        )
        {
            ButtonsGroup(
                onClick = {
                    Log.d("ICON", "ICON NAME: ${it.name}")
                    when (it.name) {
                        "Outlined.FavoriteBorder" -> {
                            visible = false
                            quoteViewModel.onEvent(QuoteEvent.InsertQuote(quote = quote))
                            displayToast(text = "Added to favorite", context = context)
                        }

                        "Filled.Favorite" -> {
                           quoteViewModel.onEvent(QuoteEvent.DeleteQuote(quote = quote))
                            displayToast(text = "Removed from favourites", context = context)
                        }

                        "Outlined.Share" -> {
                            displayToast(text = "Sharing quote.......", context = context)
                            shareImage(bitmap = snapShot.invoke(), context = context)
                            visible = false
                        }

                        "Filled.ContentCopy" -> {
                            copyImageToClipboard(context = context, bitmap = snapShot.invoke())
                            displayToast(text = "Image copied to clipboard !", context = context)
                            visible = false
                        }
                    }
                },
                screen = screen
            )
        }

    }
}

fun displayToast(text: String, context: Context) {
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
}