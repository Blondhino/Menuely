package com.blondhino.menuely.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.blondhino.menuely.R
import com.blondhino.menuely.ui.ui.theme.blackLight
import com.blondhino.menuely.ui.ui.theme.greenDark

@Composable
fun MenuelyUpdateDeleteSetActiveDialog(
    onUpdateCLick: () -> Unit,
    onDeleteClick: () -> Unit,
    onDismiss: () -> Unit,
    onSetActive: ()->Unit,
    unitName: String = ""
) {
    val isDialogOpen = remember { mutableStateOf(false) }
    val context = LocalContext.current
    if (!isDialogOpen.value) {
        AlertDialog(
            onDismissRequest = { isDialogOpen.value = false; onDismiss() },
            title = {

                Column(modifier = Modifier.fillMaxWidth()) {

                    Text(
                        text = "$unitName Options",
                        style = MaterialTheme.typography.h1,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth().padding(bottom = 40.dp)
                    )

                    MenuelySideMenuItem(
                        itemTitle = "Update $unitName",
                        onClick = { onUpdateCLick() },
                    )
                    MenuelySideMenuItem(
                        itemTitle = "Delete $unitName",
                        onClick = { onDeleteClick() })
                    MenuelySideMenuItem(
                        itemTitle = "Set active $unitName",
                        onClick = { onSetActive() })
                }

            },


            text = {

            },


            confirmButton = {

            },
            dismissButton = {

            },
            shape = RoundedCornerShape(20.dp),
            properties = DialogProperties()
        )
    }

}