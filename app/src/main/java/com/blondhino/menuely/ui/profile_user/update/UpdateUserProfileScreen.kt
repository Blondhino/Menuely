package com.blondhino.menuely.ui.profile_user.update

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.blondhino.menuely.R
import com.blondhino.menuely.ui.components.MenuelyHeader
import com.blondhino.menuely.ui.components.MenuelyTextField
import com.blondhino.menuely.ui.profile_user.ProfileUserViewModel
import com.blondhino.menuely.util.GalleryImagePicker

@Composable
fun UpdateUserProfileScreen(profileUserViewModel: ProfileUserViewModel) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
    ) {

        MenuelyHeader(
            headerUrl = profileUserViewModel.userProfileModel.headerImageUrl.value,
            mainImageUrl = profileUserViewModel.userProfileModel.profileImageUrl.value,
            height = 220.dp
        )

        GalleryImagePicker(
            text = stringResource(R.string.change_cover),
            textStyle = MaterialTheme.typography.h5,
            onImageSelected = { uri, bitmap, multipart ->
                profileUserViewModel.updateCoverImage(multipart)
            }
        )

        GalleryImagePicker(
            text = stringResource(R.string.change_profile),
            textStyle = MaterialTheme.typography.h5,
            onImageSelected = { uri, bitmap, multipart ->
                profileUserViewModel.updateProfileImage(multipart)
            }
        )

        profileUserViewModel.userProfileModel.firstname.value?.let {
            MenuelyTextField(
                inputText = it,
                onInputTextChanged = {},
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
                onInputTextChanged = {},
                label = stringResource(id = R.string.lastName),
                modifier = Modifier
                    .fillMaxWidth(0.95F)
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 16.dp)
            )
        }


    }
}