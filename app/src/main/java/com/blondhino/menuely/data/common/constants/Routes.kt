package com.blondhino.menuely.data.common.constants

object Routes {
    const val NO_AUTH_HEADER = "NoAuth: true"

    private const val AUTH = "auth/"
    const val REFRESH_TOKEN ="refresh-token"
    const val LOGIN_USER = AUTH + "login/user/"
    const val LOGIN_RESTAURANT = AUTH + "login/restaurant/"
    const val REGISTER_USER = AUTH + "register/user"
    const val REGISTER_RESTAURANT = AUTH + "register/restaurant"

    private const val USERS = "users/"
    const val MY_USER_PROFILE = USERS + "me"
}