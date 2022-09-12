package com.tp.cubc.poc.landing

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mbanking.cubc.login.repository.dataModel.LoginRequestBody
import com.tp.cubc.poc.R
import com.tp.cubc.poc.app.CubcAppViewModel
import com.tp.cubc.poc.landing.repository.LandingApi
import com.tp.cubc.poc.landing.repository.LandingRemoteDataSource
import com.tp.cubc.poc.landing.repository.dataModel.AccessTokenResponse
import com.tp.cubc.poc.landing.repository.dataModel.LoginResponseBodyResult
import com.tp.cubc.poc.ui.bg.LoginBg
import com.tp.cubc.poc.ui.bg.TreeBg
import com.tp.cubc.poc.ui.theme.CubcAppTheme
import com.tp.cubc.poc.ui.theme.Green500
import com.tp.cubc.poc.util.http.HttpRequestBody
import com.tp.cubc.poc.util.http.HttpResponseBody
import kotlinx.coroutines.launch
import retrofit2.Response

@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel,
    goRegister: () -> Unit,
    goApplyMobileBank: () -> Unit,
    goHome: () -> Unit
) {
    val appViewModel: CubcAppViewModel = viewModel(LocalContext.current as ComponentActivity)

    val coroutineScope = rememberCoroutineScope()

    val onLogin = fun() {
        coroutineScope.launch {
            appViewModel.loading.value++
            loginViewModel.login()
            appViewModel.loading.value--
            goHome()
        }
    }

    LoginBg {
        Column(
            Modifier
                .fillMaxSize()
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                modifier = Modifier
                    .padding(0.dp, 100.dp, 0.dp, 60.dp)
                    .wrapContentSize(),
                painter = painterResource(R.drawable.ic_splash_logo),
                contentDescription = "background_image",
                contentScale = ContentScale.FillBounds
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = MaterialTheme.colors.surface,
                        shape = RoundedCornerShape(8.dp)
                    ),
                value = loginViewModel.username,
                onValueChange = { loginViewModel.username = it },
                placeholder = { Text("Please Enter the Username") }
            )
            Spacer(modifier = Modifier.height(50.dp))

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = MaterialTheme.colors.surface,
                        shape = RoundedCornerShape(8.dp)
                    ),
                value = loginViewModel.usermima,
                onValueChange = { loginViewModel.usermima = it },
                placeholder = { Text("Please Enter the Password") }
            )
            Spacer(modifier = Modifier.height(40.dp))

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                onClick = onLogin
            ) {
                Text("Login")
            }
            Spacer(modifier = Modifier.height(18.dp))

            Column(
                Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                TextButton(onClick = goApplyMobileBank) {
                    Text(
                        stringResource(id = R.string.apply_mobile_bank),
                        color = MaterialTheme.colors.onPrimary
                    )
                }
                Row(
                    Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Divider(
                        modifier = Modifier.weight(0.5f),
                        color = MaterialTheme.colors.onPrimary
                    )
                    Text(
                        " or ",
                        modifier = Modifier.weight(0.2f),
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colors.onPrimary
                    )
                    Divider(
                        modifier = Modifier.weight(0.5f),
                        color = MaterialTheme.colors.onPrimary
                    )
                }
                TextButton(onClick = goRegister) {
                    Text(
                        stringResource(id = R.string.go_register),
                        color = MaterialTheme.colors.onPrimary
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1.0f))

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(24.dp, 12.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_login_location),
                    contentDescription = "Location Icon",
                    tint = MaterialTheme.colors.onPrimary
                )

                Icon(
                    painter = painterResource(id = R.drawable.ic_login_contact),
                    contentDescription = "Location Icon",
                    tint = MaterialTheme.colors.onPrimary
                )

                Icon(
                    modifier = Modifier
                        .height(44.dp)
                        .width(88.dp),
                    painter = painterResource(id = R.drawable.ic_login_language_khmer),
                    contentDescription = "Location Icon",
                    tint = MaterialTheme.colors.onPrimary
                )
            }
        }
    }
}


@Preview(name = "phone", device = "spec:shape=Normal,width=375,height=790,unit=dp,dpi=480")
@Composable
fun PreviewScreen() {
    CubcAppTheme() {
        val viewModel = LoginViewModel(LandingRemoteDataSource(object: LandingApi{
            override suspend fun accessToken(requestBody: HttpRequestBody): Response<HttpResponseBody<AccessTokenResponse>> {
                return Response.success(HttpResponseBody("", "", AccessTokenResponse("")))
            }
            override suspend fun login(loginRequest: LoginRequestBody): Response<HttpResponseBody<LoginResponseBodyResult>> {
                return Response.success(HttpResponseBody("", "", LoginResponseBodyResult("")))
            }

        }))
        LoginScreen(viewModel, {}, {}, {})
    }
}