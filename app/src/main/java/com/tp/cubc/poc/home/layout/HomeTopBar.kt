package com.tp.cubc.poc.home.layout

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tp.cubc.poc.R
import com.tp.cubc.poc.ui.component.TitleText

@Composable
fun HomeTopBar() {
    TopAppBar(
        title = {
            TitleText(text = "Top App Bar")
        },
        navigationIcon = {
            IconButton(onClick = {}) {
                Icon(Icons.Filled.Settings, "backIcon", tint = colorResource(id = R.color.white))
            }
        },
        actions = {
            IconButton(onClick = {}) {
                Icon(Icons.Filled.Notifications, "backIcon", tint = colorResource(id = R.color.white))
            }
        },
        backgroundColor = colorResource(id = R.color.green_500),
        elevation = 10.dp
    )
//    Row(
//        Modifier
//            .fillMaxWidth()
//            .height(48.dp)
//    ) {
//        Text(stringResource(id = R.string.back))
//        Text(
//            modifier = Modifier.weight(1.0f),
//            text = stringResource(id = R.string.home)
//        )
//        Text("Not")
//    }
}

@Preview(name = "phone", device = "spec:shape=Normal,width=375,height=790,unit=dp,dpi=480")
@Composable
private fun PreviewScreen() {
    HomeTopBar()
}