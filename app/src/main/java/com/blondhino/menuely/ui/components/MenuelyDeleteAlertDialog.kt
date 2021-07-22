package com.blondhino.menuely.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
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
import com.blondhino.menuely.util.GalleryImagePickerMenus

@Composable
fun MenuelyDeleteAlertDialog(
    onDeleteClick: () -> Unit,
    onDismiss: () -> Unit,
    unitName: String = "",
    unitTitle: String = ""
) {
    val isDialogOpen = remember { mutableStateOf(false) }
    val context = LocalContext.current
    if (!isDialogOpen.value) {
        AlertDialog(
            onDismissRequest = { isDialogOpen.value = false; onDismiss() },
            title = {

                Text(
                    text = "Delete $unitName ",
                    style = MaterialTheme.typography.h1,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 40.dp)
                )

            },


            text = {
                Text(
                    "Do you really want to delete : $unitTitle",
                    style = MaterialTheme.typography.h3,
                    fontSize = 14.sp
                )
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
                        stringResource(R.string.delete),
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .clickable {
                                onDeleteClick()
                            },
                        style = MaterialTheme.typography.h4,
                        fontSize = 14.sp,
                        color = greenDark
                    )
                }
            },
            dismissButton = {

            },
            shape = RoundedCornerShape(20.dp),
            properties = DialogProperties()
        )
    }

}