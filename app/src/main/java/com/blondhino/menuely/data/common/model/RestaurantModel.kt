package com.blondhino.menuely.data.common.model

class RestaurantModel(
    var id: Int? =null,
    var email: String?=null,
    var name: String?=null,
    var description: String?=null,
    var country: String?=null,
    var city: String?=null,
    var address: String?=null,
    var postalCode: String?=null,
    var profileImage: ImageModel?=null,
    var coverImage: ImageModel?=null,
    var createdAt :Long?=null,
    var updatedAt :Long?=null
)