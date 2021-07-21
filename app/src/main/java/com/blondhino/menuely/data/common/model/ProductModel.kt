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

class ProductModel(
    val name : String?=null,
    val description : String?=null,
    val price : String?=null,
    val currency : String?=null,
    val categoryId: Int?=null,
    val image:MultipartBody.Part? =null

)



