package com.tp.cubc.poc.ui.theme

import androidx.compose.material.Colors
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.material.primarySurface
import androidx.compose.ui.graphics.Color
import com.tp.cubc.poc.R


//object ColorPalette {
//    val lightColors = Colors(
//        primary = Color(0xFF6200EE),
//        primaryVariant = Color(0xFF3700B3),
//        secondary = Color(0xFF03DAC6),
//        secondaryVariant = Color(0xFF018786),
//        background = Color.White,
//        surface = Color.White,
//        error = Color(0xFFCC6620),
//        onPrimary = Color.White,
//        onSecondary = Color.Black,
//        onBackground = Color.Black,
//        onSurface = Color.Black,
//        onError = Color.White,
//        isLight = true
//    )
//
//    val darkColors = Colors(
//        primary = Color(0xFFBB86FC),
//        primaryVariant = Color(0xFF3700B3),
//        secondary = Color(0xFF03DAC6),
//        secondaryVariant = Color(0xFF03DAC6),
//        background = Color(0xFF121212),
//        surface = Color(0xFF121212),
//        error = Color(0xFFCF6679),
//        onPrimary = Color.Black,
//        onSecondary = Color.Black,
//        onBackground = Color.White,
//        onSurface = Color.White,
//        onError = Color.Black,
//        isLight = false
//    )
//}

val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200,
    error = Color(0xFFAA6622),
)

val LightColorPalette = lightColors(
    primary = Golden500,
    primaryVariant = Green500,
    surface = Color.White,
    background = Green600,
    secondary = Teal200

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)