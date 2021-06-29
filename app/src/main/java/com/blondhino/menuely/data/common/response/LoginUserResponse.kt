package com.blondhino.menuely.data.common.response

import com.blondhino.menuely.data.common.model.AuthModel
import com.blondhino.menuely.data.database.tables.AuthTableModel
import com.blondhino.menuely.data.database.tables.UserTableModel

class LoginUserResponse(
    val user: UserTableModel?,
    val auth: AuthTableModel?
)