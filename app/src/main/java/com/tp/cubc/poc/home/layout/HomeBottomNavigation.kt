package com.tp.cubc.poc.home.layout

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.tp.cubc.poc.R
import com.tp.cubc.poc.home.HomeIndexRouter

enum class HomeBottomNavItemRes(
    val iconResChecked: Int,
    val iconResUnChecked: Int,
    val text: Int
) {
    Home(
        R.drawable.ic_home_tab_home_checked,
        R.drawable.ic_home_tab_home_unchecked,
        R.string.home
    ),
    Account(
        R.drawable.ic_home_tab_account_checked,
        R.drawable.ic_home_tab_account_unchecked,
        R.string.account
    ),
    Location(
        R.drawable.ic_home_tab_location_checked,
        R.drawable.ic_home_tab_location_unchecked,
        R.string.location
    ),
    Other(
        R.drawable.ic_home_tab_other_checked,
        R.drawable.ic_home_tab_other_unchecked,
        R.string.service
    ),
}


@Composable
fun HomeBottomNavigation(
    homeIndexRouter: HomeIndexRouter
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(1.0f)
            .padding(8.dp, 15.dp)
            .background(
                color = colorResource(id = R.color.white),
                shape = RoundedCornerShape(30.dp)
            ),
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        HomeBottomNavItem(
            res = HomeBottomNavItemRes.Home,
            onClick = homeIndexRouter.goHome
        )
        HomeBottomNavItem(
            res = HomeBottomNavItemRes.Account,
            onClick = homeIndexRouter.goAccount
        )
        HomeBottomNavItem(
            res = HomeBottomNavItemRes.Location,
            onClick = homeIndexRouter.goLocation
        )
        HomeBottomNavItem(
            res = HomeBottomNavItemRes.Other,
            onClick = homeIndexRouter.goOther
        )
    }
}

@Composable
fun HomeBottomNavItem(
    res: HomeBottomNavItemRes,
    onClick: () -> Unit
) {
    val localContext = LocalContext.current

    Column(
        modifier = Modifier
            .wrapContentHeight()
            .wrapContentWidth(align = Alignment.CenterHorizontally)
            .clickable {
                Toast.makeText(
                    localContext,
                    "Oster",
                    Toast.LENGTH_SHORT
                )    .show()
                onClick()
            }
    ) {
        Image(
            modifier = Modifier
                .padding(0.dp, 6.dp, 0.dp, 0.dp)
                .align(alignment = Alignment.CenterHorizontally),
            imageVector = ImageVector.vectorResource(id = res.iconResUnChecked),
            contentDescription = stringResource(id = res.text),
        )
        Text(
            modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 6.dp),
            text = stringResource(id = res.text)
        )
    }
}

@Preview(name = "phone", device = "spec:shape=Normal,width=375,height=790,unit=dp,dpi=480")
@Composable
private fun PreviewScreen() {
    HomeBottomNavigation(homeIndexRouter = HomeIndexRouter(rememberNavController()))
}