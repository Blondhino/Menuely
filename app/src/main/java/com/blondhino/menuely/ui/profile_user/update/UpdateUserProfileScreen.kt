package com.blondhino.menuely.ui.profile_user.update

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.blondhino.menuely.R
import com.blondhino.menuely.ui.components.MenuelyCircularProgressBar
import com.blondhino.menuely.ui.components.MenuelyHeader
import com.blondhino.menuely.ui.components.MenuelyTextField
import com.blondhino.menuely.ui.profile_user.ProfileUserViewModel

@Composable
fun UpdateUserProfileScreen(profileUserViewModel: ProfileUserViewModel) {


    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
        ) {

            MenuelyHeader(
                height = 220.dp,
                headerUrl = profileUserViewModel.userProfileModel.headerImageUrl.value,
                mainImageUrl = profileUserViewModel.userProfileModel.profileImageUrl.value,
                isInEditMode = true,
                onCartClicked = {},
                onMainImageSelected = { uri, bitmap, multipart ->
                    profileUserViewModel.updateProfileImage(
                        multipart
                    )
                }
            ) { uri, bitmap, multipart ->
                profileUserViewModel.updateCoverImage(
                    multipart
                )
            }

            profileUserViewModel.userProfileModel.firstname.value?.let {
                MenuelyTextField(
                    inputText = it,
                    onInputTextChanged = { firstName ->
                        profileUserViewModel.userProfileModel.firstname.value = firstName
                        profileUserViewModel.updateFirstName(firstName)
                    },
                    label = stringResource(id = R.string.firstName),
                    modifier = Modifier
                        .fillMaxWidth(0.95F)
                        .padding(top = 24.dp)
                        .align(Alignment.CenterHorizontally)
                )
            }

            profileUserViewModel.userProfileModel.lastname.value?.let {
                MenuelyTextField(
                    inputText = it,
                    onInputTextChanged = { lastName ->
                        profileUserViewModel.userProfileModel.lastname.value = lastName
                        profileUserViewModel.updateLastName(lastName)
                    },
                    label = stringResource(id = R.string.lastName),
                    modifier = Modifier
                        .fillMaxWidth(0.95F)
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 16.dp)
                )
            }


        }
        MenuelyCircularProgressBar(isLoading = profileUserViewModel.isLoading.value)

    }
}