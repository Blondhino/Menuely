package com.blondhino.menuely.ui.profile_restaurant

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavHostController
import com.blondhino.menuely.R
import com.blondhino.menuely.ui.components.MenuelyHeader
import com.blondhino.menuely.ui.home.host.HostViewModel
import com.blondhino.menuely.ui.ui.theme.blackLight
import com.blondhino.menuely.ui.ui.theme.greenLight
import com.blondhino.menuely.ui.ui.theme.greyLight


@Composable
fun ProfileRestaurantScreen(
    navController: NavHostController,
    hostViewModel: HostViewModel,
    restaurantViewModel: RestaurantViewModel
) {
    val scrollState = rememberScrollState()
    restaurantViewModel.fetchMyRestaurantProfile()
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        val (menuOptions, content, menu, aboutUs) = createRefs()

        Column(
            modifier = Modifier
                .background(Color.White)
                .constrainAs(content) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {
            MenuelyHeader(
                headerUrl = restaurantViewModel.myRestaurantProfile.coverImage.value,
                mainImageUrl = restaurantViewModel.myRestaurantProfile.profileImage.value,
                height = 220.dp,
                onMainImageSelected = { uri, bitmap, multipart -> },
                onCoverImageSelected = { uri, bitmap, multipart -> }
            )

            Text(
                text = restaurantViewModel.myRestaurantProfile.name.value,
                style = MaterialTheme.typography.body2,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 8.dp, end = 8.dp, start = 8.dp),
                textAlign = TextAlign.Center
            )

            Text(
                text = restaurantViewModel.myRestaurantProfile.address.value + " "
                        + restaurantViewModel.myRestaurantProfile.city.value + " "
                        + restaurantViewModel.myRestaurantProfile.postalCode.value + " "
                        + restaurantViewModel.myRestaurantProfile.country.value,
                style = MaterialTheme.typography.h6,
                modifier = Modifier
                    .padding(top = 8.dp, end = 16.dp, start = 16.dp)
                    .align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center,
                color = greenLight
            )

            Text(
                stringResource(R.string.about_us),
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(start = 16.dp, top = 32.dp, bottom = 16.dp),
                fontSize = 16.sp
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

        Column(modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(scrollState)
            .constrainAs(aboutUs) {
                top.linkTo(content.bottom)
                bottom.linkTo(parent.bottom, 80.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
            }
        ) {
            Text(
                text = restaurantViewModel.myRestaurantProfile.description.value,
                style = MaterialTheme.typography.h6,
                fontSize = 12.sp,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .align(Alignment.Start),
                color = blackLight,
                )
        }

    }
}

