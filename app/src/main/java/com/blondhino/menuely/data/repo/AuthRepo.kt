package com.blondhino.menuely.data.repo

import com.blondhino.menuely.data.database.dao.AuthDao
import com.blondhino.menuely.data.database.tables.AuthTableModel

class AuthRepo(
    private val authDao: AuthDao
) {

    fun accessToken(): String? {
        return authDao.getAuth()?.accessToken
    }

    fun refreshToken(): String? {
        return authDao.getAuth()?.refreshToken
    }

    fun insertNewAuth(authTableModel: AuthTableModel) {
        authDao.insert(authTableModel)
    }
}