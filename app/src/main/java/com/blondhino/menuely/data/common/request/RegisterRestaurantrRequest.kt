package com.blondhino.menuely.data.common.request

class RegisterRestaurantrRequest(
    val email: String,
    val password: String,
    val name: String,
    val description: String,
    val country: String,
    val city: String,
    val address: String,
    val postalCode: String,
)
