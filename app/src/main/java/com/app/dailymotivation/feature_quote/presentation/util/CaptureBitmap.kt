package com.app.dailymotivation.feature_quote.presentation.util

import android.graphics.Bitmap
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.drawToBitmap

@Composable
fun captureBitmap(
    content: @Composable () -> Unit
): () -> Bitmap {

    val context = LocalContext.current

    //Composable View that will take composable as its content
    val composeView = remember { ComposeView(context) }

    //Callback function which returns the bitmap of the Compose View
    fun captureBitmap(): Bitmap {
        return composeView.drawToBitmap()
    }

    AndroidView(
        factory = {
            composeView.apply {
                setContent {
                    content.invoke()
                }
            }
        }
    )

    //return the callback function
    return ::captureBitmap
}
