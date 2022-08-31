package com.tp.cubc.poc.home

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.tp.cubc.poc.home.layout.HomeLayout
import com.tp.cubc.poc.home.layout.HomeTopBar
import com.tp.cubc.poc.ui.component.TitleText

@Composable
fun HomeScreen(
    homeIndexRouter: HomeIndexRouter,
    goTransfer: () -> Unit
) {
    HomeLayout(
        currentRouteName = HomeRoutes.Home.name,
        homeIndexRouter = homeIndexRouter
    ) {
        HomeTopBar()
        TitleText("Home")

        Button(onClick = goTransfer) {
            Text("Go Transfer")
        }

        Button(onClick = {}) {
            Text("Go Payment")
        }
    }
}


@Preview(name = "phone", device = "spec:shape=Normal,width=375,height=790,unit=dp,dpi=480")
@Composable
private fun PreviewScreen() {
    val navController = rememberNavController()
    HomeScreen(HomeIndexRouter(navController)) {}
}