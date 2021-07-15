package com.blondhino.menuely.data.common.model

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import com.blondhino.menuely.data.database.dao.UserDao
import com.blondhino.menuely.data.database.tables.UserTableModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.sample

class UserProfileModel(private val userDao: UserDao) {
    val profileImageUrl = mutableStateOf("")
    val headerImageUrl = mutableStateOf("")
    val firstname = mutableStateOf(userDao.getUser()?.firstname)
    val lastname = mutableStateOf(userDao.getUser()?.lastname)
    val lastNameDebounced = MutableStateFlow(lastname.value).sample(1000)
    val email = mutableStateOf(userDao.getUser()?.email)
    val createdAt = mutableStateOf(userDao.getUser()?.createdAt)
    val updatedAt = mutableStateOf(userDao.getUser()?.updatedAt)




    fun setProfile(userModel: UserModel) {

        profileImageUrl.value= userModel.profileImage?.url.toString()
        headerImageUrl.value= userModel.coverImage?.url.toString()
        userModel.profileImage?.let { profileImageUrl.value }
        userModel.coverImage?.let { headerImageUrl.value }
        userModel.firstname?.let { firstname.value = it }
        userModel.lastname?.let { lastname.value = it }
        userModel.email?.let { email.value = it }
        userModel.createdAt?.let { createdAt.value = it }
        userModel.updatedAt?.let { updatedAt.value = it }

        val userTableModel = userDao.getUser()?.id?.let {
            UserTableModel(
                id = it,
                firstname = firstname.value,
                lastname = lastname.value,
                email = email.value,
                createdAt = createdAt.value,
                updatedAt = updatedAt.value
            )
        }
        if (userTableModel != null) {
            userDao.update(userTableModel)
        }
    }

}