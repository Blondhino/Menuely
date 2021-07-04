package com.blondhino.menuely.ui.profile_restaurant

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.blondhino.menuely.R
import com.blondhino.menuely.ui.components.MenuelyHeader
import com.blondhino.menuely.ui.components.MenuelyInfoCard
import com.blondhino.menuely.ui.home.host.HostViewModel
import com.blondhino.menuely.util.generateAccountInfo


@Composable
fun ProfileRestaurantScreen(navController: NavHostController, hostViewModel: HostViewModel) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        val (menuOptions, content, menu) = createRefs()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .constrainAs(content) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }, horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MenuelyHeader(
                headerUrl = "",
                mainImageUrl = "",
                height = 220.dp
            )



        }

        Image(
            painter = painterResource(id = R.drawable.ic_hamburger_menu),
            "",
            modifier = Modifier
                .constrainAs(menuOptions) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                }
                .padding(16.dp)
                .size(20.dp)
                .clickable { hostViewModel.isDrawerOpen.value = true }
        )

    }
}
