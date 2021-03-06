package com.blondhino.menuely.data.repo

import com.blondhino.menuely.data.common.MenuelyApi
import com.blondhino.menuely.data.common.Response
import com.blondhino.menuely.data.common.ResponseHandler
import com.blondhino.menuely.data.common.model.*
import com.blondhino.menuely.data.common.response.EmptyResponse
import com.blondhino.menuely.data.common.response.MenuCategoryResponse
import com.blondhino.menuely.data.common.response.MenuProductsResponse
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

    suspend fun updateRestaurantMenu(menuModel: MenuModel, menuId: Int): Response<EmptyResponse> {
        return try {
            val response = api.updateRestaurantMenu(menuModel,menuId)
            responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            responseHandler.handleError(e.message.toString())
        }

    }

    suspend fun getSingleMenu( menuId: Int): Response<MenuModel> {
        return try {
            val response = api.getSingleMenu(menuId)
            responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            responseHandler.handleError(e.message.toString())
        }

    }

    suspend fun deleteMenuProduct(id: Int): Response<EmptyResponse> {
        return try{
            val response = api.deleteMenuProduct(id)
            responseHandler.handleSuccess(response)
        }catch (e:Exception){
            responseHandler.handleError(e.message.toString())
        }
    }

    suspend fun deleteRestaurantMenu(id: Int): Response<EmptyResponse> {
        return try{
            val response = api.deleteMenu(id)
            responseHandler.handleSuccess(response)
        }catch (e:Exception){
            responseHandler.handleError(e.message.toString())
        }
    }

    suspend fun deleteRestaurantCategory(id: Int): Response<EmptyResponse> {
        return try{
            val response = api.deleteMenuCategory(id)
            responseHandler.handleSuccess(response)
        }catch (e:Exception){
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


    suspend fun getProductsForMenu(categoryId: Int): Response<ArrayList<MenuProductsResponse>> {
        return try {
            val response = api.getProductsForMenu(categoryId)
            responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            responseHandler.handleError(e.message.toString())
        }
    }


    suspend fun createMenuCategory(categoryModel: CategoryModel): Response<MenuCategoryResponse> {
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

    suspend fun updateMenuCategory(categoryModel: CategoryModel, id: Int): Response<EmptyResponse> {
        return try {
                val response = api.updateCategory(
                    id=id,
                    image = categoryModel.image,
                    menuId = createPart(categoryModel.menuId.toString()),
                    name = createPart(categoryModel.name.toString())
                )

            responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            responseHandler.handleError(e.message.toString())
        }

    }


    suspend fun createProduct(productModel: ProductModel): Response<EmptyResponse> {
        return try {
            val response = productModel.image?.let { image ->
                api.createProduct(
                    image = image,
                    categoryId = createPart(productModel.categoryId.toString()),
                    currency = createPart(productModel.currency.toString()),
                    price = createPart(productModel.price.toString()),
                    description = createPart(productModel.description.toString()),
                    name = createPart(productModel.name.toString())
                )
            }
            responseHandler.handleSuccess(response!!)
        } catch (e: Exception) {
            responseHandler.handleError(e.message.toString())
        }
    }

    suspend fun updateProduct(productModel: ProductModel, id:Int): Response<EmptyResponse> {
        return try {
            val response =
                api.updateProduct(
                    id=id,
                    image = productModel.image,
                    price = createPart(productModel.price.toString()),
                    description = createPart(productModel.description.toString()),
                    name = createPart(productModel.name.toString())
                )

            responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            responseHandler.handleError(e.message.toString())
        }
    }

    private fun createPart(part: String): RequestBody {
        return part
            .toRequestBody(MultipartBody.FORM)
    }


}