package com.blondhino.menuely.ui.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.blondhino.menuely.R
import com.blondhino.menuely.ui.base.BaseComposeActivity


class SplashActivity : BaseComposeActivity() {
    override fun setLayout(): @Composable () -> Unit = {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
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

    override fun fetchData() {}
    override fun observeData() {}
    override fun setupUi() {}
}






