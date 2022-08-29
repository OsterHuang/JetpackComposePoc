package com.tp.cubc.poc.home

import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.tp.cubc.poc.home.layout.HomeLayout
import com.tp.cubc.poc.home.layout.HomeTopBar

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
        Text("Home")

        OutlinedButton(onClick = goTransfer) {
            Text("Go Transfer")
        }

        OutlinedButton(onClick = {}) {
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