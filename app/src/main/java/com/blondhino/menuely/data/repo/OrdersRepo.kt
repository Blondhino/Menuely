package com.blondhino.menuely.data.repo

import com.blondhino.menuely.data.common.MenuelyApi
import com.blondhino.menuely.data.common.Response
import com.blondhino.menuely.data.common.ResponseHandler
import com.blondhino.menuely.data.common.model.CartProductModel
import com.blondhino.menuely.data.common.model.OrderModel
import com.blondhino.menuely.data.common.request.CreateOrderRequest
import com.blondhino.menuely.data.common.response.EmptyResponse


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

    suspend fun getUserOrders(): Response<ArrayList<OrderModel>> {
        return try {
            val response = api.getUserOrders()
            responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            responseHandler.handleError(e.message.toString())
        }
    }

    suspend fun getUserOrderDetails(orderId: Int) : Response<OrderModel>{
        return try{
            val response = api.getUserOrderDetails(orderId)
            responseHandler.handleSuccess(response)
        }catch (e:Exception){
            responseHandler.handleError(e.message.toString())
        }
    }

}