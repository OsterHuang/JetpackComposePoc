package com.tp.cubc.poc.ui.component

import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay

@Composable
fun DropdownField(
    value: String = "",
    label: @Composable (() -> Unit)? = null,
    onValueChange: (String) -> Unit = {},
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    enabled: Boolean = true
) {
    var isExpanded by remember{ mutableStateOf(false) }
    val angle: Float by animateFloatAsState(
        targetValue = if (isExpanded) 0f else 180f
    )

    val items = listOf("A", "B", "C", "D", "E", "F")
    var selectedIndex by remember { mutableStateOf(0) }

    // Clickable - Fix the google bug
    val onClickSource = remember { MutableInteractionSource() }
    val onClick = { if (enabled) isExpanded = true}
    if (onClickSource.collectClickAsState().value) {
        onClick.invoke()
    }

    Box {
        OutlinedTextField(
            value = items[selectedIndex],
            interactionSource = onClickSource,
            onValueChange = onValueChange,
            label = label,
            placeholder = placeholder,
            leadingIcon = leadingIcon,
            readOnly = true,
            trailingIcon = {
                Icon(
                    imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription = "ArrowDropDown",
                    modifier = Modifier.rotate(angle),
                )
            },
            isError = isError,
            modifier = Modifier.clickable(enabled = enabled, onClick = onClick)
        )

        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            items.forEachIndexed { index, s ->
                DropdownMenuItem(onClick = {
                    selectedIndex = index
                    isExpanded = false
                }) {
                    Text(text = s)
                }
            }
        }
    }
}

@Composable
fun InteractionSource.collectClickAsState(): State<Boolean> {
    val onClick = remember { mutableStateOf(false) }
    LaunchedEffect(this) {
        var wasPressed = false
        interactions.collect { interaction ->
            when (interaction) {
                is PressInteraction.Press -> {
                    wasPressed = true
                }
                is PressInteraction.Release -> {
                    if (wasPressed) onClick.value = true
                    wasPressed = false
                }
                is PressInteraction.Cancel -> {
                    wasPressed = false
                }
            }
            // reset state with some delay otherwise it can re-emit the previous state
            delay(10L)
            onClick.value = false
        }
    }
    return onClick
}

@Preview
@Composable
private fun previewComponent() {
    DropdownField()
}