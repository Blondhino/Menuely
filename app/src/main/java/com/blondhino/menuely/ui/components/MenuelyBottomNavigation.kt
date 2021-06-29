package com.blondhino.menuely.ui.components

import android.graphics.drawable.VectorDrawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.getValue
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Terrain
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.blondhino.menuely.R
import com.blondhino.menuely.data.common.Screen
import com.blondhino.menuely.data.common.constants.NavigationRoutes.SCAN_SCREEN
import com.blondhino.menuely.data.common.enums.LoginStatus
import com.blondhino.menuely.ui.ui.theme.greenLight
import com.blondhino.menuely.ui.ui.theme.greyDark
import com.blondhino.menuely.ui.ui.theme.greyLight
import com.blondhino.menuely.ui.ui.theme.greyMedium


@Composable
fun MenuelyBottomNavigation(
    loginStatus: LoginStatus = LoginStatus.LOGGED_AS_USER,
    navController: NavHostController
) {
    val loggedAsUserScreens = listOf(
        Screen.Scan,
        Screen.SearchRestaurants,
        Screen.ProfileUser
    )
    BottomNavigation(backgroundColor= greyDark, modifier = Modifier.clip(
        RoundedCornerShape(15.dp, 15.dp,0.dp,0.dp))) {
        val currentRoute = currentRoute(navController)
        loggedAsUserScreens.forEach { screen ->
            BottomNavigationItem(
                icon = { Icon(painterResource(id = screen.icon), contentDescription = "lololol", modifier = Modifier.width(20.dp))},
                label = { Text(stringResource(id = screen.title)) },
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

    }


}


@Composable
private fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}