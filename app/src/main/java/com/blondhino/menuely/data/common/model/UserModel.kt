package com.blondhino.menuely.data.common.model

class UserModel(
    val id: Int?,
    val email: String?,
    val firstname: String?,
    val lastname: String?,
    val profileImage: ImageModel?,
    val coverImage: String?,
    val createdAt :Long?,
    val updatedAt :Long?
)