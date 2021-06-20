package com.blondhino.menuely.ui.splash

import android.content.Intent
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import com.blondhino.menuely.data.common.LoginStatus
import com.blondhino.menuely.data.database.dao.UserDao
import com.blondhino.menuely.data.repo.OnBoardingRepo
import com.blondhino.menuely.ui.base.BaseComposeActivity
import com.blondhino.menuely.ui.onboarding.OnBoardingActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : BaseComposeActivity() {

    @Inject
    lateinit var userDao: UserDao;

    @Inject
    lateinit var onBoardingRepo: OnBoardingRepo

    private val viewModel: SplashViewModel by viewModels()

    override fun setLayout(): @Composable () -> Unit = {}

    override fun fetchData() {
       viewModel.checkLoginStatus()
    }


    override fun observeData() {
        viewModel.loginStatus.observe(this,this::handleLoginStatus)
    }

    private fun handleLoginStatus(loginStatus: LoginStatus) {
        when (loginStatus) {
            LoginStatus.LOGGED_AS_USER -> {}
            LoginStatus.LOGGED_AS_RESTAURANT -> {}
            LoginStatus.LOGGED_OUT -> {proceedToOnBoarding()}
        }
    }

    private fun proceedToOnBoarding() {
        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            val intent = Intent(this, OnBoardingActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }, 400)

    }


    override fun setupUi() {}

}






