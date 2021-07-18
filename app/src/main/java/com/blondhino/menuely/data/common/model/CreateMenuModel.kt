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

class CreateMenuModel() {
    val name = mutableStateOf("")
    val currency = mutableStateOf("")
    val description = mutableStateOf("")
    val numberOfTables = mutableStateOf("0")
    val errorMessage = mutableStateOf(0)


    fun validate(): Boolean {
        if (name.value.isEmpty()
            || currency.value.isEmpty()
            || description.value.isEmpty()
            || numberOfTables.value.isEmpty()
        ) {
            errorMessage.value = R.string.please_fill_all
            return false
        } else if (numberOfTables.value.length>1 && numberOfTables.value[0].toString()=="0") {
            errorMessage.value = R.string.pleaseSelectValidNum
            return false
        } else if (numberOfTables.value.toInt() <= 0) {
            errorMessage.value = R.string.pleaseSelectValidNum
            return false
        }

        errorMessage.value=0
        return true
    }

    fun clear(){
        name.value=""
        currency.value=""
        description.value=""
        numberOfTables.value=""
        errorMessage.value=0
    }

    fun provideMenuModel():MenuModel{
        return MenuModel(
            name=this.name.value,
            description =this.description.value,
            currency = this.currency.value,
            numberOfTables = this.numberOfTables.value.toInt()
        )
    }


}



