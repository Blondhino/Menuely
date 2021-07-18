package com.blondhino.menuely.util

import androidx.compose.animation.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

@ExperimentalAnimationApi
@Composable
fun EnterAnimation(content: @Composable () -> Unit) {
    AnimatedVisibility(
        visible = true,
        enter = slideInHorizontally(
            initialOffsetX = { -100 }
        ) /*+ expandVertically(
            expandFrom = Alignment.Top
        )*/ //+ fadeIn(initialAlpha = 0.3f),
        ,exit = slideOutHorizontally() /*+ shrinkVertically()*/ + fadeOut(),
        content = content,
        initiallyVisible = false
    )
}