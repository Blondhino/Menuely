package com.blondhino.menuely.ui.onboarding.host

import android.content.Intent
import androidx.activity.viewModels
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
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


    override fun setLayout(): @Composable () -> Unit = {
        val loading = onBoardingViewModel.loading
        val messageText = onBoardingViewModel.messageText
        navController = rememberNavController()
        val scaffoldState = rememberScaffoldState()

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
        when (loginStatus) {
            LOGGED_AS_USER -> {
                val intent = Intent(this, HomeHostActivity::class.java)
                intent.putExtra(LOGGED_STATUS_INTENT_VALUE, LOGGED_AS_USER.name)
                startActivity(intent)
            }

            LOGGED_AS_RESTAURANT -> {
                val intent = Intent(this, HomeHostActivity::class.java)
                intent.putExtra(LOGGED_STATUS_INTENT_VALUE, LOGGED_AS_RESTAURANT.name)
                startActivity(intent)
            }

            LOGGED_OUT -> {

            }
        }
    }

}








