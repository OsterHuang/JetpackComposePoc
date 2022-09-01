package com.tp.cubc.poc.transfer

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.tp.cubc.poc.R
import com.tp.cubc.poc.ui.bg.TreeBg

@Composable
fun TransferInputScreen() {

    var expanded by remember { mutableStateOf(false) }
    val suggestions = listOf("Item1", "Item2", "Item3")

    TreeBg {
        Column(Modifier.fillMaxSize()) {
            Text(
                stringResource(id = R.string.transfer),
                color = colorResource(R.color.white)
            )

            Box {
                Button(onClick = { expanded = !expanded }){
                    Text ("DropDown")
                    Icon(
                        imageVector = Icons.Filled.ArrowDropDown,
                        contentDescription = null,
                    )
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                ) {
                    suggestions.forEach { label ->
                        DropdownMenuItem(onClick = {
                            expanded = false
                            //do something ...
                        }) {
                            Text(text = label)
                        }
                    }
                }
            }
        }
    }
}


@Preview(name = "phone", device = "spec:shape=Normal,width=375,height=790,unit=dp,dpi=480")
@Composable
private fun PreviewScreen() {
    TransferInputScreen()
}