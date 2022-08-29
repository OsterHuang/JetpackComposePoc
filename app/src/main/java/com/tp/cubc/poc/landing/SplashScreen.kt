package com.tp.cubc.poc.landing

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.Image
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tp.cubc.poc.R
import com.tp.cubc.poc.ui.bg.TreeBg
import kotlinx.coroutines.delay

@Composable
fun SplashScreen (
    next: () -> Unit
) {
    LaunchedEffect(true) {
        delay(3000)
        next()
    }

    TreeBg() {
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .wrapContentSize()
                    .weight(1.0f),
                painter = painterResource(R.drawable.ic_splash_logo),
                contentDescription = "background_image",
                contentScale = ContentScale.FillBounds
            )
            Text(
                stringResource(R.string.splash_copyright),
                modifier = Modifier.padding(0.dp, 16.dp),
                textAlign = TextAlign.Center,
                color = colorResource(R.color.white)
            )
        }
    }
}

@Preview(name = "phone", device = "spec:shape=Normal,width=375,height=790,unit=dp,dpi=480")
@Composable
fun PreviewSlashScreen(
    modifier: Modifier = Modifier.fillMaxSize()
) {
    SplashScreen {}
}
