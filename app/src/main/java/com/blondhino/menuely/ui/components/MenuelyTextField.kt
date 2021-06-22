package com.blondhino.menuely.ui.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue // only if using var
import com.blondhino.menuely.R
import com.blondhino.menuely.ui.ui.theme.greenDark
import com.blondhino.menuely.ui.ui.theme.greyLight


@Composable
fun MenuelyTextField(
    inputText: String,
    onInputTextChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String = "",
    keyboardType: KeyboardType = KeyboardType.Text,
) {
    var passwordVisibility: Boolean by remember { mutableStateOf(false) }
    var visualTransformation: VisualTransformation by remember {
        mutableStateOf(
            PasswordVisualTransformation()
        )
    }

    TextField(
        value = inputText,
        onValueChange = { onInputTextChanged(it) },
        modifier = modifier,
        label = { Text(text = label, fontFamily = FontFamily(Font(R.font.montserrat_medium))) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedLabelColor = if (inputText.isEmpty()) greyLight else greenDark,
            backgroundColor = Color.Transparent,
            unfocusedBorderColor = greyLight
        ),
        textStyle = MaterialTheme.typography.h3,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        trailingIcon = {
            if (keyboardType == KeyboardType.Password || keyboardType == KeyboardType.NumberPassword) {
                IconButton(onClick = {
                    passwordVisibility = !passwordVisibility
                    if (passwordVisibility) {
                        visualTransformation =
                            VisualTransformation.None
                    } else {
                        visualTransformation = PasswordVisualTransformation()
                    }
                }) {
                    val image = if (passwordVisibility) {
                        Icons.Filled.Visibility
                    } else {
                        Icons.Filled.VisibilityOff

                    }

                    Icon(imageVector = image, "")
                }
            } else {
                visualTransformation =
                    VisualTransformation.None
            }

        },
        visualTransformation = visualTransformation

    )

}

