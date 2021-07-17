package com.blondhino.menuely.data.common.model

class RestaurantModel(
    val id: Int? =null,
    val email: String?=null,
    val name: String?=null,
    val description: String?=null,
    val country: String?=null,
    val city: String?=null,
    val address: String?=null,
    val postalCode: String?=null,
    val profileImage: ImageModel?=null,
    val coverImage: ImageModel?=null,
    val createdAt :Long?=null,
    val updatedAt :Long?=null
)