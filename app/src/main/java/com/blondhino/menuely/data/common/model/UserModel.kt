package com.blondhino.menuely.data.common.model

class UserModel(
    val id: Int? =null,
    val email: String?=null,
    val firstname: String?=null,
    val lastname: String?=null,
    val profileImage: ImageModel?=null,
    val coverImage: ImageModel?=null,
    val employer : String? =null,
    val createdAt :Long?=null,
    val updatedAt :Long?=null
)