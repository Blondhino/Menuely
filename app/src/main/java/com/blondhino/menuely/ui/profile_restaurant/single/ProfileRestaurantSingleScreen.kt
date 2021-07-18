package com.blondhino.menuely.ui.profile_restaurant

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavHostController
import com.blondhino.menuely.R
import com.blondhino.menuely.ui.components.MenuelyButton
import com.blondhino.menuely.ui.components.MenuelyHeader
import com.blondhino.menuely.ui.search_restaurant.SearchViewModel
import com.blondhino.menuely.ui.ui.theme.*

@Composable
fun ProfileRestaurantSingleScreen(
    navController: NavHostController,
    searchViewModel: SearchViewModel,
    restaurantViewModel: RestaurantViewModel
) {
    restaurantViewModel.getSingleRestaurant(searchViewModel.clickedRestaurantId.value)
    val scrollState = rememberScrollState()
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (content, showMenuButton, aboutUs) = createRefs()

        Column(
            modifier = Modifier
                .background(Color.White)
                .constrainAs(content) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)


                },
        ) {
            MenuelyHeader(
                mainImageUrl = restaurantViewModel.restaurant.profileImage.value,
                headerUrl = restaurantViewModel.restaurant.coverImage.value,
                height = 220.dp,
                onMainImageSelected = { uri, bitmap, multipart -> },
                onCoverImageSelected = {uri, bitmap, multipart ->}
            )

            Text(
                text = restaurantViewModel.restaurant.name.value,
                style = MaterialTheme.typography.body2,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 8.dp, end = 8.dp, start = 8.dp),
                textAlign = TextAlign.Center
            )

            Text(
                text = restaurantViewModel.restaurant.address.value + " "
                        + restaurantViewModel.restaurant.city.value + " "
                        + restaurantViewModel.restaurant.postalCode.value + " "
                        + restaurantViewModel.restaurant.country.value,
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



        MenuelyButton(modifier = Modifier
            .constrainAs(showMenuButton) {
                bottom.linkTo(parent.bottom, 16.dp)
            }
            .fillMaxWidth()
            .height(55.dp)
            .padding(horizontal = 8.dp),
            text = stringResource(R.string.check_menu)) {

        }

        Column(modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(scrollState)
            .constrainAs(aboutUs) {
                top.linkTo(content.bottom)
                bottom.linkTo(showMenuButton.top, 32.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
            }
        ) {
            Text(
                text = restaurantViewModel.restaurant.description.value,
                style = MaterialTheme.typography.h6,
                fontSize = 12.sp,
                modifier = Modifier.padding(horizontal = 16.dp),
                color = blackLight,

                )
        }
    }

}
