package com.tp.cubc.poc.ui.bg

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.tp.cubc.poc.R
import com.tp.cubc.poc.home.HomeIndexRouter
import com.tp.cubc.poc.home.HomeScreen
import com.tp.cubc.poc.ui.theme.CubcAppTheme

@Composable
fun HomeBg (
    modifier: Modifier = Modifier.fillMaxSize(),
    noWhiteTree: Boolean = true,
    content: @Composable () -> Unit
) {


    Box(modifier) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(R.drawable.bg_home),
            contentDescription = "background_image",
            contentScale = ContentScale.FillBounds
        )
        if (!noWhiteTree)
            Image(
                modifier = Modifier
                    .height(545.dp)
                    .width(373.dp)
                    .scale(2f, 1f)
                    .absoluteOffset(-40.dp, 72.dp),
                painter = painterResource(R.drawable.ic_home_tree),
                contentDescription = "background_image",
                contentScale = ContentScale.FillBounds
            )
        content()
    }
}

@Preview(name = "phone")
@Composable
private fun PreviewScreen() {
    HomeBg {}
}