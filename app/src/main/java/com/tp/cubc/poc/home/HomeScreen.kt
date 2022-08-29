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
import com.tp.cubc.poc.home.layout.HomeTopBar
import com.tp.cubc.poc.ui.bg.TreeBg

@Composable
fun HomeScreen(homeRouter: HomeRouter) {
    HomeLayout(homeRouter) {
        HomeTopBar()
        Text("Home")
    }
}


@Preview(name = "phone", device = "spec:shape=Normal,width=375,height=790,unit=dp,dpi=480")
@Composable
private fun PreviewScreen() {
    val navController = rememberNavController()
    HomeScreen(HomeRouter(navController))
}