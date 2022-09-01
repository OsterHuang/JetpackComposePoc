package com.tp.cubc.poc.example

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tp.cubc.poc.ui.bg.TreeBg
import com.tp.cubc.poc.ui.component.DropdownField
import com.tp.cubc.poc.ui.component.ErrorMessage
import com.tp.cubc.poc.ui.component.TitleText

@Composable
fun ComponentSampleScreen() {

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

            Spacer(Modifier.weight(1.0f))
        }
    }
}


@Preview(name = "phone", device = "spec:shape=Normal,width=375,height=790,unit=dp,dpi=480")
@Composable
private fun PreviewScreen() {
    ComponentSampleScreen()
}