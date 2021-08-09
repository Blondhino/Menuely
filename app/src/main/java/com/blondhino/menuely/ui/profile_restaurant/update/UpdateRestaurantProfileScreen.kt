package com.blondhino.menuely.ui.profile_restaurant.update

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.blondhino.menuely.R
import com.blondhino.menuely.ui.components.MenuelyCircularProgressBar
import com.blondhino.menuely.ui.components.MenuelyHeader
import com.blondhino.menuely.ui.components.MenuelyTextBox
import com.blondhino.menuely.ui.components.MenuelyTextField
import com.blondhino.menuely.ui.profile_restaurant.RestaurantViewModel

@Composable
fun UpdateRestaurantProfileScreen(restaurantViewModel: RestaurantViewModel) {
    val scrollState = rememberScrollState()

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .verticalScroll(scrollState),
        ) {

            MenuelyHeader(
                height = 220.dp,
                headerUrl = restaurantViewModel.myRestaurantProfile.coverImage.value,
                mainImageUrl = restaurantViewModel.myRestaurantProfile.profileImage.value,
                isInEditMode = true,
                onCartClicked = {},
                onMainImageSelected = { uri, bitmap, multipart ->
                    restaurantViewModel.updateProfileImage(
                        multipart
                    )
                }
            ) { uri, bitmap, multipart ->
                restaurantViewModel.updateCoverImage(
                    multipart
                )
            }

            MenuelyTextBox(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(100.dp),
                value = restaurantViewModel.myRestaurantProfile.description.value,
                onValueChange = { description ->
                    restaurantViewModel.myRestaurantProfile.description.value = description
                    restaurantViewModel.updateDescription(description)
                })

            restaurantViewModel.myRestaurantProfile.name.value?.let {
                MenuelyTextField(
                    inputText = it,
                    onInputTextChanged = { name ->
                        restaurantViewModel.myRestaurantProfile.name.value = name
                        restaurantViewModel.updateName(name)
                    },
                    label = stringResource(id = R.string.retaurantName),
                    modifier = Modifier
                        .fillMaxWidth(0.95F)
                        .padding(top = 24.dp)
                        .align(Alignment.CenterHorizontally)
                )
            }

            restaurantViewModel.myRestaurantProfile.address.value?.let {
                MenuelyTextField(
                    inputText = it,
                    onInputTextChanged = { address ->
                        restaurantViewModel.myRestaurantProfile.address.value = address
                        restaurantViewModel.updateAddress(address)
                    },
                    label = stringResource(id = R.string.address),
                    modifier = Modifier
                        .fillMaxWidth(0.95F)
                        .padding(top = 24.dp)
                        .align(Alignment.CenterHorizontally)
                )
            }


            restaurantViewModel.myRestaurantProfile.postalCode.value?.let {
                MenuelyTextField(
                    inputText = it,
                    onInputTextChanged = { postalCode ->
                        restaurantViewModel.myRestaurantProfile.postalCode.value = postalCode
                        restaurantViewModel.updatePostalCode(postalCode)
                    },
                    label = stringResource(id = R.string.postalCode),
                    modifier = Modifier
                        .fillMaxWidth(0.95F)
                        .padding(top = 24.dp)
                        .align(Alignment.CenterHorizontally)
                )
            }

            restaurantViewModel.myRestaurantProfile.city.value?.let {
                MenuelyTextField(
                    inputText = it,
                    onInputTextChanged = { city ->
                        restaurantViewModel.myRestaurantProfile.city.value = city
                        restaurantViewModel.updateCity(city)
                    },
                    label = stringResource(id = R.string.city),
                    modifier = Modifier
                        .fillMaxWidth(0.95F)
                        .padding(top = 24.dp)
                        .align(Alignment.CenterHorizontally)
                )
            }

            restaurantViewModel.myRestaurantProfile.country.value?.let {
                MenuelyTextField(
                    inputText = it,
                    onInputTextChanged = { country ->
                        restaurantViewModel.myRestaurantProfile.country.value = country
                        restaurantViewModel.updateCountry(country)
                    },
                    label = stringResource(id = R.string.country),
                    modifier = Modifier
                        .fillMaxWidth(0.95F)
                        .padding(top = 24.dp, bottom = 24.dp)
                        .align(Alignment.CenterHorizontally)
                )
            }


        }
        MenuelyCircularProgressBar(isLoading = restaurantViewModel.isLoading.value)

    }
}