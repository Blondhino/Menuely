package com.blondhino.menuely.ui.components

import android.util.Log
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.blondhino.menuely.data.common.Screen
import com.blondhino.menuely.data.common.constants.NavigationRoutes.MENUS_SCREEN
import com.blondhino.menuely.data.common.constants.NavigationRoutes.SCAN_SCREEN
import com.blondhino.menuely.data.common.enums.LoginStatus
import com.blondhino.menuely.ui.ui.theme.greenLight
import com.blondhino.menuely.ui.ui.theme.greyDark


@Composable
fun MenuelyBottomNavigation(
    loginStatus: LoginStatus = LoginStatus.LOGGED_AS_USER,
    navController: NavHostController
) {
    val loggedAsUserScreens = listOf(
        Screen.Scan,
        Screen.SearchRestaurants,
        Screen.ProfileUser,
    )

    val loggedAsRestaurantScreens = listOf(
        Screen.Menus,
        Screen.Employees,
        Screen.ProfileRestaurant
    )
    BottomNavigation(
        backgroundColor = greyDark, modifier = Modifier.clip(
            RoundedCornerShape(15.dp, 15.dp, 0.dp, 0.dp)
        )
    ) {
        val currentRoute = currentRoute(navController)
        if (loginStatus == LoginStatus.LOGGED_AS_USER) {
            loggedAsUserScreens.forEach { screen ->
                BottomNavigationItem(
                    icon = {
                        Icon(
                            painterResource(id = screen.icon),
                            contentDescription = "lololol",
                            modifier = Modifier.width(20.dp)
                        )
                    },
                    label = { Text(stringResource(id = screen.title), color = greenLight) },
                    selected = currentRoute == screen.route,
                    selectedContentColor = greenLight,
                    unselectedContentColor = Color.Black.copy(0.2F),
                    alwaysShowLabel = false,
                    onClick = {
                        if (currentRoute != screen.route) {
                            navController.navigate(screen.route) {
                                popUpTo(SCAN_SCREEN); launchSingleTop = true
                            }
                        }
                    })

            }
        } else {
            loggedAsRestaurantScreens.forEach { screen ->
                BottomNavigationItem(
                    icon = {
                        Icon(
                            painterResource(id = screen.icon),
                            contentDescription = "lololol",
                            modifier = Modifier.width(20.dp)
                        )
                    },
                    label = { Text(stringResource(id = screen.title), color = greenLight) },
                    selected = currentRoute == screen.route,
                    selectedContentColor = greenLight,
                    unselectedContentColor = Color.Black.copy(0.2F),
                    alwaysShowLabel = false,
                    onClick = {
                        if (currentRoute != screen.route) {
                            navController.navigate(screen.route) {
                                popUpTo(MENUS_SCREEN); launchSingleTop = true
                            }
                        }
                    })

            }
        }

    }


}


@Composable
private fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}