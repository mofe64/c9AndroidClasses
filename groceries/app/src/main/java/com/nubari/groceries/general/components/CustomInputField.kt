package com.nubari.groceries.general.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.nubari.groceries.R


@Composable
fun CustomInputField(
    value: String,
    onValueChange: (String) -> Unit,
    onFocusChange: (FocusState) -> Unit,
    modifier: Modifier,
    headerText: String,
    hasError: Boolean,
    errorMessage: String,
    hasTrailingIcon: Boolean = false,
    hasLeadingIcon: Boolean = false,
    trailingIcon: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    imeAction: ImeAction = ImeAction.Next,
    keyboardType: KeyboardType = KeyboardType.Text,
    isPasswordInput: Boolean = false,
) {

    val touched = remember {
        mutableStateOf(false)
    }
    val passwordVisible = remember {
        mutableStateOf(false)
    }

    Column(modifier = modifier) {
        OutlinedTextField(
            value = value,
            onValueChange = {
                touched.value = true
                onValueChange(it)
            },
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged {
                    if (touched.value) onFocusChange(it)
                },
            label = {
                Text(text = headerText)
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = imeAction,
            ),
            trailingIcon = if (hasTrailingIcon) {
                if (isPasswordInput) {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            painter = painterResource(
                                id = if (passwordVisible.value) {
                                    R.drawable.ic_baseline_visibility_24
                                } else {
                                    R.drawable.ic_baseline_visibility_off_24
                                }
                            ),
                            contentDescription = ""
                        )
                    }
                }
                if (!isPasswordInput) {
                    trailingIcon
                } else {
                    {}
                }

            } else {
                {}
            },
            leadingIcon = if (hasLeadingIcon) {
                leadingIcon
            } else {
                {}
            }
        )
        if (hasError) {
            Text(text = errorMessage)
        }
    }

}