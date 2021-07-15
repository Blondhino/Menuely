package com.blondhino.menuely.ui.onboarding

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.blondhino.menuely.R
import com.blondhino.menuely.data.common.constants.NavigationRoutes.REGISTER_AS_RESTAURANT_SCREEN
import com.blondhino.menuely.data.common.constants.NavigationRoutes.REGISTER_AS_USER_SCREEN
import com.blondhino.menuely.data.common.enums.RegistrationProcessType.*
import com.blondhino.menuely.ui.components.MenuelyButton
import com.blondhino.menuely.ui.components.OptionSelector

@Composable
fun registerScreen(
    navController: NavHostController,
    viewModel: OnBoardingViewModel,
    loading: Boolean = false,
) {
    val context = LocalContext.current
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (image, container, button) = createRefs()


        Image(
            painter = painterResource(id = R.drawable.ic_menuely),
            contentDescription = "",
            modifier = Modifier
                .width(80.dp)
                .padding(top = 16.dp)
                .constrainAs(image) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                }
        )
        Column(modifier = Modifier
            .constrainAs(container) {
                top.linkTo(image.bottom)
                start.linkTo(parent.start)
                bottom.linkTo(button.top)
                end.linkTo(parent.end)
            }
            .fillMaxWidth(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "I want to register as:",
                style = MaterialTheme.typography.h5,
                modifier = Modifier.padding(16.dp)
            )

            OptionSelector(
                optionTitle = "Private person",
                imageResource = R.drawable.user_ico,
                isSelected = viewModel.registrationProcessModel.registerAsUser.value,
                onOptionClick = {
                    viewModel.registrationProcessModel.selectRegisterAsUser();
                })

            OptionSelector(
                optionTitle = "Restaurant",
                imageResource = R.drawable.restaurant_ico,
                isSelected = viewModel.registrationProcessModel.registerAsRestaurant.value,
                onOptionClick = {
                    viewModel.registrationProcessModel.selectRegisterAsRestaurant();
                })

        }

        MenuelyButton(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .constrainAs(button) {
                bottom.linkTo(parent.bottom, 16.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            text = "Next",
            onClick = {
                when (viewModel.registrationProcessModel.getSelection()) {
                    UNDEFINED -> {
                        viewModel.messageText.value = context.getString(R.string.please_select_reg)
                    }
                    REGISTER_AS_USER -> {
                        navController.navigate(REGISTER_AS_USER_SCREEN)
                    }
                    REGISTER_AS_RESTAURANT -> {
                        navController.navigate(REGISTER_AS_RESTAURANT_SCREEN)
                    }
                }
            })

    }

}