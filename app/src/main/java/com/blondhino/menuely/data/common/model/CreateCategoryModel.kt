package com.blondhino.menuely.data.common.model

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.res.stringResource
import com.blondhino.menuely.R
import com.blondhino.menuely.data.database.dao.UserDao
import com.blondhino.menuely.data.database.tables.UserTableModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.sample
import okhttp3.MultipartBody

class CreateCategoryModel() {
    val name = mutableStateOf("")
    val menuId = mutableStateOf(0)
    val image = mutableStateOf<MultipartBody.Part?>(null)
    val errorMessage = mutableStateOf(0)


    fun validate(): Boolean {
        if (name.value.isEmpty() || image.value == null) {
            errorMessage.value = R.string.please_fill_all
            return false
        }
        return true
    }

    fun clear() {
        this.name.value=""
        this.menuId.value=0
        this.image.value=null
    }

    fun provideCategoryModel(): CategoryModel {
        return CategoryModel(
            name = this.name.value,
            menuId = this.menuId.value,
            image = this.image.value!!
        )
    }

}



