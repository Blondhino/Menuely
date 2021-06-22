package com.blondhino.menuely.ui.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.blondhino.menuely.R
import com.blondhino.menuely.data.common.NavigationRoutes
import com.blondhino.menuely.ui.components.MenuelyButton
import com.blondhino.menuely.ui.components.MenuelyCircularProgressBar
import com.blondhino.menuely.ui.components.MenuelyTextField

@Composable
fun registerScreen(
    navController: NavHostController,
    viewModel: OnBoardingViewModel,
    loading: Boolean = false,
) {

    Text(text = "Register")

}