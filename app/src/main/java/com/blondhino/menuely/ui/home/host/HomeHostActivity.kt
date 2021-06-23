package com.blondhino.menuely.ui.home.host

import android.content.Intent
import androidx.activity.viewModels
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import com.blondhino.menuely.ui.base.BaseComposeActivity
import com.blondhino.menuely.ui.onboarding.OnBoardingViewModel
import com.blondhino.menuely.ui.onboarding.host.OnboardingHostActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeHostActivity : BaseComposeActivity() {
    private val onBoardingViewModel: OnBoardingViewModel by viewModels()
    override fun setLayout(): @Composable () -> Unit = {
        Button(onClick = { logout() }) {

        }
    }

    private fun logout() {
        onBoardingViewModel.logout()
        val intent = Intent(this, OnboardingHostActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun fetchData() {

    }
}