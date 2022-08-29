package com.tp.cubc.poc.home.layout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.tp.cubc.poc.home.HomeIndexRouter
import com.tp.cubc.poc.ui.bg.TreeBg

@Composable
fun HomeLayout(
    homeIndexRouter: HomeIndexRouter,
    content: @Composable () -> Unit
) {
    TreeBg() {
        Column(modifier = Modifier.fillMaxSize(1.0f)) {
            Column(modifier = Modifier.weight(1.0f)) {
                content()
            }
            HomeBottomNavigation(homeIndexRouter)
        }
    }
}


@Preview(name = "phone", device = "spec:shape=Normal,width=375,height=790,unit=dp,dpi=480")
@Composable
private fun PreviewScreen() {
    val navController = rememberNavController()
    HomeLayout(HomeIndexRouter(navController)) {
        Text("Test the content")
    }
}
