package com.app.quotify.feature_quote.presentation.quotes.components.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun EmptyScreen(
    displayProgressIndicator: Boolean = false,
    text: String = ""
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 40.dp),
        contentAlignment = Alignment.Center
    ) {
        if (displayProgressIndicator) {
            LinearProgressIndicator()
        } else {
            Text(
                text = text,
                fontSize = 18.sp
            )
        }
    }

}