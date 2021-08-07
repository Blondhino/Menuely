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

class MenuModel(
    val id: Int? =null,
    var name : String? =null,
    var description:String?=null,
    var currency : String?= null,
    val restaurantId : Int? = null,
    val numberOfTables:Int? = null,
    val createdAt : Long?= null,
    val updatedAt : Long?= null,
    val isActive : Boolean? = null
) {}



