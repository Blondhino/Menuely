package com.blondhino.menuely.ui.onboarding

import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.blondhino.menuely.ui.base.BaseComposeActivity
import com.blondhino.menuely.ui.components.MenuelyTextField
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnBoardingActivity : BaseComposeActivity() {
    val viewModel: OnBoardingViewModel by viewModels()
    override fun setLayout(): @Composable () -> Unit = {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            MenuelyTextField(
                inputText = viewModel.loginModel.email.value,
                onInputTextChanged = {viewModel.loginModel.onEmailChanged(it)},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .offset(y = 50.dp)
                ,
                label = "Email",
                keyboardType = KeyboardType.Email
            )
            MenuelyTextField(
                inputText = viewModel.loginModel.password.value ,
                onInputTextChanged = {viewModel.loginModel.onPasswordChanged(it)},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .offset(y = 50.dp)
                ,
                label = "Password",
                keyboardType = KeyboardType.Password
            )



        }

    }


    override fun fetchData() {}

    override fun observeData() {}

    override fun setupUi() {}

}