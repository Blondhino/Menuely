package com.blondhino.menuely.ui.profile_user

import android.util.Log
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
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.blondhino.menuely.R
import com.blondhino.menuely.ui.components.MenuelyHeader
import com.blondhino.menuely.ui.components.MenuelyInfoCard
import com.blondhino.menuely.ui.home.host.HostViewModel
import com.blondhino.menuely.util.generateAccountInfo
import com.blondhino.menuely.util.parseDate

@Composable
fun ProfileUserScreen(
    navController: NavHostController,
    viewModel: ProfileUserViewModel,
    hostViewModel: HostViewModel
) {
    viewModel.fetchUserData()
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        val (menuOptions, content, menu) = createRefs()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .constrainAs(content) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }, horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MenuelyHeader(
                headerUrl = viewModel.userProfileModel.headerImageUrl.value,
                mainImageUrl = viewModel.userProfileModel.profileImageUrl.value,
                height = 220.dp
            )
            Text(
                text = viewModel.userProfileModel.firstname.value + " " + viewModel.userProfileModel.lastname.value,
                style = MaterialTheme.typography.body2,
                modifier = Modifier.padding(top = 8.dp)
            )

            viewModel.userProfileModel.email.value?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(top = 8.dp, bottom = 32.dp)
                )
            }

            MenuelyInfoCard(
                "Account info:",
                generateAccountInfo(
                    viewModel.userProfileModel.createdAt.value,
                    viewModel.userProfileModel.updatedAt.value
                ),
                R.drawable.ic_profile_green,
            )
        }

        Image(
            painter = painterResource(id = R.drawable.ic_hamburger_menu),
            "",
            modifier = Modifier
                .constrainAs(menuOptions) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                }
                .padding(16.dp)
                .size(20.dp)
                .clickable { hostViewModel.isDrawerOpen.value = true }
        )

    }


}


