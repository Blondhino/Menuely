package com.blondhino.menuely.ui.onboarding.register

import android.graphics.Color
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavHostController
import com.blondhino.menuely.R
import com.blondhino.menuely.data.common.NavigationRoutes
import com.blondhino.menuely.data.common.RegistrationProcessType
import com.blondhino.menuely.data.common.RegistrationProcessType.*
import com.blondhino.menuely.ui.components.MenuelyButton
import com.blondhino.menuely.ui.components.MenuelyCircularProgressBar
import com.blondhino.menuely.ui.components.MenuelyTextField
import com.blondhino.menuely.ui.components.OptionSelector
import com.blondhino.menuely.ui.onboarding.OnBoardingViewModel
import com.blondhino.menuely.ui.ui.theme.greenLight

@Composable
fun RegisterAsUserScreen(viewModel: OnBoardingViewModel, navController: NavHostController) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
   Box(){
       ConstraintLayout(modifier = Modifier.fillMaxSize()) {
           val (image, container, button) = createRefs()


           Image(
               painter = painterResource(id = R.drawable.user_ico),
               contentDescription = "",
               modifier = Modifier
                   .height(90.dp)
                   .fillMaxWidth()
                   .padding(top = 16.dp)
                   .background(androidx.compose.ui.graphics.Color.White)
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
                   inputText = viewModel.registerAsUserModel.email.value,
                   onInputTextChanged = { viewModel.registerAsUserModel.onEmailChanged(it) },
                   modifier = Modifier
                       .fillMaxWidth()
                       .padding(16.dp),
                   label = stringResource(
                       R.string.email
                   )
               )
               MenuelyTextField(
                    inputText = viewModel.registerAsUserModel.firstName.value,
                    onInputTextChanged = { viewModel.registerAsUserModel.onFirstNameChanged(it) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    label = stringResource(
                        R.string.firstName
                    )
                )
                MenuelyTextField(
                    inputText = viewModel.registerAsUserModel.lastName.value,
                    onInputTextChanged = { viewModel.registerAsUserModel.onLastNameChanged(it) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    label = stringResource(
                        R.string.lastName
                    )
                )
                MenuelyTextField(
                    inputText = viewModel.registerAsUserModel.password.value,
                    onInputTextChanged = { viewModel.registerAsUserModel.onPasswordChanged(it) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    label = stringResource(
                        R.string.password
                    ),
                    keyboardType = KeyboardType.Password
                )
                MenuelyTextField(
                    inputText = viewModel.registerAsUserModel.retypePassword.value,
                    onInputTextChanged = { viewModel.registerAsUserModel.onRetypePasswordChanged(it) },
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
                   viewModel.register(REGISTER_AS_USER)
               })
       }
       MenuelyCircularProgressBar(isLoading = viewModel.loading.value)
   }

}