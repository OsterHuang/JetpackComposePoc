package com.tp.cubc.poc.home.layout

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.tp.cubc.poc.R
import com.tp.cubc.poc.home.HomeIndexRouter
import com.tp.cubc.poc.home.HomeRoutes

enum class HomeBottomNavItemResources(
    val routeName: String,
    val iconResChecked: Int,
    val iconResUnChecked: Int,
    val text: Int,
) {
    Home(
        HomeRoutes.Home.name,
        R.drawable.ic_home_tab_home_checked,
        R.drawable.ic_home_tab_home_unchecked,
        R.string.home,

    ),
    Account(
        HomeRoutes.Account.name,
        R.drawable.ic_home_tab_account_checked,
        R.drawable.ic_home_tab_account_unchecked,
        R.string.account
    ),
    Location(
        HomeRoutes.Location.name,
        R.drawable.ic_home_tab_location_checked,
        R.drawable.ic_home_tab_location_unchecked,
        R.string.location
    ),
    Other(
        HomeRoutes.Other.name,
        R.drawable.ic_home_tab_other_checked,
        R.drawable.ic_home_tab_other_unchecked,
        R.string.service
    ),
}


@Composable
fun HomeBottomNavigation(
    checkedRouteName: String,
    homeIndexRouter: HomeIndexRouter,
) {
    Log.d("Oster", checkedRouteName)

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
        val navigateActionsMap = mapOf(
            HomeBottomNavItemResources.Home to homeIndexRouter.goHome,
            HomeBottomNavItemResources.Account to homeIndexRouter.goAccount,
            HomeBottomNavItemResources.Location to homeIndexRouter.goLocation,
            HomeBottomNavItemResources.Other to homeIndexRouter.goOther,
        )

        HomeBottomNavItemResources.values().map { res ->
            HomeBottomNavItem(
                res = res,
                checked = checkedRouteName == res.routeName,
                onClick = {
                    navigateActionsMap[res]?.let { navTo -> navTo() }
                }
            )
        }
    }
}

@Composable
fun HomeBottomNavItem(
    res: HomeBottomNavItemResources,
    checked: Boolean,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .wrapContentHeight()
            .wrapContentWidth(align = Alignment.CenterHorizontally)
            .clickable {
                onClick()
            }
    ) {
        val icon = if (checked)
            ImageVector.vectorResource(id = res.iconResChecked)
        else
            ImageVector.vectorResource(id = res.iconResUnChecked)
        
        val textColor = if (checked)
            colorResource(id = R.color.gray_700)
        else
            colorResource(id = R.color.gray_300)

        Image(
            modifier = Modifier
                .padding(0.dp, 6.dp, 0.dp, 0.dp)
                .align(alignment = Alignment.CenterHorizontally),
            imageVector = icon,
            contentDescription = stringResource(id = res.text),
        )
        Text(
            modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 6.dp),
            text = stringResource(id = res.text),
            color = textColor
        )
    }
}

@Preview(name = "phone", device = "spec:shape=Normal,width=375,height=790,unit=dp,dpi=480")
@Composable
private fun PreviewScreen() {
    HomeBottomNavigation(
        checkedRouteName = "Location",
        homeIndexRouter = HomeIndexRouter(rememberNavController())
    )
}