package com.blondhino.menuely.data.common.model

import androidx.compose.runtime.mutableStateOf
import com.blondhino.menuely.data.database.dao.UserDao
import com.blondhino.menuely.data.database.tables.UserTableModel

class UserProfileModel(private val userDao: UserDao) {
    val profileImageUrl = mutableStateOf("")
    val headerImageUrl = mutableStateOf("")
    val firstname = mutableStateOf(userDao.getUser()?.firstname)
    val lastname = mutableStateOf(userDao.getUser()?.lastname)
    val email = mutableStateOf(userDao.getUser()?.email)
    val createdAt = mutableStateOf(userDao.getUser()?.createdAt)
    val updatedAt = mutableStateOf(userDao.getUser()?.updatedAt)


    fun setProfile(userModel: UserModel) {
        userModel.profileImage?.let { profileImageUrl.value = it }
        userModel.coverImage?.let { headerImageUrl.value = it }
        userModel.firstname?.let { firstname.value = it }
        userModel.lastname?.let { lastname.value = it }
        userModel.email?.let { email.value = it }
        userModel.createdAt?.let { createdAt.value = it }
        userModel.updatedAt?.let { updatedAt.value = it }

       val userTableModel =UserTableModel(
           id=userDao.getUser()!!.id,
           firstname = firstname.value,
           lastname = lastname.value,
           email = email.value,
           createdAt = createdAt.value,
           updatedAt = updatedAt.value
       )
        userDao.update(userTableModel)
    }

}