package com.blondhino.menuely.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.blondhino.menuely.ui.ui.theme.greenDark

@Composable
fun MenuelySnackBar(
    snackbarHostState: SnackbarHostState,
    onDismiss: () -> Unit
) {


    SnackbarHost(
        hostState = snackbarHostState,
        snackbar = { data ->
            Snackbar(
                modifier = Modifier
                    .padding(16.dp),
                action = {
                    data.actionLabel?.let { actionLabel ->
                        TextButton(onClick = { onDismiss() }) {
                            Text(text = actionLabel, style = MaterialTheme.typography.h4, color = greenDark)
                        }
                    }
                }
            ) {
                Text(text = data.message,style = MaterialTheme.typography.h5, color = Color.White)
            }
        }
    )
}