package com.blondhino.menuely.data.repo

import android.util.Log
import com.blondhino.menuely.data.common.MenuelyApi
import com.blondhino.menuely.data.common.Response
import com.blondhino.menuely.data.common.ResponseHandler
import com.blondhino.menuely.data.common.model.RestaurantModel
import com.blondhino.menuely.data.common.response.EmptyResponse
import com.blondhino.menuely.data.database.tables.RestaurantTableModel
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody


class RestaurantRepo(
    private val api: MenuelyApi,
    private val responseHandler: ResponseHandler,
) {
    suspend fun searchRestaurants(search: String): Response<ArrayList<RestaurantModel>> {
        return try {
            val response = api.searchRestaurants(search)
            responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            responseHandler.handleError(e.message.toString())
        }
    }

    suspend fun getSingleRestaurant(id: Int): Response<RestaurantModel> {
        return try {
            val response = api.getSingleRestaurant(id)
            responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            responseHandler.handleError(e.message.toString())
        }
    }

    suspend fun getMyRestaurantProfile(): Response<RestaurantModel> {
        return try {
            val response = api.getMyRestaurantProfile()
            responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            responseHandler.handleError(e.message.toString())
        }
    }

    suspend fun updateRestaurantName(name:String):Response<EmptyResponse>{
        return try {
            val response = api.updateRestaurantProfile(RestaurantModel(name = name))
            responseHandler.handleSuccess(response)
        }catch (e:Exception){
            responseHandler.handleError(e.message.toString())
        }
    }

    suspend fun updateRestaurantDescription(description:String):Response<EmptyResponse>{
        return try {
            val response = api.updateRestaurantProfile(RestaurantModel(description = description))
            responseHandler.handleSuccess(response)
        }catch (e:Exception){
            responseHandler.handleError(e.message.toString())
        }
    }

    suspend fun updateRestaurantAddress(address:String):Response<EmptyResponse>{
        return try {
            val response = api.updateRestaurantProfile(RestaurantModel(address = address))
            responseHandler.handleSuccess(response)
        }catch (e:Exception){
            responseHandler.handleError(e.message.toString())
        }
    }

    suspend fun updateRestaurantCity(city:String):Response<EmptyResponse>{
        return try {
            val response = api.updateRestaurantProfile(RestaurantModel(city = city))
            responseHandler.handleSuccess(response)
        }catch (e:Exception){
            responseHandler.handleError(e.message.toString())
        }
    }

    suspend fun updateRestaurantPostalCode(postalCode:String):Response<EmptyResponse>{
        return try {
            val response = api.updateRestaurantProfile(RestaurantModel(postalCode = postalCode))
            responseHandler.handleSuccess(response)
        }catch (e:Exception){
            responseHandler.handleError(e.message.toString())
        }
    }

    suspend fun updateRestaurantCountry(country:String):Response<EmptyResponse>{
        return try {
            val response = api.updateRestaurantProfile(RestaurantModel(country = country))
            responseHandler.handleSuccess(response)
        }catch (e:Exception){
            responseHandler.handleError(e.message.toString())
        }
    }

    suspend fun updateCoverImage(multipart: MultipartBody.Part): Response<EmptyResponse>? {
        return try {
            val response = api.updateImageOnRestaurantProfile(multipart, createPartFromString("cover"))
            responseHandler.handleSuccess(response)

        } catch (e: Exception) {
            Log.d("EXception", e.message.toString())
            responseHandler.handleError(e.message.toString())
        }
    }

    suspend fun updateProfileImage(multipart: MultipartBody.Part): Response<EmptyResponse>? {
        return try {
            val response = api.updateImageOnRestaurantProfile(multipart, createPartFromString("profile"))
            responseHandler.handleSuccess(response)

        } catch (e: Exception) {
            Log.d("EXception", e.message.toString())
            responseHandler.handleError(e.message.toString())
        }
    }

    private fun createPartFromString(kind: String): RequestBody? {
        return kind
            .toRequestBody(MultipartBody.FORM)
    }


}