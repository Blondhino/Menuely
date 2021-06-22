package com.blondhino.menuely.ui.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.blondhino.menuely.ui.components.MenuelyTextField
import com.blondhino.menuely.R
import com.blondhino.menuely.data.common.NavigationRoutes.REGISTER_SCREEN
import com.blondhino.menuely.ui.components.MenuelyButton
import com.blondhino.menuely.ui.components.MenuelyCircularProgressBar

@Composable
fun loginScreen(
    navController: NavHostController,
    viewModel: OnBoardingViewModel,
    loading: Boolean = false,
    errorText: String
) {
    Scaffold() {
        Box(modifier = Modifier.fillMaxSize()) {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
            ) {
                val (image, container, loginButton) = createRefs()


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

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .constrainAs(container) {
                            top.linkTo(image.bottom)
                            bottom.linkTo(loginButton.top, 16.dp)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        },
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    MenuelyTextField(
                        inputText = viewModel.loginModel.email.value,
                        onInputTextChanged = { viewModel.loginModel.onEmailChanged(it) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        label = stringResource(R.string.email),
                        keyboardType = KeyboardType.Email
                    )

                    MenuelyTextField(
                        inputText = viewModel.loginModel.password.value,
                        onInputTextChanged = { viewModel.loginModel.onPasswordChanged(it) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        label = stringResource(R.string.password),
                        keyboardType = KeyboardType.Password
                    )

                    Text(
                        stringResource(R.string.donthaveAcc),
                        style = MaterialTheme.typography.h5,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                    Text(stringResource(R.string.registerHere),
                        style = MaterialTheme.typography.h4,
                        modifier = Modifier
                            .padding(8.dp)
                            .clickable(
                                enabled = true,
                                role = Role.Button
                            ) {
                                navController.navigate(REGISTER_SCREEN)
                            })
                }



                MenuelyButton(
                    onClick = { viewModel.loginUser() },
                    modifier = Modifier
                        .constrainAs(loginButton) {
                            bottom.linkTo(parent.bottom, 16.dp)
                        }
                        .padding(16.dp)
                        .fillMaxWidth(),
                    text = stringResource(R.string.login)
                )

            }
            MenuelyCircularProgressBar(isLoading = loading)
        }
    }

}

