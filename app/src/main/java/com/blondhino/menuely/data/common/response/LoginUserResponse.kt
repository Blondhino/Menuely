package com.blondhino.menuely.data.common.response

import com.blondhino.menuely.data.common.AuthModel
import com.blondhino.menuely.data.common.UserModel
import com.blondhino.menuely.data.database.tables.UserTableModel

class LoginUserResponse(
    val user: UserTableModel?,
    val auth: AuthModel?
)