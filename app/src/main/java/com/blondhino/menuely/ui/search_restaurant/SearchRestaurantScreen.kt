package com.blondhino.menuely.ui.search_restaurant

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.blondhino.menuely.R
import com.blondhino.menuely.data.common.constants.NavigationRoutes.RESTAURANT_SCREEN_SINGLE
import com.blondhino.menuely.ui.components.MenuelyEmptyState
import com.blondhino.menuely.ui.components.MenuelyJumpingProgressBar
import com.blondhino.menuely.ui.components.MenuelySearchBox
import com.blondhino.menuely.ui.components.MenuelySearchRestaurantTicket

@Composable
fun SearchRestaurantsScreen(navController: NavHostController, searchViewModel: SearchViewModel) {
    searchViewModel.initialSearch()
    val restaurants = searchViewModel.restaurants.value

    Column(modifier = Modifier.fillMaxSize()) {
        MenuelySearchBox(
            value = searchViewModel.searchModel.search.value,
            onValueChanged = { value ->
                searchViewModel.searchModel.search.value = value
                searchViewModel.search()
            })

        MenuelyJumpingProgressBar(isLoading = searchViewModel.isLoading.value)

        MenuelyEmptyState(
            visible = searchViewModel.emptyStateVisible.value,
            title = stringResource(id = R.string.oops),
            subtitle = stringResource(id = R.string.no_matches)
        )

        LazyColumn() {
            itemsIndexed(items = restaurants) { index, item ->
                item.name?.let { name ->
                    item.address?.let { address ->
                        item.id?.let { id ->
                            MenuelySearchRestaurantTicket(
                                id = id,
                                titleText = name,
                                descText = address + " " + item.city + " " + item.postalCode + " " + item.country,
                                imageUrl = item.profileImage?.url.toString()
                            ) { clickedId ->
                                searchViewModel.clickedRestaurantId.value = clickedId
                                navController.navigate(RESTAURANT_SCREEN_SINGLE)
                            }
                        }
                    }
                }
            }

        }
    }
}