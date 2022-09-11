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
fun ValueText(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier,
        color = MaterialTheme.colors.onSurface,
        style = MaterialTheme.typography.subtitle2,
        text = text
    )
}

@Preview
@Composable
fun PreviewValueText() {
    CubcAppTheme {
        Surface(color =  MaterialTheme.colors.surface) {
            ValueText("This is value text")
        }
    }
}