package com.blondhino.menuely.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.blondhino.menuely.R
import com.blondhino.menuely.data.common.Screen
import com.blondhino.menuely.data.common.constants.NavigationRoutes.UPDATE_USER_PROFILE_SCREEN
import com.blondhino.menuely.data.common.enums.LoginStatus
import com.blondhino.menuely.ui.home.host.HostViewModel
import kotlinx.coroutines.GlobalScope


@Composable
fun MenuelySideMenu(
    loginStatus: LoginStatus,
    onLogoutCalled: () -> Unit,
    navController: NavHostController,
    hostViewModel: HostViewModel
) {

    val loggedAsUserSideMenuItems = listOf(
        Screen.UpdateUserProfile,
        Screen.UpdateUserProfile,
        Screen.UpdateUserProfile,
        Screen.UpdateUserProfile
    )
    val context = LocalContext.current

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        val (image, content, logout) = createRefs()

        Text("Logout", modifier = Modifier
            .padding(16.dp)
            .constrainAs(logout) {
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end)
                start.linkTo(parent.start)

            }
            .clickable { onLogoutCalled() })

        Column(
            modifier = Modifier.constrainAs(content) {
                top.linkTo(parent.top)
                bottom.linkTo(logout.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints

            },
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            loggedAsUserSideMenuItems.forEach { option ->
                MenuelySideMenuItem(itemTitle = context.getString(option.title)) {
                    navController.navigate(option.route)
                    hostViewModel.isDrawerOpen.value = false
                }
            }
        }
    }
}


