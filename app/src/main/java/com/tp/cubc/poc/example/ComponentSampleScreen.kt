package com.tp.cubc.poc.example

import android.app.DatePickerDialog
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.widget.DatePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tp.cubc.poc.R
import com.tp.cubc.poc.ui.bg.TreeBg
import com.tp.cubc.poc.ui.component.*
import com.tp.cubc.poc.ui.theme.CubcAppTheme
import com.tp.cubc.poc.ui.theme.Typography
import java.util.*

@Composable
fun ComponentSampleScreen() {
    var email by remember { mutableStateOf("") }
    var errorFieldValue by remember { mutableStateOf("") }

    val pickedYearState = remember { mutableStateOf(2000) }
    val pickedMonthState = remember { mutableStateOf(5) }
    val pickedDateState = remember { mutableStateOf(20) }

    var pickedDate by remember{ mutableStateOf(Date()) }
    var pickedDateDailog by remember{ mutableStateOf("2011/04/13") }

    val mDatePickerDialog = DatePickerDialog(
        LocalContext.current,
        R.style.MySpinnerDatePickerStyle, // Spinner
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->

            pickedDateDailog = "$mDayOfMonth/${mMonth+1}/$mYear"

        }, 2022, 4, 8

    )


    ProvideTextStyle(Typography.body2) {
        TreeBg {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .background(
                        color = MaterialTheme.colors.surface,
                        shape = RoundedCornerShape(30.dp)
                    )
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("All components trying")

                OutlinedTextField(
                    value = email,
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Email", style = Typography.body2) },
                    textStyle = Typography.body1,
                    placeholder = { Text("Please Enter Email") },
                    onValueChange = { email = it},
                )
                Spacer(Modifier.height(16.dp))

                OutlinedTextField(
                    value = errorFieldValue,
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Has Error") },
                    isError = true,
                    onValueChange = { errorFieldValue = it },
                )
                ErrorMessage(text = "Oster test error", Modifier.fillMaxWidth())
                Spacer(Modifier.height(16.dp))

                DropdownField(
                    value = "Oster",
                    label = { Text("People") },
                )
                DropdownField(
                    value = "Oster",
                    label = { Text("Disabled Drop down") },
                    enabled = false
                )

                Row(Modifier.fillMaxWidth()) {
                    NumberPicker(
                        state = pickedYearState,
                        range = IntRange(1990, 2023),
                        modifier = Modifier.weight(1.0f)
                    )

                    NumberPicker(
                        state = pickedMonthState,
                        range = IntRange(1, 12),
                        modifier = Modifier.weight(1.0f)
                    )

                    NumberPicker(
                        state = pickedDateState,
                        range = IntRange(1, 31),
                        modifier = Modifier.weight(1.0f)
                    )
                }

                DatePicker { date -> pickedDate = date }

                Button({ mDatePickerDialog.show() }) {
                    Text("Open Calendar")
                }

                Spacer(Modifier.weight(1.0f))

            }
        }
    }
}


@Preview(name = "light")
@Preview(name = "dark", uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun PreviewScreen() {
    CubcAppTheme {
        ComponentSampleScreen()
    }
}