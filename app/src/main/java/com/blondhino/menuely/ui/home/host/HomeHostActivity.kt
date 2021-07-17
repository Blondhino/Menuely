package com.blondhino.menuely.ui.home.host

import androidx.activity.viewModels
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.blondhino.menuely.data.common.constants.IntentConstants.LOGGED_STATUS_INTENT_VALUE
import com.blondhino.menuely.data.common.constants.NavigationRoutes.UPDATE_USER_PROFILE_SCREEN
import com.blondhino.menuely.data.common.enums.LoginStatus
import com.blondhino.menuely.data.common.enums.LoginStatus.LOGGED_AS_USER
import com.blondhino.menuely.data.common.enums.LoginStatus.valueOf
import com.blondhino.menuely.ui.base.BaseComposeActivity
import com.blondhino.menuely.ui.components.MenuelyBottomNavigation
import com.blondhino.menuely.ui.components.MenuelySideMenu
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
    private lateinit var scaffoldState: ScaffoldState
    private lateinit var scope: CoroutineScope
    override fun setLayout(): @Composable () -> Unit = {
        val navController = rememberNavController()
        val loginStatus: LoginStatus? = intent.getStringExtra(LOGGED_STATUS_INTENT_VALUE)?.let {
            valueOf(
                it
            )
        }
        scope = rememberCoroutineScope()
        scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
        Scaffold(
            scaffoldState = scaffoldState,
            drawerGesturesEnabled = !scaffoldState.drawerState.isClosed,
            bottomBar = {
                if (currentRoute(navController = navController) != UPDATE_USER_PROFILE_SCREEN) {
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
                MenuelySideMenu(loginStatus = LOGGED_AS_USER, onLogoutCalled = {
                    logout()
                }, navController = navController, hostViewModel = hostViewModel)

            }
        ) {
            loginStatus?.let { it1 ->
                HomeHostScreenNavigationConf(
                    navController = navController,
                    profileUserViewModel = profileUserViewModel,
                    hostViewModel = hostViewModel,
                    searchViewModel = searchViewModel,
                    loginStatus = it1
                )
            }
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
        if (scaffoldState.drawerState.isOpen) {
            scope.launch {
                scaffoldState.drawerState.close()
            }
        } else {
            super.onBackPressed()
        }
    }
}

