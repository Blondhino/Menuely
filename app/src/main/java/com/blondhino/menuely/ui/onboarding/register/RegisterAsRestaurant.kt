package com.blondhino.menuely.ui.onboarding.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavHostController
import com.blondhino.menuely.R
import com.blondhino.menuely.data.common.enums.RegistrationProcessType
import com.blondhino.menuely.ui.components.MenuelyButton
import com.blondhino.menuely.ui.components.MenuelyCircularProgressBar
import com.blondhino.menuely.ui.components.MenuelyTextField
import com.blondhino.menuely.ui.onboarding.OnBoardingViewModel

@Composable
fun RegisterAsRestaurantScreen(navController: NavHostController, viewModel: OnBoardingViewModel) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    Box() {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (image, container, button) = createRefs()


            Image(
                painter = painterResource(id = R.drawable.restaurant_ico),
                contentDescription = "",
                modifier = Modifier
                    .height(90.dp)
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .background(Color.White)
                    .constrainAs(image) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                        start.linkTo(parent.start)
                    }
            )
            Column(modifier = Modifier
                .constrainAs(container) {
                    top.linkTo(image.bottom, 16.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(button.top)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                }
                .verticalScroll(scrollState),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                MenuelyTextField(
                    inputText = viewModel.registerAsRestaurantModel.email.value,
                    onInputTextChanged = { viewModel.registerAsRestaurantModel.onEmailChanged(it) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    label = stringResource(
                        R.string.email
                    )
                )
                MenuelyTextField(
                    inputText = viewModel.registerAsRestaurantModel.restaurantName.value,
                    onInputTextChanged = { viewModel.registerAsRestaurantModel.onRestaurantNameChanged(it) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    label = stringResource(
                        R.string.retaurantName
                    )
                )
                MenuelyTextField(
                    inputText = viewModel.registerAsRestaurantModel.address.value,
                    onInputTextChanged = { viewModel.registerAsRestaurantModel.onAddressChanged(it) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    label = stringResource(
                        R.string.address
                    )
                )
                MenuelyTextField(
                    inputText = viewModel.registerAsRestaurantModel.postalCode.value,
                    onInputTextChanged = { viewModel.registerAsRestaurantModel.onPostalCodeChanged(it) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    label = stringResource(
                        R.string.postalCode
                    )
                )
                MenuelyTextField(
                    inputText = viewModel.registerAsRestaurantModel.city.value,
                    onInputTextChanged = { viewModel.registerAsRestaurantModel.onCityChanged(it) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    label = stringResource(
                        R.string.city
                    )
                )
                MenuelyTextField(
                    inputText = viewModel.registerAsRestaurantModel.country.value,
                    onInputTextChanged = { viewModel.registerAsRestaurantModel.onCountryChanged(it) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    label = stringResource(
                        R.string.country
                    )
                )
                MenuelyTextField(
                    inputText = viewModel.registerAsRestaurantModel.description.value,
                    onInputTextChanged = { viewModel.registerAsRestaurantModel.onDescriptionChanged(it) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    label = stringResource(
                        R.string.description
                    )
                )
                MenuelyTextField(
                    inputText = viewModel.registerAsRestaurantModel.password.value,
                    onInputTextChanged = { viewModel.registerAsRestaurantModel.onPasswordChanged(it) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    label = stringResource(
                        R.string.password
                    ),
                    keyboardType = KeyboardType.Password
                )
                MenuelyTextField(
                    inputText = viewModel.registerAsRestaurantModel.retypePassword.value,
                    onInputTextChanged = { viewModel.registerAsRestaurantModel.onRetypePasswordChanged(it) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    label = stringResource(
                        R.string.rePassword
                    ),
                    keyboardType = KeyboardType.Password
                )


            }

            MenuelyButton(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .constrainAs(button) {
                    bottom.linkTo(parent.bottom, 16.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
                text = stringResource(R.string.register),
                onClick = {
                    viewModel.register(RegistrationProcessType.REGISTER_AS_RESTAURANT)
                })
        }
        MenuelyCircularProgressBar(isLoading = viewModel.loading.value)
    }
}