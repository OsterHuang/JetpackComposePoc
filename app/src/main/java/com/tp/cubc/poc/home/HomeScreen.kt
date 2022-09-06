package com.tp.cubc.poc.home

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.tp.cubc.poc.home.layout.HomeLayout
import com.tp.cubc.poc.home.layout.HomeTopBar
import com.tp.cubc.poc.ui.component.TitleText
import com.tp.cubc.poc.ui.theme.CubcAppTheme

@Composable
fun HomeScreen(
    homeIndexRouter: HomeIndexRouter,
    goTransfer: () -> Unit,
    goUiExample: () -> Unit
) {
    HomeLayout(
        noWhiteTree = false,
        currentRouteName = HomeRoutes.Home.name,
        homeIndexRouter = homeIndexRouter
    ) {
        HomeTopBar()
        TitleText("Home")

        Button(onClick = goTransfer) {
            Text("Go Transfer")
        }

        Spacer(Modifier.height(24.dp))

        Button(onClick = goUiExample) {
            Text("Go UI Examples")
        }
    }
}


@Preview(name = "phone", device = "spec:shape=Normal,width=375,height=790,unit=dp,dpi=480")
@Composable
private fun PreviewScreen() {
    val navController = rememberNavController()
    CubcAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            HomeScreen(HomeIndexRouter(navController), {}) {}
        }
    }
}