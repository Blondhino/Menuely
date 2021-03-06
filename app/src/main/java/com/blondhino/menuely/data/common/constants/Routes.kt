package com.blondhino.menuely.data.common.constants

object Routes {
     const val BASE_URL = "https://menuely-eyj6bxkacq-ey.a.run.app/"
   // const val BASE_URL = "https://menuely.herokuapp.com/"

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
    const val SEARCH_USERS="users"

    const val UPDATE_IMAGE_ON_PROFILE = "$MY_USER_PROFILE/image"
    const val UPDATE_USER_PROFILE = "$MY_USER_PROFILE/profile"

    const val RESTAURANTS = "restaurants"
    private const val MY_RESTAURANTS_PROFILE ="$RESTAURANTS/me"
    const val UPDATE_RESTAURANT_PROFILE ="$MY_RESTAURANTS_PROFILE/profile"
    const val UPDATE_IMAGE_ON_RESTAURANT_PROFILE = "$MY_RESTAURANTS_PROFILE/image"

    private const val OFFERS ="offers/"

    const val MENUS = OFFERS+"menus"
    const val CATEGORIES = OFFERS+"categories"
    const val PRODUCTS = OFFERS+"products"

    const val CREATE_MENU = OFFERS+"menus/"
    const val CREATE_CATEGORY = OFFERS+"categories/"
    const val CREATE_PRODUCT = OFFERS+"products/"
    const val GET_CATEGORIES = OFFERS+"categories"
    const val GET_PRODUCTS = OFFERS+"products"

    const val INVITATIONS = "invitations/"
    const val INVITATION= INVITATIONS+"create"


    const val ORDERS = "orders/"
    const val ORDER = ORDERS+"create"
    const val USER_ORDERS = ORDERS+"user"

}