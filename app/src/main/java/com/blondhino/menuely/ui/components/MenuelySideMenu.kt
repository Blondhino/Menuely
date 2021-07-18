package com.blondhino.menuely.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavHostController
import com.blondhino.menuely.R
import com.blondhino.menuely.data.common.Screen
import com.blondhino.menuely.data.common.enums.LoginStatus
import com.blondhino.menuely.ui.home.host.HostViewModel


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

    val loggedAsRestaurantSideMenuItems = listOf(
        Screen.UpdateRestaurantProfile
    )

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        val (image, content, bottomOptions) = createRefs()

        Image(
            painterResource(id = R.drawable.ic_menuely),
            "",
            modifier = Modifier
                .constrainAs(image) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .height(130.dp)
                .padding(16.dp)
        )

        Column(modifier = Modifier.constrainAs(bottomOptions) {
            bottom.linkTo(parent.bottom, 16.dp)
            end.linkTo(parent.end)
            start.linkTo(parent.start)
        }) {
            MenuelySideMenuItem(stringResource(id = R.string.logout), onClick = {
                onLogoutCalled()
            }
            )
            MenuelyAppVersion()
        }



        Column(
            modifier = Modifier.constrainAs(content) {
                top.linkTo(image.bottom, 24.dp)
                bottom.linkTo(bottomOptions.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints

            },
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            if (loginStatus==LoginStatus.LOGGED_AS_USER) {
                loggedAsUserSideMenuItems.forEach { option ->
                    MenuelySideMenuItem(itemTitle = stringResource(option.title), onClick = {
                        navController.navigate(option.route)
                        hostViewModel.isDrawerOpen.value = false
                    })
                }
            } else {
                loggedAsRestaurantSideMenuItems.forEach { option ->
                    MenuelySideMenuItem(itemTitle = stringResource(option.title), onClick = {
                        navController.navigate(option.route)
                        hostViewModel.isDrawerOpen.value = false
                    })
                }
            }
        }


    }
}


