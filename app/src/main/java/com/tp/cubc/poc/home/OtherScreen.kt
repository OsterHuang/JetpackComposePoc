package com.tp.cubc.poc.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.tp.cubc.poc.R
import com.tp.cubc.poc.home.layout.HomeLayout
import com.tp.cubc.poc.ui.bg.TreeBg

@Composable
fun OtherScreen(homeRouter: HomeRouter) {
    HomeLayout(homeRouter) {
        Text(
            stringResource(id = R.string.service),
            color = colorResource(R.color.white)
        )
    }
}


@Preview(name = "phone", device = "spec:shape=Normal,width=375,height=790,unit=dp,dpi=480")
@Composable
private fun PreviewScreen() {
    val navController = rememberNavController()
    OtherScreen(HomeRouter(navController))
}