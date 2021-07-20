package com.blondhino.menuely.data.repo

import com.blondhino.menuely.data.common.MenuelyApi
import com.blondhino.menuely.data.common.Response
import com.blondhino.menuely.data.common.ResponseHandler
import com.blondhino.menuely.data.common.model.*
import com.blondhino.menuely.data.common.response.EmptyResponse
import com.blondhino.menuely.data.common.response.MenuCategoryResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody


class MenusRepo(
    private val api: MenuelyApi,
    private val responseHandler: ResponseHandler,
) {

    suspend fun getRestaurantMenus(restaurantId: Int): Response<ArrayList<MenuModel>> {
        return try {
            val response = api.getRestaurantMenus(restaurantId)
            responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            responseHandler.handleError(e.message.toString())
        }
    }


    suspend fun createRestaurantMenu(menuModel: MenuModel): Response<EmptyResponse> {
        return try {
            val response = api.createRestaurantMenu(menuModel)
            responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            responseHandler.handleError(e.message.toString())
        }

    }

    suspend fun getCategoriesForMenu(menuId: Int): Response<ArrayList<MenuCategoryResponse>> {
        return try {
            val response = api.getCategoriesForMenu(menuId)
            responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            responseHandler.handleError(e.message.toString())
        }
    }


    suspend fun createMenuCategory(categoryModel: CategoryModel): Response<EmptyResponse> {
        return try {
            val response = categoryModel.image?.let { image ->
                api.createCategory(
                    image = image,
                    menuId = createPart(categoryModel.menuId.toString()),
                    name = createPart(categoryModel.name.toString())
                )
            }
            responseHandler.handleSuccess(response!!)
        } catch (e: Exception) {
            responseHandler.handleError(e.message.toString())
        }

    }

    private fun createPart(part: String): RequestBody {
        return part
            .toRequestBody(MultipartBody.FORM)
    }


}