package com.blondhino.menuely.ui.home.host

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.blondhino.menuely.data.common.constants.NavigationRoutes.EMPLOYEES_SCREEN
import com.blondhino.menuely.data.common.constants.NavigationRoutes.MENUS_SCREEN
import com.blondhino.menuely.data.common.constants.NavigationRoutes.PROFILE_RESTAURANT_SCREEN
import com.blondhino.menuely.data.common.constants.NavigationRoutes.PROFILE_USER_SCREEN
import com.blondhino.menuely.data.common.constants.NavigationRoutes.RESTAURANT_SCREEN_SINGLE
import com.blondhino.menuely.data.common.constants.NavigationRoutes.SCAN_SCREEN
import com.blondhino.menuely.data.common.constants.NavigationRoutes.SEARCH_RESTAURANTS_SCREEN
import com.blondhino.menuely.data.common.constants.NavigationRoutes.UPDATE_USER_PROFILE_SCREEN
import com.blondhino.menuely.data.common.enums.LoginStatus
import com.blondhino.menuely.ui.MenusScreen
import com.blondhino.menuely.ui.employees.EmployeesScreen
import com.blondhino.menuely.ui.profile_restaurant.ProfileRestaurantScreen
import com.blondhino.menuely.ui.profile_restaurant.ProfileRestaurantSingleScreen
import com.blondhino.menuely.ui.profile_restaurant.RestaurantViewModel
import com.blondhino.menuely.ui.profile_user.ProfileUserScreen
import com.blondhino.menuely.ui.profile_user.ProfileUserViewModel
import com.blondhino.menuely.ui.profile_user.update.UpdateUserProfileScreen
import com.blondhino.menuely.ui.scan.ScanScreen
import com.blondhino.menuely.ui.search_restaurant.SearchRestaurantsScreen
import com.blondhino.menuely.ui.search_restaurant.SearchViewModel


@Composable

fun HomeHostScreenNavigationConf(
    navController: NavHostController,
    profileUserViewModel: ProfileUserViewModel,
    hostViewModel: HostViewModel,
    searchViewModel: SearchViewModel,
    loginStatus: LoginStatus,
    restaurantViewModel: RestaurantViewModel
) {

    val startDestination =
        if (loginStatus == LoginStatus.LOGGED_AS_USER) SCAN_SCREEN else MENUS_SCREEN

    NavHost(navController = navController, startDestination = startDestination) {

        composable(SCAN_SCREEN) {
            ScanScreen(navController)
        }

        composable(SEARCH_RESTAURANTS_SCREEN) {
            SearchRestaurantsScreen(navController,searchViewModel)
        }

        composable(PROFILE_USER_SCREEN) {
            ProfileUserScreen(navController, profileUserViewModel, hostViewModel)
        }

        composable(MENUS_SCREEN) {
            MenusScreen(navController = navController)
        }

        composable(EMPLOYEES_SCREEN) {
            EmployeesScreen(navController = navController)
        }

        composable(PROFILE_RESTAURANT_SCREEN) {
            ProfileRestaurantScreen(navController = navController,hostViewModel = hostViewModel)
        }

        composable(UPDATE_USER_PROFILE_SCREEN){
            UpdateUserProfileScreen(profileUserViewModel = profileUserViewModel)
        }

        composable(RESTAURANT_SCREEN_SINGLE){
            ProfileRestaurantSingleScreen(
                navController =navController,
                searchViewModel = searchViewModel,
                restaurantViewModel = restaurantViewModel
            )
        }

    }
}
