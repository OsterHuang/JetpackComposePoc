package com.tp.cubc.poc.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tp.cubc.poc.R

@Composable
fun TitleText(
    text: String,
    modifier: Modifier = Modifier.fillMaxWidth(),
) {
    Text(
        modifier = modifier.padding(16.dp),
        fontSize = 24.sp,
        color = colorResource(id = R.color.white) ,
        text = text
    )
}