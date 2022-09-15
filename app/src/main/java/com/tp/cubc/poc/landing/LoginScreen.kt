package com.tp.cubc.poc.landing

import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tp.cubc.poc.R
import com.tp.cubc.poc.app.CubcAppViewModel
import com.tp.cubc.poc.ui.bg.LoginBg
import com.tp.cubc.poc.ui.theme.CubcAppTheme
import kotlinx.coroutines.launch

/**
 * Adapter層為與view model狀態的橋接、api error handle
 *
 * 架構想法：
 * ```
 *   *. view model 彼此無法相互inject，跨view model的操作還是透過 screen adapter做操作
 *   *. 統一操作 coroutine 操作來決定哪些api是async哪些是sync, 組裝api流程
 *   *. API處理結束後，不用透過channel來通知需要後續的操作
 *   *. 由上述兩點，loading的處理與錯誤處理，這邊處理會比較簡潔與方便。
 *   *.
 * ```
 */
@Composable
fun LoginScreenAdapter(
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

    LoginScreen(
        loginViewModel.username,
        loginViewModel.usermima,
        onChangeUsername = {  loginViewModel.username = it },
        onChangeUsermima = { loginViewModel.usermima = it },
        onLogin = onLogin,
        goRegister = goRegister,
        goApplyMobileBank = goApplyMobileBank
    )

}

/**
 * ViewModel無法在Preview中初始化，所以將需要viewModel的元件拆離
 * 概念為 functional programming - Pure function
 */
@Composable
fun LoginScreen(
//    isRegistered: Boolean,
//    isRmemberMe: Boolean,
//    isBiometricOn: Boolean,
    username: String,
    usermima: String,
    onChangeUsername: (String) -> Unit,
    onChangeUsermima: (String) -> Unit,
    onLogin: () -> Unit,
    goRegister: () -> Unit,
    goApplyMobileBank: () -> Unit
) {
    LoginBg {
        Column(
            Modifier
                .fillMaxSize()
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                modifier = Modifier
                    .padding(0.dp, 80.dp, 0.dp, 60.dp)
                    .wrapContentSize(),
                painter = painterResource(R.drawable.ic_splash_logo),
                contentDescription = "background_image",
                contentScale = ContentScale.FillBounds
            )

            LoginInputArea(
                username,
                usermima,
                onChangeUsername,
                onChangeUsermima,
                onLogin
            )
            Spacer(modifier = Modifier.height(18.dp))

            LoginRegisterActionArea(
                goRegister = goRegister,
                goApplyMobileBank = goApplyMobileBank
            )
            Spacer(modifier = Modifier.weight(1.0f))

            LoginOtherArea()
            Spacer(modifier = Modifier.height(6.dp))

            Text("V 0.1.1", color = MaterialTheme.colors.onPrimary)
        }
    }
}

@Composable
fun LoginInputArea(
    username: String,
    usermima: String,
    onChangeUsername: (String) -> Unit,
    onChangeUsermima: (String) -> Unit,
    onLogin: () -> Unit,
) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colors.surface,
                shape = RoundedCornerShape(8.dp)
            ),
        value = username,
        onValueChange = onChangeUsername,
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
        value = usermima,
        onValueChange = onChangeUsermima,
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
}

@Composable
fun LoginRegisterActionArea(
    goRegister: () -> Unit,
    goApplyMobileBank: () -> Unit
) {
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
}

@Composable
fun LoginOtherArea() {
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

@Preview(name = "phone", device = "spec:shape=Normal,width=375,height=790,unit=dp,dpi=480")
@Composable
fun PreviewScreen() {
    CubcAppTheme() {
        LoginScreen(
            username = "",
            "",
            {},
            {},
            {}, {}, {})
    }
}