package com.blondhino.menuely.ui.profile_user

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.blondhino.menuely.ui.components.MenuelyHeader

@Composable
fun ProfileUserScreen(
    navController: NavHostController,
    viewModel: ProfileUserViewModel
) {
    viewModel.fetchUserData()

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        val (header) = createRefs()

        MenuelyHeader(
            modifier = Modifier.constrainAs(header) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            headerUrl = viewModel.userProfileModel.headerImageUrl.value,
            mainImageUrl = viewModel.userProfileModel.profileImageUrl.value,
            height = 220.dp
        )

    }
}


