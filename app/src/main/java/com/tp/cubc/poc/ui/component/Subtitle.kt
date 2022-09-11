package com.tp.cubc.poc.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tp.cubc.poc.R
import com.tp.cubc.poc.ui.theme.CubcAppTheme
import com.tp.cubc.poc.ui.theme.Golden500
import com.tp.cubc.poc.ui.theme.Green500
import com.tp.cubc.poc.ui.theme.Green600

@Composable
fun Subtitle(
    text: String,
    shape: Shape = RectangleShape,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .padding(0.dp, 0.dp, 12.dp, 0.dp)
                .size(12.dp)
                .clip(shape)
                .background(MaterialTheme.colors.primary)
        )

        Text(
            color = MaterialTheme.colors.onPrimary,
            style = MaterialTheme.typography.subtitle1,
            text = text
        )
    }
}

@Preview
@Composable
fun PreviewSubtitle() {
    CubcAppTheme {
        Surface(color = Green600) {
            Subtitle("This is subtitle1")
        }
    }
}