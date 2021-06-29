package com.blondhino.menuely.ui.home.host

import android.content.Intent
import androidx.activity.viewModels
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.blondhino.menuely.data.common.enums.LoginStatus
import com.blondhino.menuely.data.common.enums.LoginStatus.LOGGED_AS_USER
import com.blondhino.menuely.ui.base.BaseComposeActivity
import com.blondhino.menuely.ui.components.MenuelyBottomNavigation
import com.blondhino.menuely.ui.onboarding.OnBoardingViewModel
import com.blondhino.menuely.ui.onboarding.host.OnboardingHostActivity
import com.blondhino.menuely.ui.profile_user.ProfileUserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeHostActivity : BaseComposeActivity() {
    private val profileUserViewModel: ProfileUserViewModel by viewModels()
    override fun setLayout(): @Composable () -> Unit = {
        val navController = rememberNavController()
        Scaffold(
            bottomBar = {
                MenuelyBottomNavigation(
                    navController = navController,
                    loginStatus = LOGGED_AS_USER
                )
            }
        ) {
            HomeHostScreenNavigationConf(
                navController = navController,
                loginStatus = LOGGED_AS_USER,
                profileUserViewModel = profileUserViewModel
            )
        }
    }

    override fun fetchData() {

    }
}