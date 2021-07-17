package com.blondhino.menuely.ui.search_restaurant

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.blondhino.menuely.ui.components.MenuelySearchBox
import com.blondhino.menuely.ui.components.MenuelySearchResultTicket

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

        LazyColumn() {
            itemsIndexed(items = restaurants) { index, item ->
                item.name?.let { name ->
                    item.address?.let {
                        address ->
                        MenuelySearchResultTicket(
                            titleText = name,
                            descText = address+" "+item.city+" "+item.postalCode+" "+item.country,
                            imageUrl = item.profileImage?.url.toString()
                        )
                    }
                }
            }

        }
    }
}