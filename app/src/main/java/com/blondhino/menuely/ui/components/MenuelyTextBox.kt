package com.blondhino.menuely.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.blondhino.menuely.ui.ui.theme.greenDark
import com.blondhino.menuely.ui.ui.theme.greyLight

@Composable
fun MenuelyTextBox(
    value: String = "",
    onValueChange : (value:String) ->Unit
    ,modifier: Modifier = Modifier
        .height(100.dp)
        .fillMaxWidth()

) {
    OutlinedTextField(
        value = value,
        placeholder = {
            Text(
                text = "Write description...",
                style = MaterialTheme.typography.h3,
                fontSize = 12.sp,
                color = greyLight
            )
        },
        onValueChange = {onValueChange(it)},
        modifier = modifier,
        shape = RoundedCornerShape(15.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = greyLight,
            focusedLabelColor = greenDark,
            placeholderColor = greyLight
        ),
        textStyle = MaterialTheme.typography.h3,
        maxLines = 5
    )
}


