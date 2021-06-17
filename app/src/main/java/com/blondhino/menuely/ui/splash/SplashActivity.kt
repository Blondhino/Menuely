package com.blondhino.menuely.ui.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.blondhino.menuely.R
import com.blondhino.menuely.data.database.dao.UserDao
import com.blondhino.menuely.data.database.tables.UserModel
import com.blondhino.menuely.data.repo.OnBoardingRepo
import com.blondhino.menuely.ui.base.BaseComposeActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : BaseComposeActivity() {

    @Inject
    lateinit var userDao: UserDao;

    @Inject
    lateinit var onBoardingRepo: OnBoardingRepo

    override fun setLayout(): @Composable () -> Unit = {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(Color.White)

        ) {

            Column() {
                Image(
                    painterResource(id = R.drawable.ic_menuely),
                    "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .padding(top = 64.dp)
                        .align(Alignment.CenterHorizontally)


                )
            }

            Spacer(modifier = Modifier.padding(10.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Menuely",
                    Modifier
                        .align(Alignment.CenterHorizontally),
                    style = MaterialTheme.typography.h1

                )

                Spacer(modifier = Modifier.padding(top = 10.dp))
                Text(
                    text = "digitize your menus",
                    Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h5
                )
            }
        }
    }

    override fun fetchData() {
        runBlocking {
            withContext(Dispatchers.IO) {
                onBoardingRepo.loginUser()
            }
        }
    }


    override fun observeData() {

    }

    override fun setupUi() {}

}






