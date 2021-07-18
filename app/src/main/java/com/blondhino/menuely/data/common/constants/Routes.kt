package com.blondhino.menuely.data.common.constants

object Routes {
    // const val BASE_URL = "https://menuely-eyj6bxkacq-ey.a.run.app/"
    const val BASE_URL = "https://menuely.herokuapp.com/"

    const val NO_AUTH_HEADER = "NoAuth: true"
    const val NO_AUTH: String = "NoAuth"

    private const val AUTH = "auth/"
    const val REFRESH_TOKEN = AUTH + "refresh-token"
    const val LOGIN_USER = AUTH + "login/user/"
    const val LOGIN_RESTAURANT = AUTH + "login/restaurant"
    const val REGISTER_USER = AUTH + "register/user"
    const val REGISTER_RESTAURANT = AUTH + "register/restaurant"

    private const val USERS = "users/"
    const val MY_USER_PROFILE = USERS + "me"

    const val UPDATE_IMAGE_ON_PROFILE = "$MY_USER_PROFILE/image"
    const val UPDATE_USER_PROFILE = "$MY_USER_PROFILE/profile"

    const val RESTAURANTS = "restaurants"

    private const val OFFERS ="offers/"

    const val MENUS = OFFERS+"menus"

}