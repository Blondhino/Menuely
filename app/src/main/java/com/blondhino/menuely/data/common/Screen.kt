package com.blondhino.menuely.data.common

import android.graphics.drawable.VectorDrawable
import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.East
import androidx.compose.material.icons.filled.Icecream
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import com.blondhino.menuely.R
import com.blondhino.menuely.data.common.constants.NavigationRoutes.PROFILE_USER_SCREEN
import com.blondhino.menuely.data.common.constants.NavigationRoutes.SCAN_SCREEN
import com.blondhino.menuely.data.common.constants.NavigationRoutes.SEARCH_RESTAURANTS_SCREEN

sealed class Screen(val route: String, val title: Int, val icon: Int) {
    object Scan : Screen(SCAN_SCREEN, R.string.scan, R.drawable.ic_scan)
    object SearchRestaurants :
        Screen(SEARCH_RESTAURANTS_SCREEN, R.string.search, R.drawable.ic_search)

    object ProfileUser : Screen(PROFILE_USER_SCREEN, R.string.profile, R.drawable.ic_profile)
}
