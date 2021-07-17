package com.blondhino.menuely.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.blondhino.menuely.R

@Composable
fun MenuelyEmptyState(visible: Boolean = false, title: String = "", subtitle: String = "") {

    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth()
            .height(if(visible)300.dp else 0.dp)
            .padding(top =64.dp)
            .alpha(if (visible) 1F else 0F),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = R.drawable.ic_empty_state),
            contentDescription = "",
            modifier = Modifier.height(90.dp)
        )
        Text(
            text = title,
            style = MaterialTheme.typography.h2,
            fontSize = 24.sp,
            modifier = Modifier.padding(16.dp)
        )

        Text(
            text = subtitle,
            style = MaterialTheme.typography.h5,
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 16.dp)
        )


    }

}


@Composable
@Preview
fun previewEmptyState() {
    MenuelyEmptyState(visible = true, title = "Oops", subtitle = "Nothing found")
}