package com.blondhino.menuely.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.blondhino.menuely.R
import com.blondhino.menuely.ui.ui.theme.*

@Composable
fun MenuelySearchBox(
    onValueChanged: (value: String) -> Unit,
    value: String = "",
    hintText:String ="Search for restaurants"
) {

    OutlinedTextField(
        value = value,
        onValueChange = { onValueChanged(it) },
        leadingIcon = {
            Icon(
                painterResource(id = R.drawable.ic_search),
                tint = greyLight,
                contentDescription = "lololol",
                modifier = Modifier.width(20.dp)
            )
        },
        textStyle = MaterialTheme.typography.caption,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(50.dp)
        ,
        shape = RoundedCornerShape(50),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = greenLight,
            unfocusedBorderColor = greyLight

        ),
        placeholder = {
            Text(
                text = hintText,
                fontFamily = FontFamily(Font(R.font.montserrat_semi_bold)),
                fontSize = 11.sp,
                modifier = Modifier
                    .height(45.dp)
                ,
                color = greyLight
            )
        }
    )
}






