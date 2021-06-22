package com.blondhino.menuely.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun HomeScreen() {

    Text("This is home screen", modifier = Modifier
        .fillMaxSize()
        .background(Color.White))
}