package com.tp.cubc.poc.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.tp.cubc.poc.R
import com.tp.cubc.poc.home.layout.HomeLayout

@Composable
fun LocationScreen(homeIndexRouter: HomeIndexRouter) {
    HomeLayout(homeIndexRouter) {
        Text(
            stringResource(id = R.string.location),
            color = colorResource(R.color.white)
        )
    }
}


@Preview(name = "phone", device = "spec:shape=Normal,width=375,height=790,unit=dp,dpi=750")
@Composable
private fun PreviewScreen() {
    val navController = rememberNavController()
    LocationScreen(HomeIndexRouter(navController))
}