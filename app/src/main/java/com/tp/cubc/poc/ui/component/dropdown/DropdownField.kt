package com.tp.cubc.poc.ui.component.dropdown

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun <T: DropdownItemSelectable>DropdownField(
    modifier: Modifier = Modifier.wrapContentHeight(),
    value: DropdownItemSelectable? = null,
    items: List<T>? = listOf(),
    label: @Composable (() -> Unit)? = null,
    onValueChange: (T) -> Unit = {},
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    enabled: Boolean = true
) {
    var isExpanded by remember{ mutableStateOf(false) }
    val angle: Float by animateFloatAsState(
        targetValue = if (isExpanded) 0f else 180f
    )

    // Clickable - Fix the google bug
    val onClickSource = remember { MutableInteractionSource() }
    val onClick = { if (enabled) isExpanded = true}
    if (onClickSource.collectClickAsState().value) {
        onClick.invoke()
    }

    Box(modifier) {
        OutlinedTextField(
            value = value?.getLabel() ?: "",
            onValueChange = {},
            interactionSource = onClickSource,
            label = label,
            placeholder = placeholder,
            leadingIcon = leadingIcon,
            readOnly = true,
            trailingIcon = {
                Icon(
                    imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription = "ArrowDropDown",
                    modifier = Modifier.size(18.dp).rotate(angle),
                )
            },
            isError = isError,
            modifier = Modifier.fillMaxWidth().clickable(enabled = enabled, onClick = onClick)
        )

        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            items?.forEachIndexed { _, selectableItem ->
                DropdownMenuItem(onClick = {
                    onValueChange(selectableItem)
                    isExpanded = false
                }) {
                    Text(text = selectableItem.getLabel())
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
    DropdownField<DropdownItemSelectable>()
}