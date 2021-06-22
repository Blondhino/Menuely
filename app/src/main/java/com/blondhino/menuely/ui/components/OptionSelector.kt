package com.blondhino.menuely.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.blondhino.menuely.ui.ui.theme.greenDark
import com.blondhino.menuely.ui.ui.theme.greenLight
import com.blondhino.menuely.ui.ui.theme.greyMedium

@Composable
fun OptionSelector(
    isSelected: Boolean = false,
    imageResource: Int = 0,
    optionTitle: String,
    onOptionClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(15.dp),
        backgroundColor = greyMedium,
    ) {
        Box(modifier = Modifier.clickable { onOptionClick() }) {
            Row(
                Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    painter = painterResource(id = imageResource),
                    contentDescription = "",
                    modifier = Modifier
                        .height(55.dp)
                )

                Text(text = optionTitle, style = MaterialTheme.typography.h3)

                RadioButton(selected = isSelected,
                    onClick = { onOptionClick() },
                    colors = RadioButtonDefaults.colors(selectedColor = greenLight)
                )
            }
        }
    }
}

@Composable
@Preview
fun previewOptionSelector() {
    OptionSelector(optionTitle = "This is title") {

    }
}