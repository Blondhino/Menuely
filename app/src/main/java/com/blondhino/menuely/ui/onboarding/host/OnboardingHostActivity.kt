package com.blondhino.menuely.ui.onboarding.host

import android.content.Intent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.blondhino.menuely.R
import com.blondhino.menuely.data.common.constants.IntentConstants.LOGGED_STATUS_INTENT_VALUE
import com.blondhino.menuely.data.common.enums.LoginStatus
import com.blondhino.menuely.data.common.enums.LoginStatus.*
import com.blondhino.menuely.data.common.constants.NavigationRoutes.LOGIN_SCREEN
import com.blondhino.menuely.data.common.constants.NavigationRoutes.REGISTER_AS_RESTAURANT_SCREEN
import com.blondhino.menuely.data.common.constants.NavigationRoutes.REGISTER_AS_USER_SCREEN
import com.blondhino.menuely.data.common.constants.NavigationRoutes.REGISTER_SCREEN
import com.blondhino.menuely.data.repo.OnBoardingRepo
import com.blondhino.menuely.ui.base.BaseComposeActivity
import com.blondhino.menuely.ui.components.MenuelySnackBar
import com.blondhino.menuely.ui.home.host.HomeHostActivity
import com.blondhino.menuely.ui.onboarding.*
import com.blondhino.menuely.ui.onboarding.register.RegisterAsRestaurantScreen
import com.blondhino.menuely.ui.onboarding.register.RegisterAsUserScreen
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionRequired
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.rememberPermissionState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class OnboardingHostActivity : BaseComposeActivity() {

    @Inject
    lateinit var onBoardingRepo: OnBoardingRepo
    private val viewModel: SplashViewModel by viewModels()
    private val onBoardingViewModel: OnBoardingViewModel by viewModels()
    lateinit var navController: NavHostController


    @OptIn(ExperimentalPermissionsApi::class)
    override fun setLayout(): @Composable () -> Unit = {
        val loading = onBoardingViewModel.loading
        val messageText = onBoardingViewModel.messageText
        navController = rememberNavController()
        val scaffoldState = rememberScaffoldState()
        val cameraPermissionState = rememberPermissionState(android.Manifest.permission.CAMERA)
        Scaffold(
            scaffoldState = scaffoldState,
            snackbarHost = { scaffoldState.snackbarHostState }

        ) {
            lifecycleScope.launch {
                if (messageText.value.isNotEmpty()) {
                    scaffoldState.snackbarHostState.showSnackbar(
                        messageText.value, actionLabel = getString(
                            R.string.hide
                        )
                    )
                    messageText.value = ""
                }
            }

            NavHost(navController = navController, startDestination = LOGIN_SCREEN) {

                composable(REGISTER_SCREEN) {
                    registerScreen(navController = navController, viewModel = onBoardingViewModel)
                }

                composable(REGISTER_AS_USER_SCREEN) {
                    RegisterAsUserScreen(
                        navController = navController,
                        viewModel = onBoardingViewModel
                    )
                }

                composable(REGISTER_AS_RESTAURANT_SCREEN) {
                    RegisterAsRestaurantScreen(
                        navController = navController,
                        viewModel = onBoardingViewModel
                    )
                }

                composable(LOGIN_SCREEN) {
                    LoginScreen(
                        viewModel = onBoardingViewModel,
                        navController = navController,
                        loading = loading.value
                    )
                }

            }
        }

        MenuelySnackBar(
            snackbarHostState = scaffoldState.snackbarHostState,
            onDismiss = { scaffoldState.snackbarHostState.currentSnackbarData?.dismiss() },
        )

        if (viewModel.loginStatusState.value == LOGGED_AS_USER) {
            askForPermission(cameraPermissionState = cameraPermissionState)
            when {
                cameraPermissionState.hasPermission -> {
                    val intent = Intent(this, HomeHostActivity::class.java)
                    intent.putExtra(LOGGED_STATUS_INTENT_VALUE, LOGGED_AS_USER.name)
                    startActivity(intent)
                }
            }
        }
        if (onBoardingViewModel.loginStatusState.value == LOGGED_AS_USER) {
            askForPermission(cameraPermissionState = cameraPermissionState)
            when {
                cameraPermissionState.hasPermission -> {
                    val intent = Intent(this, HomeHostActivity::class.java)
                    intent.putExtra(LOGGED_STATUS_INTENT_VALUE, LOGGED_AS_USER.name)
                    startActivity(intent)
                }
            }
        }
    }


    override fun fetchData() {
        viewModel.checkLoginStatus()
        viewModel.loginStatus.observe(this, this::handleLoginStatus)
        onBoardingViewModel.loginStatus.observe(this, this::handleLoginStatus)
        onBoardingViewModel.successfulRegistration.observe(this, this::handleSuccessfulRegistration)
    }

    private fun handleSuccessfulRegistration(isSuccessful: Boolean) {
        if (isSuccessful) {

            navController.navigate(LOGIN_SCREEN) {
                popUpTo(LOGIN_SCREEN); launchSingleTop = true;
            }
        }
    }


    private fun handleLoginStatus(loginStatus: LoginStatus) {
        if (loginStatus == LOGGED_AS_RESTAURANT) {
            val intent = Intent(this, HomeHostActivity::class.java)
            intent.putExtra(LOGGED_STATUS_INTENT_VALUE, LOGGED_AS_RESTAURANT.name)
            startActivity(intent)
        }
    }

}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun askForPermission(cameraPermissionState: PermissionState) {
    PermissionRequired(
        permissionState = cameraPermissionState,
        permissionNotGrantedContent = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    stringResource(R.string.cammera_permission),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h6,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(32.dp))
                Row {
                    Button(onClick = { cameraPermissionState.launchPermissionRequest() }) {
                        Text(
                            stringResource(R.string.allow), style = MaterialTheme.typography.body2,
                            fontSize = 16.sp,
                            color = Color.White
                        )
                    }
                }
            }
        },
        permissionNotAvailableContent = {

        }) {

    }
}








