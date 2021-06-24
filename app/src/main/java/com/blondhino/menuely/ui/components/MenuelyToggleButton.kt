package com.blondhino.menuely.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.blondhino.menuely.data.common.enums.LoginProcessType
import com.blondhino.menuely.data.common.enums.LoginProcessType.LOGIN_AS_RESTAURANT
import com.blondhino.menuely.data.common.enums.LoginProcessType.LOGIN_AS_USER
import com.blondhino.menuely.ui.ui.theme.greenLight
import com.blondhino.menuely.ui.ui.theme.greyLight


@Composable
fun MenuelyToggleButton(
    firstOptionName: String = "",
    secondOptionName: String = "",
    loginProcessType: LoginProcessType = LOGIN_AS_USER,
    onOptionSelected: (LoginProcessType) -> Unit,
    modifier: Modifier = Modifier
) {
    var backgroundColorOption1 = greenLight
    var textColorOption1 = Color.White
    var backgroundColorOption2 = Color.White
    var textColorOption2 = greyLight
    if (loginProcessType == LOGIN_AS_RESTAURANT) {
        backgroundColorOption1 = Color.White
        textColorOption1 = greyLight
        backgroundColorOption2 = greenLight
        textColorOption2 = Color.White
    }


    Row(modifier = modifier) {
        Card(
            shape = RoundedCornerShape(15.dp, 0.dp, 0.dp, 15.dp),
            backgroundColor = backgroundColorOption1
        ) {
            Box(modifier = Modifier.clickable { onOptionSelected(LOGIN_AS_USER) }){
                Text(
                    firstOptionName,
                    modifier = Modifier.padding(16.dp),
                    color = textColorOption1,
                    style = MaterialTheme.typography.h4
                )
            }
        }
        Card(
            shape = RoundedCornerShape(0.dp, 15.dp, 15.dp, 0.dp),
            backgroundColor = backgroundColorOption2
        ) {
            Box(modifier = Modifier.clickable { onOptionSelected(LOGIN_AS_RESTAURANT) }){
                Text(
                    secondOptionName,
                    modifier = Modifier.padding(16.dp),
                    color = textColorOption2,
                    style = MaterialTheme.typography.h4
                )
            }
        }
    }

}


@Composable
@Preview
fun previewDropDown() {
    MenuelyToggleButton("Private user", "Restaurant", LOGIN_AS_USER, {})
}