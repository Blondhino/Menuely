package com.blondhino.menuely.ui.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController

@Composable
fun registerScreen(navController: NavHostController, viewModel: OnBoardingViewModel) {
    Column(modifier = Modifier.fillMaxSize().background(Color.White)) {
        Text("This is register")
    }
}