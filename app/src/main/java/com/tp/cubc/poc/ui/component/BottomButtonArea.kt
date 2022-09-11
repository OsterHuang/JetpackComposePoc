package com.tp.cubc.poc.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BottomButtonArea(
    modifier: Modifier = Modifier.padding(20.dp),
    content: @Composable () -> Unit
) {
    val bgColor = MaterialTheme.colors.primaryVariant

    Column(Modifier.fillMaxWidth()) {
        Box(
            Modifier.fillMaxWidth().height(6.dp).background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.Transparent,
                        Color.Black.copy(alpha = 0.1f),
                    )
                )
            )
        )
        Column(
            modifier = modifier.background(bgColor),

            ) {
            content()
        }
    }
}