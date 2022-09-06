package com.tp.cubc.poc.home.layout

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tp.cubc.poc.ui.theme.CubcAppTheme

@Composable
fun HomeTopBar() {
    val foregroundColor = MaterialTheme.colors.onPrimary
    val backgroundColor = MaterialTheme.colors.background

    TopAppBar(
        title = {
            Text(
                text = "Top App Bar",
                style = com.tp.cubc.poc.ui.theme.Typography.h3,
                color = foregroundColor
            )
        },
        navigationIcon = {
            IconButton(onClick = {}) {
                Icon(Icons.Filled.Settings, "backIcon", tint = foregroundColor)
            }
        },
        actions = {
            IconButton(onClick = {}) {
                Icon(Icons.Filled.Notifications, "backIcon", tint = foregroundColor)
            }
        },
        backgroundColor = backgroundColor,
        elevation = 10.dp
    )
}

@Preview(name = "phone", device = "spec:shape=Normal,width=375,height=790,unit=dp,dpi=480")
@Composable
private fun PreviewScreen() {
    CubcAppTheme {
        Surface(color = MaterialTheme.colors.background) {
            HomeTopBar()
        }
    }
}