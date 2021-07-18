package com.blondhino.menuely.data.repo

import android.content.Context
import android.util.Log
import com.blondhino.menuely.data.common.MenuelyApi
import com.blondhino.menuely.data.common.Response
import com.blondhino.menuely.data.common.ResponseHandler
import com.blondhino.menuely.data.common.model.MenuModel
import com.blondhino.menuely.data.common.model.RestaurantModel
import com.blondhino.menuely.data.common.model.UserModel
import com.blondhino.menuely.data.common.response.EmptyResponse
import com.blondhino.menuely.data.database.tables.RestaurantTableModel
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody


class MenusRepo(
    private val api: MenuelyApi,
    private val responseHandler: ResponseHandler,
) {

    suspend fun getRestaurantMenus(restaurantId: Int): Response<ArrayList<MenuModel>>{
      return try{
          val response = api.getRestaurantMenus(restaurantId)
          responseHandler.handleSuccess(response)
      }  catch (e:Exception){
          responseHandler.handleError(e.message.toString())
      }
    }


    suspend fun createRestaurantMenu (menuModel: MenuModel): Response<EmptyResponse>{
        return  try {
            val response = api.createRestaurantMenu(menuModel)
            responseHandler.handleSuccess(response)
        }catch (e:Exception){
            responseHandler.handleError(e.message.toString())
        }

    }



}