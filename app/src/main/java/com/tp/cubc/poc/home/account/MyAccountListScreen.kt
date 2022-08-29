package com.tp.cubc.poc.home.account

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.tp.cubc.poc.R
import com.tp.cubc.poc.home.HomeIndexRouter
import com.tp.cubc.poc.home.HomeRoutes
import com.tp.cubc.poc.home.layout.HomeLayout
import com.tp.cubc.poc.ui.bg.TreeBg

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

        OutlinedButton(onClick = goDetail) {
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