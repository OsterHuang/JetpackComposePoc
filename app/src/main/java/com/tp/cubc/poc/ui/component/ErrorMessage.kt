package com.tp.cubc.poc.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tp.cubc.poc.R

@Composable
fun ErrorMessage(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier.padding(0.dp, 8.dp),
        color = MaterialTheme.colors.error,
        text = text
    )
}