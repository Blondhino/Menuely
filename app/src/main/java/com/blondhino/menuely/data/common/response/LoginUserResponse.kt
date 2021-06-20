package com.blondhino.menuely.data.common.response

import com.blondhino.menuely.data.common.AuthModel
import com.blondhino.menuely.data.common.UserModel

class LoginUserResponse(
    val user: UserModel?,
    val auth: AuthModel?
)