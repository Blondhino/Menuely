package com.blondhino.menuely.data.common.response

import com.blondhino.menuely.data.common.model.AuthModel
import com.blondhino.menuely.data.common.model.ImageModel
import com.blondhino.menuely.data.database.tables.AuthTableModel
import com.blondhino.menuely.data.database.tables.UserTableModel

class MenuCategoryResponse(
    var id: Int?,
    var name : String?,
    val currency: String?,
    val restaurantId : Int?,
    val createdAt: Long?,
    val updatedAt: Long?,
    val image : ImageModel?
)