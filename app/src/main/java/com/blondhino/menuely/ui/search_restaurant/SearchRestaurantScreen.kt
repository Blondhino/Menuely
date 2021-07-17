package com.blondhino.menuely.ui.search_restaurant

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.blondhino.menuely.ui.components.MenuelySearchBox

@Composable
fun SearchRestaurantsScreen(navController: NavHostController, searchViewModel: SearchViewModel) {
    MenuelySearchBox(
        value = searchViewModel.searchModel.searchTerm.value,
        onValueChanged = { value ->
            searchViewModel.searchModel.searchTerm.value = value
        })
}