package com.blondhino.menuely.ui.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.blondhino.menuely.ui.ui.theme.greyMedium

@Composable
fun MenuelyJumpingProgressBar(
    isLoading: Boolean = false
) {

    val animatedHeight by animateDpAsState(targetValue = (if(isLoading)60.dp else 0.dp))
    val animatedAlpha by animateFloatAsState(targetValue = (if(isLoading)1F else 0F))

    Row(
        modifier = Modifier
            .height(animatedHeight)
            .fillMaxWidth()
            .background(Color.White)
            .alpha(animatedAlpha)
        ,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator()
    }

}


@Composable
@Preview
fun previewJumpingProgressBar() {
    MenuelyJumpingProgressBar(isLoading = true)
}