package com.blondhino.menuely.ui.home.host

import android.util.Log
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.blondhino.menuely.data.common.constants.IntentConstants.LOGGED_STATUS_INTENT_VALUE
import com.blondhino.menuely.data.common.constants.NavigationRoutes.CART_SCREEN
import com.blondhino.menuely.data.common.constants.NavigationRoutes.CATEGORY_SCREEN
import com.blondhino.menuely.data.common.constants.NavigationRoutes.JOB_INVITATIONS_SCREEN
import com.blondhino.menuely.data.common.constants.NavigationRoutes.PRODUCTS_SCREEN
import com.blondhino.menuely.data.common.constants.NavigationRoutes.RESTAURANT_SCREEN_SINGLE
import com.blondhino.menuely.data.common.constants.NavigationRoutes.UPDATE_RESTAURANT_PROFILE_SCREEN
import com.blondhino.menuely.data.common.constants.NavigationRoutes.UPDATE_USER_PROFILE_SCREEN
import com.blondhino.menuely.data.common.constants.NavigationRoutes.USER_ORDERS_LIST_SCREEN
import com.blondhino.menuely.data.common.constants.NavigationRoutes.USER_ORDER_DETAILS_SCREEN
import com.blondhino.menuely.data.common.enums.LoginStatus
import com.blondhino.menuely.data.common.enums.LoginStatus.LOGGED_AS_USER
import com.blondhino.menuely.data.common.enums.LoginStatus.valueOf
import com.blondhino.menuely.ui.base.BaseComposeActivity
import com.blondhino.menuely.ui.cart.CartViewModel
import com.blondhino.menuely.ui.components.MenuelyBottomNavigation
import com.blondhino.menuely.ui.components.MenuelyCartAlertDialog
import com.blondhino.menuely.ui.components.MenuelySideMenu
import com.blondhino.menuely.ui.employees.EmployeesViewModel
import com.blondhino.menuely.ui.menus.MenusViewModel
import com.blondhino.menuely.ui.orders.OrdersViewModel
import com.blondhino.menuely.ui.profile_restaurant.RestaurantViewModel
import com.blondhino.menuely.ui.profile_user.ProfileUserViewModel
import com.blondhino.menuely.ui.search_restaurant.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeHostActivity : BaseComposeActivity() {
    private val profileUserViewModel: ProfileUserViewModel by viewModels()
    private val hostViewModel: HostViewModel by viewModels()
    private val searchViewModel: SearchViewModel by viewModels()
    private val restaurantViewModel: RestaurantViewModel by viewModels()
    private val menusViewModel: MenusViewModel by viewModels()
    private val cartViewModel: CartViewModel by viewModels()
    private val employeesViewModel: EmployeesViewModel by viewModels()
    private val ordersViewModel: OrdersViewModel by viewModels()
    private val cartAlertDialogVisible = mutableStateOf(false)
    private lateinit var selectedScreen: String
    private lateinit var scaffoldState: ScaffoldState
    private lateinit var scope: CoroutineScope

    @ExperimentalAnimationApi
    override fun setLayout(): @Composable () -> Unit = {
        val navController = rememberNavController()
        val loginStatus: LoginStatus? = intent.getStringExtra(LOGGED_STATUS_INTENT_VALUE)?.let {
            valueOf(
                it
            )
        }
        scope = rememberCoroutineScope()
        scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
        selectedScreen = currentRoute(navController = navController).toString()
        Scaffold(
            scaffoldState = scaffoldState,
            drawerGesturesEnabled = !scaffoldState.drawerState.isClosed,
            bottomBar = {
                if (
                    selectedScreen != UPDATE_USER_PROFILE_SCREEN &&
                    selectedScreen != RESTAURANT_SCREEN_SINGLE &&
                    selectedScreen != UPDATE_RESTAURANT_PROFILE_SCREEN &&
                    selectedScreen != CATEGORY_SCREEN &&
                    selectedScreen != PRODUCTS_SCREEN &&
                    selectedScreen != JOB_INVITATIONS_SCREEN &&
                    selectedScreen != CART_SCREEN &&
                    selectedScreen != USER_ORDERS_LIST_SCREEN &&
                    selectedScreen != USER_ORDER_DETAILS_SCREEN
                ) {
                    loginStatus?.let {
                        MenuelyBottomNavigation(
                            navController = navController,
                            loginStatus = it
                        )
                    }
                }
            },
            drawerShape = RoundedCornerShape(0.dp, 15.dp, 15.dp, 0.dp),
            drawerContent = {
                loginStatus?.let {
                    MenuelySideMenu(loginStatus = it, onLogoutCalled = {
                        logout()
                    }, navController = navController, hostViewModel = hostViewModel)
                }

            }
        ) {
            loginStatus?.let { it1 ->
                HomeHostScreenNavigationConf(
                    navController = navController,
                    profileUserViewModel = profileUserViewModel,
                    hostViewModel = hostViewModel,
                    searchViewModel = searchViewModel,
                    loginStatus = it1,
                    restaurantViewModel = restaurantViewModel,
                    menusViewModel = menusViewModel,
                    cartViewModel = cartViewModel,
                    employeesViewModel = employeesViewModel,
                    ordersViewModel = ordersViewModel
                )
            }
        }
        if (cartAlertDialogVisible.value) {
            MenuelyCartAlertDialog(
                onQuitClick = {
                    cartAlertDialogVisible.value = false
                    cartViewModel.clearCart()
                    super.onBackPressed()
                }, onDismiss = {
                    cartAlertDialogVisible.value = false
                })
        }
    }

    override fun fetchData() {
        hostViewModel.isDrawerOpen.observe(this, this::handleDrawer)
    }

    private fun handleDrawer(isOpen: Boolean) {
        if (isOpen) {
            scope.launch {
                scaffoldState.drawerState.open()
            }
        } else {
            scope.launch {
                scaffoldState.drawerState.close()
            }
        }
    }

    override fun onBackPressed() {
        Log.d("GoingBackFrom", selectedScreen)
        if (scaffoldState.drawerState.isOpen) {
            scope.launch {
                scaffoldState.drawerState.close()
            }
        } else if (selectedScreen == RESTAURANT_SCREEN_SINGLE &&
            cartViewModel.scannedRestaurantId.value != 0 &&
            !cartViewModel.isCartEmpty()
        ) {
            cartAlertDialogVisible.value = true
        } else {
            super.onBackPressed()
        }

    }
}

