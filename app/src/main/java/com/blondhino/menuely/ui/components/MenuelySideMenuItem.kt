package com.blondhino.menuely.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.blondhino.menuely.R

@Composable
fun MenuelySideMenuItem(itemTitle: String = "", onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
        ,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            itemTitle,
            modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 16.dp),
            style = MaterialTheme.typography.h3
        )
        Image(
            painter = painterResource(id = R.drawable.ic_arrow_right),
            contentDescription = "",
            modifier = Modifier
                .size(20.dp)
                .offset(x = (-16).dp)
        )
    }
}


@Composable
@Preview
fun previewItem() {
    MenuelySideMenuItem("item1", {})
}