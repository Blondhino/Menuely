package com.blondhino.menuely.ui.home.host

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.blondhino.menuely.data.common.constants.NavigationRoutes.PROFILE_USER_SCREEN
import com.blondhino.menuely.data.common.constants.NavigationRoutes.SCAN_SCREEN
import com.blondhino.menuely.data.common.constants.NavigationRoutes.SEARCH_RESTAURANTS_SCREEN
import com.blondhino.menuely.data.common.enums.LoginStatus
import com.blondhino.menuely.ui.profile_user.ProfileUserScreen
import com.blondhino.menuely.ui.profile_user.ProfileUserViewModel
import com.blondhino.menuely.ui.scan.ScanScreen
import com.blondhino.menuely.ui.search_restaurant.SearchRestaurantsScreen


@Composable

fun HomeHostScreenNavigationConf(
    navController: NavHostController,
    loginStatus: LoginStatus,
    profileUserViewModel: ProfileUserViewModel
) {

    val startDestination = SCAN_SCREEN

    NavHost(navController = navController, startDestination = startDestination) {

        composable(SCAN_SCREEN) {
            ScanScreen(navController)
        }

        composable(SEARCH_RESTAURANTS_SCREEN) {
            SearchRestaurantsScreen(navController)
        }

        composable(PROFILE_USER_SCREEN) {
            ProfileUserScreen(navController,profileUserViewModel)
        }

    }
}
