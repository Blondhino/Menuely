package com.blondhino.menuely.data.common.response

import com.blondhino.menuely.data.common.model.AuthModel
import com.blondhino.menuely.data.database.tables.AuthTableModel
import com.blondhino.menuely.data.database.tables.RestaurantTableModel

class LoginRestaurantResponse(
    val restaurant: RestaurantTableModel?,
    val auth: AuthTableModel?
)