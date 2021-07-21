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
import java.text.NumberFormat

class CreateProductModel() {
    val name = mutableStateOf("")
    val description = mutableStateOf("")
    val price = mutableStateOf("")
    val currency = mutableStateOf("")
    val categoryId = mutableStateOf(0)
    val image = mutableStateOf<MultipartBody.Part?>(null)
    val errorMessage = mutableStateOf(0)


    fun validate(): Boolean {
        if (name.value.isEmpty() || description.value.isEmpty() || price.value.isEmpty() || image.value == null) {
            errorMessage.value = R.string.please_fill_all
            return false
        } else if (price.value.startsWith("-")) {
            errorMessage.value = R.string.negative_value
            return false
        }
        return true
    }

    fun provideProductModel(): ProductModel =
        ProductModel(
            name = this.name.value,
            description = this.description.value,
            price= this.price.value,
            currency = this.currency.value,
            categoryId = this.categoryId.value,
            image = this.image.value
        )

    fun clear() {


    }

}



