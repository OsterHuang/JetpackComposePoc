package com.tp.cubc.poc.home

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.tp.cubc.poc.R
import com.tp.cubc.poc.home.layout.HomeLayout

@Composable
fun OtherScreen(homeIndexRouter: HomeIndexRouter) {
    HomeLayout(
        currentRouteName = HomeRoutes.Other.name,
        homeIndexRouter = homeIndexRouter
    ) {
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
    OtherScreen(HomeIndexRouter(navController))
}