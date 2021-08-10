package com.blondhino.menuely.ui.home.host

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.blondhino.menuely.data.common.constants.NavigationRoutes.CART_SCREEN
import com.blondhino.menuely.data.common.constants.NavigationRoutes.CATEGORY_SCREEN
import com.blondhino.menuely.data.common.constants.NavigationRoutes.EMPLOYEES_SCREEN
import com.blondhino.menuely.data.common.constants.NavigationRoutes.JOB_INVITATIONS_SCREEN
import com.blondhino.menuely.data.common.constants.NavigationRoutes.MENUS_SCREEN
import com.blondhino.menuely.data.common.constants.NavigationRoutes.PRODUCTS_SCREEN
import com.blondhino.menuely.data.common.constants.NavigationRoutes.PROFILE_RESTAURANT_SCREEN
import com.blondhino.menuely.data.common.constants.NavigationRoutes.PROFILE_USER_SCREEN
import com.blondhino.menuely.data.common.constants.NavigationRoutes.RESTAURANT_SCREEN_SINGLE
import com.blondhino.menuely.data.common.constants.NavigationRoutes.SCAN_SCREEN
import com.blondhino.menuely.data.common.constants.NavigationRoutes.SEARCH_RESTAURANTS_SCREEN
import com.blondhino.menuely.data.common.constants.NavigationRoutes.UPDATE_RESTAURANT_PROFILE_SCREEN
import com.blondhino.menuely.data.common.constants.NavigationRoutes.UPDATE_USER_PROFILE_SCREEN
import com.blondhino.menuely.data.common.enums.LoginStatus
import com.blondhino.menuely.ui.cart.CartScreen
import com.blondhino.menuely.ui.cart.CartViewModel
import com.blondhino.menuely.ui.employees.EmployeesScreen
import com.blondhino.menuely.ui.employees.EmployeesViewModel
import com.blondhino.menuely.ui.menus.MenusViewModel
import com.blondhino.menuely.ui.menus.category.CategoryScreen
import com.blondhino.menuely.ui.menus.menu.MenusScreen
import com.blondhino.menuely.ui.menus.product.ProductsScreen
import com.blondhino.menuely.ui.profile_restaurant.ProfileRestaurantScreen
import com.blondhino.menuely.ui.profile_restaurant.ProfileRestaurantSingleScreen
import com.blondhino.menuely.ui.profile_restaurant.RestaurantViewModel
import com.blondhino.menuely.ui.profile_restaurant.update.UpdateRestaurantProfileScreen
import com.blondhino.menuely.ui.profile_user.ProfileUserScreen
import com.blondhino.menuely.ui.profile_user.ProfileUserViewModel
import com.blondhino.menuely.ui.profile_user.jobs.JobInvitationsScreen
import com.blondhino.menuely.ui.profile_user.update.UpdateUserProfileScreen
import com.blondhino.menuely.ui.scan.ScanScreen
import com.blondhino.menuely.ui.search_restaurant.SearchRestaurantsScreen
import com.blondhino.menuely.ui.search_restaurant.SearchViewModel
import com.blondhino.menuely.util.EnterAnimation


@ExperimentalAnimationApi
@Composable

fun HomeHostScreenNavigationConf(
    navController: NavHostController,
    profileUserViewModel: ProfileUserViewModel,
    hostViewModel: HostViewModel,
    searchViewModel: SearchViewModel,
    loginStatus: LoginStatus,
    restaurantViewModel: RestaurantViewModel,
    menusViewModel: MenusViewModel,
    cartViewModel: CartViewModel,
    employeesViewModel: EmployeesViewModel
) {

    val startDestination =
        if (loginStatus == LoginStatus.LOGGED_AS_USER) SCAN_SCREEN else MENUS_SCREEN

    NavHost(navController = navController, startDestination = startDestination) {

        composable(SCAN_SCREEN) {
            ScanScreen(navController, menusViewModel = menusViewModel,cartViewModel = cartViewModel,searchViewModel=searchViewModel)
        }

        composable(SEARCH_RESTAURANTS_SCREEN) {
            SearchRestaurantsScreen(navController, searchViewModel)
        }

        composable(PROFILE_USER_SCREEN) {
            ProfileUserScreen(navController, profileUserViewModel, hostViewModel,employeesViewModel)
        }

        composable(MENUS_SCREEN) {
            MenusScreen(
                navController = navController,
                menusViewModel = menusViewModel,
                loginStatus = loginStatus
            )
        }

        composable(EMPLOYEES_SCREEN) {
            EmployeesScreen(navController = navController,employeesViewModel = employeesViewModel)
        }

        composable(PROFILE_RESTAURANT_SCREEN) {
            ProfileRestaurantScreen(
                navController = navController,
                hostViewModel = hostViewModel,
                restaurantViewModel = restaurantViewModel
            )
        }

        composable(UPDATE_USER_PROFILE_SCREEN) {
            UpdateUserProfileScreen(profileUserViewModel = profileUserViewModel)
        }

        composable(RESTAURANT_SCREEN_SINGLE) {
            EnterAnimation {
                ProfileRestaurantSingleScreen(
                    navController = navController,
                    searchViewModel = searchViewModel,
                    restaurantViewModel = restaurantViewModel,
                    cartViewModel = cartViewModel
                )
            }
        }

        composable(UPDATE_RESTAURANT_PROFILE_SCREEN) {
            UpdateRestaurantProfileScreen(restaurantViewModel = restaurantViewModel)
        }

        composable(CATEGORY_SCREEN) {
            EnterAnimation {
                CategoryScreen(
                    navController = navController,
                    menusViewModel = menusViewModel,
                    loginStatus = loginStatus,
                    cartViewModel = cartViewModel
                )
            }
        }

        composable(PRODUCTS_SCREEN) {
            EnterAnimation {
                ProductsScreen(
                    navController = navController,
                    menusViewModel = menusViewModel,
                    loginStatus = loginStatus,
                    cartViewModel = cartViewModel
                )
            }
        }

        composable(CART_SCREEN){
            CartScreen(navController=navController, cartViewModel = cartViewModel)
        }

        composable(JOB_INVITATIONS_SCREEN){
            JobInvitationsScreen(navController=navController,employeesViewModel = employeesViewModel)
        }

    }
}
