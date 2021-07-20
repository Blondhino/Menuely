package com.blondhino.menuely.ui.base

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.blondhino.menuely.ui.onboarding.OnBoardingViewModel
import com.blondhino.menuely.ui.onboarding.host.OnboardingHostActivity
import com.blondhino.menuely.ui.ui.theme.MenuelyTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking

abstract class BaseComposeActivity : ComponentActivity() {
    private val onBoardingViewModel: OnBoardingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        //  WindowCompat.setDecorFitsSystemWindows(window, false)
        runBlocking {
            setContent {
                MenuelyTheme(darkTheme = false, content = setLayout())
            }
            fetchData()
        }

    }

     fun logout() {
        onBoardingViewModel.logout()
        val intent = Intent(this, OnboardingHostActivity::class.java)
        startActivity(intent)
        finish()
    }

    protected abstract fun setLayout(): @Composable() () -> Unit

    protected abstract fun fetchData()

    @Composable
     fun currentRoute(navController: NavHostController): String? {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        return navBackStackEntry?.destination?.route
    }

}