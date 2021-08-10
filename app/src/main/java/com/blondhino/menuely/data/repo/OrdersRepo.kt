package com.blondhino.menuely.data.repo

import android.util.Log
import com.blondhino.menuely.data.common.MenuelyApi
import com.blondhino.menuely.data.common.Response
import com.blondhino.menuely.data.common.ResponseHandler
import com.blondhino.menuely.data.common.model.CartProductModel
import com.blondhino.menuely.data.common.model.RestaurantModel
import com.blondhino.menuely.data.common.request.CreateOrderRequest
import com.blondhino.menuely.data.common.response.EmptyResponse
import com.blondhino.menuely.data.database.tables.RestaurantTableModel
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody


class OrdersRepo(
    private val api: MenuelyApi,
    private val responseHandler: ResponseHandler,
) {
    suspend fun submitOrder(
        restaurantId: Int,
        tableId: Int,
        totalPrice: Double,
        orderedProducts: ArrayList<CartProductModel>
    ): Response<EmptyResponse> {
        return try {

            val response = api.submitOrder(
                CreateOrderRequest(
                    restaurantId = restaurantId,
                    tableId = tableId,
                    totalPrice = totalPrice,
                    orderedProducts = orderedProducts
                )
            )
            responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            responseHandler.handleError(e.message.toString())
        }
    }

}