package com.tp.cubc.poc.home.account

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.tp.cubc.poc.R
import com.tp.cubc.poc.home.HomeIndexRouter
import com.tp.cubc.poc.home.HomeRoutes
import com.tp.cubc.poc.home.layout.HomeLayout

@Composable
fun MyAccountListScreen(
    homeIndexRouter: HomeIndexRouter,
    goDetail: () -> Unit
) {
    HomeLayout(
        currentRouteName = HomeRoutes.Account.name,
        homeIndexRouter = homeIndexRouter
    ) {
        Text(
            stringResource(id = R.string.my_account),
            color = colorResource(R.color.white)
        )

        Button(onClick = goDetail) {
            Text("My account 1")
        }
    }
}


@Preview(name = "phone", device = "spec:shape=Normal,width=375,height=790,unit=dp,dpi=480")
@Composable
private fun PreviewScreen() {
    val navController = rememberNavController()
    MyAccountListScreen (HomeIndexRouter(navController)) {}
}