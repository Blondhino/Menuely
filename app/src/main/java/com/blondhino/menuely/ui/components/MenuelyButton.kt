package com.blondhino.menuely.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.blondhino.menuely.ui.ui.theme.greenDark
import com.blondhino.menuely.ui.ui.theme.greenLight

@Composable
fun MenuelyButton(
    modifier: Modifier = Modifier,
    text: String ="",
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier,

        contentPadding = PaddingValues(),
        onClick = { onClick() },
        shape = RoundedCornerShape(15.dp)
    ) {
        rememberRipple(color = Color.White)
        Box(
            modifier = Modifier
                .background(Brush.verticalGradient(listOf(greenLight, greenDark)))
                .then(modifier),
            contentAlignment = Alignment.Center,
        ) {
            Text(text = text, style = MaterialTheme.typography.h2, color = Color.White)
            rememberRipple(color = Color.White)
        }
    }
}


@Composable
@Preview
fun preview(){
    MenuelyButton(text = "Button") {

    }
}