package com.app.dailymotivation.feature_quote.presentation.quotes.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.app.dailymotivation.R
import com.app.dailymotivation.feature_quote.domain.modal.Quote
import com.app.dailymotivation.ui.theme.Shapes
import com.app.dailymotivation.ui.theme.iconColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuoteItem(
    modifier: Modifier = Modifier,
    quote: Quote,
    @DrawableRes drawable: Int = R.drawable.placeholder,
    onClick: () -> Unit
) {
    val offset = Offset(0.0f, 0.0f)

    Card(
        modifier = modifier
            .clickable {
                onClick()
            },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        shape = Shapes.medium
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {

            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp)
                    .background(Color.Black)
                    .alpha(0.6f),
                contentScale = ContentScale.Crop,
                model = ImageRequest.Builder(LocalContext.current)
                    .data(quote.quoteImgUrl)
                    .placeholder(drawable)
                    .allowHardware(false)
                    .error(R.drawable.error)
                    .build(),
                contentDescription = stringResource(R.string.description),
            )

            Column(
                modifier = Modifier
                    .padding(
                        top = 70.dp,
                        end = 30.dp,
                        start = 30.dp,
                        bottom = 25.dp
                    ),
                verticalArrangement = Arrangement.Center,
            ) {

                Text(
                    text = quote.quoteText.uppercase(),
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.SansSerif,
                        shadow = Shadow(
                            color = Color.Black,
                            offset = offset,
                            blurRadius = 10f
                        )
                    ),
                    color = Color.White
                )

                Spacer(
                    modifier = Modifier
                        .height(80.dp)
                        .fillMaxWidth()
                )

                Text(
                    text = quote.quoteAuthor,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontFamily = FontFamily.SansSerif,
                        shadow = Shadow(
                            color = Color.Black,
                            offset = offset,
                            blurRadius = 25f
                        )
                    ),
                    color = Color.White
                )
            }
        }

    }

}

@Composable
fun ButtonsGroup(
    modifier: Modifier = Modifier,
    onClick: (ImageVector) -> Unit
) {
    val iconsList = listOf(
        Icons.Outlined.FavoriteBorder,
        Icons.Outlined.Share,
        Icons.Filled.ContentCopy
    )

    val filledFavoriteIcon = Icons.Filled.Favorite

    var favoriteIconState by rememberSaveable {
        mutableStateOf(false)
    }

    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .padding(bottom = 8.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        contentPadding = PaddingValues(top = 16.dp, bottom = 16.dp)
    ) {
        items(iconsList) { icon ->

            val isIconFavorite =
                icon.name == "Outlined.FavoriteBorder" || icon.name == "Filled.Favorite"

            Icon(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .background(iconColor)
                    .padding(10.dp)
                    .clickable {
                        if (isIconFavorite)
                            favoriteIconState = !favoriteIconState

                        onClick(icon)
                    },
                imageVector =
                if (isIconFavorite) {
                    if (favoriteIconState) filledFavoriteIcon else icon
                } else {
                    icon
                },
                tint = Color.Black,
                contentDescription = null
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun QuoteItemPreview() {
    QuoteItem(
        quote = Quote(
            quoteText = "You don't get what you want. You only get what you are",
            quoteAuthor = "Shivam Gupta",
            quoteImgUrl = ""
        ),
        onClick = {}
    )

}
