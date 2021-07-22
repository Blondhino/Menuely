package com.blondhino.menuely.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.blondhino.menuely.R
import com.blondhino.menuely.data.common.enums.Mode
import com.blondhino.menuely.data.common.enums.Mode.*
import com.blondhino.menuely.ui.ui.theme.blackLight
import com.blondhino.menuely.ui.ui.theme.greenDark

@Composable
fun MenuelyCreateMenuDialog(
    onDismiss: () -> Unit,
    onSave: () -> Unit,
    onUpdate: () -> Unit,
    mode: Mode = CREATE,
    menuNameValue: String = "",
    onMenuNameValueChange: (value: String) -> Unit,
    currencyValue: String = "",
    onCurrencyValueChange: (value: String) -> Unit,
    numberOfTablesValue: String = "",
    onNumberOfTablesChange: (value: String) -> Unit,
    descriptionValue: String = "",
    onDescriptionValueChange: (value: String) -> Unit
) {
    val isDialogOpen = remember { mutableStateOf(false) }
    val context = LocalContext.current
    if (!isDialogOpen.value) {
        AlertDialog(
            onDismissRequest = { isDialogOpen.value = false; onDismiss() },
            title = {
                Text(
                    text = stringResource(R.string.menu),
                    style = MaterialTheme.typography.h1,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            },


            text = {

                Column() {
                    MenuelyTextField(
                        inputText = menuNameValue,
                        onInputTextChanged = { onMenuNameValueChange(it) },
                        label = stringResource(R.string.menu_name),
                        modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
                    )
                    MenuelyTextField(
                        inputText = currencyValue,
                        onInputTextChanged = { onCurrencyValueChange(it) },
                        label = stringResource(R.string.currency),
                        modifier = Modifier.padding(vertical = 8.dp)
                    )

                    MenuelyTextField(
                        inputText = numberOfTablesValue,
                        onInputTextChanged = { onNumberOfTablesChange(it) },
                        label = stringResource(R.string.num_of_tables),
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                            .height(if(mode==CREATE) 55.dp else 0.dp)
                            .alpha(if (mode == CREATE) 1F else 0F),
                        keyboardType = KeyboardType.NumberPassword
                    )
                }

            },


            confirmButton = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        stringResource(R.string.cancel),
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .clickable {
                                onDismiss()
                            },
                        style = MaterialTheme.typography.h4,
                        fontSize = 14.sp,
                        color = blackLight
                    )
                    Text(
                        stringResource(R.string.save),
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .clickable {
                                if (mode == CREATE) {
                                    onSave()
                                } else {
                                    onUpdate()
                                }
                            },
                        style = MaterialTheme.typography.h4,
                        fontSize = 14.sp,
                        color = greenDark
                    )
                }
            },
            dismissButton = {
                MenuelyTextBox(
                    value = descriptionValue,
                    onValueChange = { onDescriptionValueChange(it) }
                )
            },
            shape = RoundedCornerShape(20.dp),
            properties = DialogProperties()
        )
    }

}