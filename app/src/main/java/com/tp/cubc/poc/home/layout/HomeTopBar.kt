package com.tp.cubc.poc.home.layout

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.tp.cubc.poc.R

@Composable
fun HomeTopBar() {
    Row(
        Modifier
            .fillMaxWidth()
            .height(48.dp)
    ) {
        Text(stringResource(id = R.string.back))
        Text(
            modifier = Modifier.weight(1.0f),
            text = stringResource(id = R.string.home)
        )
        Text("Not")
    }
}