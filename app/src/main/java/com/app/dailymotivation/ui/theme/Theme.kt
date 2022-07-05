package com.app.dailymotivation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColorScheme(

    /*  onSurface = Color.White,
      surface = Blue,
      primary = LightBlue,
      onPrimary = Navy*/

//    primary = Purple200,
//    secondary = Color.Black,
//    onSecondary = Color.White

    primary = primaryColor,
    secondary = secondaryDarkColor,
    onPrimary = primaryTextColor,
    onSecondary = secondaryTextColor


    /*  primary = Green80,
      onPrimary = Green20,
      primaryContainer = Green30,
      onPrimaryContainer = Green90,
      inversePrimary = Green40,
      secondary = DarkGreen80,
      onSecondary = DarkGreen20,
      secondaryContainer = DarkGreen30,
      onSecondaryContainer = DarkGreen90,
      tertiary = Violet80,
      onTertiary = Violet20,
      tertiaryContainer = Violet30,
      onTertiaryContainer = Violet90,
      error = Red80,
      onError = Red20,
      errorContainer = Red30,
      onErrorContainer = Red90,
      background = Grey10,
      onBackground = Grey90,
      surface = GreenGrey30,
      onSurface = GreenGrey80,
      inverseSurface = Grey90,
      inverseOnSurface = Grey10,
      surfaceVariant = GreenGrey30,
      onSurfaceVariant = GreenGrey80,
      outline = GreenGrey80*/
)

private val LightColorPalette = lightColorScheme(
    /*  onSurface = Navy,
      surface = Blue,
      primary = Navy,
      onPrimary = Chartreuse*/

    primary = primaryColor,
    secondary = secondaryLightColor,
    onPrimary = primaryTextColor,
    onSecondary = secondaryTextColor


    /*  primary = Green80,
      onPrimary = Green20,
      primaryContainer = Green30,
      onPrimaryContainer = Green90,
      inversePrimary = Green40,
      secondary = DarkGreen80,
      onSecondary = DarkGreen20,
      secondaryContainer = DarkGreen30,
      onSecondaryContainer = DarkGreen90,
      tertiary = Violet80,
      onTertiary = Violet20,
      tertiaryContainer = Violet30,
      onTertiaryContainer = Violet90,
      error = Red80,
      onError = Red20,
      errorContainer = Red30,
      onErrorContainer = Red90,
      background = Grey10,
      onBackground = Grey90,
      surface = GreenGrey30,
      onSurface = GreenGrey80,
      inverseSurface = Grey90,
      inverseOnSurface = Grey10,
      surfaceVariant = GreenGrey30,
      onSurfaceVariant = GreenGrey80,
      outline = GreenGrey80*/
)

@Composable
fun DailyMotivationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}