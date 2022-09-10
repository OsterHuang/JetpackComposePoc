package com.tp.cubc.poc.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tp.cubc.poc.ui.theme.Typography

@Composable
fun TopBarTitleText(text: String) {
    val foregroundColor = MaterialTheme.colors.onPrimary

    Text(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center)
            .offset((-16).dp, 0.dp),
        text = text,
        style = Typography.h3,
        color = foregroundColor
    )
}