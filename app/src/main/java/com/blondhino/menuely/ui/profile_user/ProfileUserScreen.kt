package com.blondhino.menuely.ui.profile_user

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.blondhino.menuely.R
import com.blondhino.menuely.ui.components.MenuelyHeader
import com.blondhino.menuely.ui.components.MenuelyInfoCard
import com.blondhino.menuely.util.generateAccountInfo
import com.blondhino.menuely.util.parseDate

@Composable
fun ProfileUserScreen(
    navController: NavHostController,
    viewModel: ProfileUserViewModel
) {
    viewModel.fetchUserData()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White), horizontalAlignment = Alignment.CenterHorizontally
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


}


