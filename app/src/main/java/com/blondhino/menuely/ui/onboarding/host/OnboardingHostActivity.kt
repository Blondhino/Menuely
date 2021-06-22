package com.blondhino.menuely.ui.onboarding.host

import android.content.Intent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.blondhino.menuely.data.common.LoginStatus
import com.blondhino.menuely.data.common.LoginStatus.*
import com.blondhino.menuely.data.common.NavigationRoutes.HOME_ROUTE
import com.blondhino.menuely.data.common.NavigationRoutes.LOGIN_SCREEN
import com.blondhino.menuely.data.common.NavigationRoutes.REGISTER_SCREEN
import com.blondhino.menuely.data.repo.OnBoardingRepo
import com.blondhino.menuely.ui.base.BaseComposeActivity
import com.blondhino.menuely.ui.home.host.HomeHostActivity
import com.blondhino.menuely.ui.home.HomeScreen
import com.blondhino.menuely.ui.onboarding.OnBoardingViewModel
import com.blondhino.menuely.ui.onboarding.SplashViewModel
import com.blondhino.menuely.ui.onboarding.loginScreen
import com.blondhino.menuely.ui.onboarding.registerScreen
import dagger.hilt.android.AndroidEntryPoint
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
        val errorText = onBoardingViewModel.errorText
        navController = rememberNavController()
        NavHost(navController = navController, startDestination = LOGIN_SCREEN) {
            composable(LOGIN_SCREEN) {
                loginScreen(
                    navController = navController,
                    viewModel = onBoardingViewModel,
                    loading = loading.value,
                    errorText = errorText.value
                )
            }

            composable(REGISTER_SCREEN) {
                registerScreen(navController = navController, viewModel = onBoardingViewModel)
            }

        }
    }


    override fun fetchData() {
        viewModel.checkLoginStatus()
        viewModel.loginStatus.observe(this, this::handleLoginStatus)
        onBoardingViewModel.loginStatus.observe(this, this::handleLoginStatus)
    }


    private fun handleLoginStatus(loginStatus: LoginStatus) {
        when (loginStatus) {
            LOGGED_AS_USER -> {
                val intent = Intent(this, HomeHostActivity::class.java)
                startActivity(intent)
            }

            LOGGED_AS_RESTAURANT -> {

            }

            LOGGED_OUT -> {

            }
        }
    }

}








