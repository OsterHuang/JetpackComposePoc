package com.tp.cubc.poc.example

import androidx.compose.foundation.layout.*
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tp.cubc.poc.ui.bg.TreeBg
import com.tp.cubc.poc.ui.component.*
import java.util.*

@Composable
fun ComponentSampleScreen() {
    val pickedYearState = remember{ mutableStateOf(2000) }
    val pickedMonthState = remember{ mutableStateOf(5) }
    val pickedDateState = remember{ mutableStateOf(20) }

    var pickedDate by remember{ mutableStateOf(Date()) }

    TreeBg {
        Column(Modifier.fillMaxSize()) {
            TitleText("All components trying")

            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text("Email") },
                placeholder = { Text("Please Enter Email") },
            )
            Spacer(Modifier.height(16.dp))



            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text("Has Error") },
                isError = true
            )
            ErrorMessage("Oster test error")
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

            Spacer(Modifier.weight(1.0f))

        }
    }
}


@Preview(name = "phone", device = "spec:shape=Normal,width=375,height=790,unit=dp,dpi=480")
@Composable
private fun PreviewScreen() {
    ComponentSampleScreen()
}