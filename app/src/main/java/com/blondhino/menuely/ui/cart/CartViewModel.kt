package com.blondhino.menuely.ui.cart

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blondhino.menuely.data.common.model.CartProductModel
import com.blondhino.menuely.data.common.response.MenuProductsResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor() : ViewModel() {
    val scannedTableId = mutableStateOf(0)
    val scannedRestaurantId = mutableStateOf(0)
    val activeMenuId = mutableStateOf(0)
    var cartProducts: MutableLiveData<ArrayList<CartProductModel>> = MutableLiveData(arrayListOf())
    val cartProductsToShow: MutableState<List<CartProductModel>> = mutableStateOf(ArrayList())


    fun addProductToCart(productModel: MenuProductsResponse, amount: Int) {

        for (product in cartProducts.value!!) {
            if (product.orderedProductId == productModel.id) {
                product.quantity = product.quantity?.plus(amount)
                Log.d("CartProducts", "size: ${cartProducts.value!!.size}")
                for (products in cartProducts.value!!) {
                    Log.d("CartProducts", "name: ${products.name} amount: ${products.quantity}")
                }
                cartProductsToShow.value = cartProducts.value!!.toList()
                return
            }
        }
        cartProducts.value!!.add(
            CartProductModel(
                productModel.id,
                quantity = amount,
                price = productModel.price?.toDouble(),
                name = productModel.name,
                imageUrl = productModel.image?.url,
                currency = productModel.currency
            )
        )

        Log.d("CartProducts", "size: ${cartProducts.value!!.size}")

        for (product in cartProducts.value!!) {
            Log.d("CartProducts", "name: ${product.name} amount: ${product.quantity}")
        }
        cartProductsToShow.value = cartProducts.value!!.toList()
    }

    fun clearCart() {
        cartProducts.value?.clear()
        cartProductsToShow.value = cartProducts.value!!.toList()
    }

    fun isCartEmpty(): Boolean = cartProducts.value?.size == 0

    fun incrementProduct(id:Int) = viewModelScope.launch{
        for (product in cartProducts.value!!) {
            if (product.orderedProductId == id) {
                product.quantity= product.quantity?.plus(1)
            }
        }
        cartProductsToShow.value = cartProductsToShow.value.drop(cartProducts.value!!.size)
        cartProductsToShow.value = cartProducts.value!!.toList()
    }

    fun decrementProduct(id:Int) {
        for (product in cartProducts.value!!) {
            if (product.orderedProductId == id) {
                if(product.quantity==1){
                    removeProductWithId(product.orderedProductId)
                    cartProductsToShow.value = cartProductsToShow.value.drop(cartProducts.value!!.size)
                    cartProductsToShow.value = cartProducts.value!!.toList()
                    return
                }else {
                    product.quantity = product.quantity?.minus(1)
                }
            }
        }
        try {
            cartProductsToShow.value = cartProductsToShow.value.drop(cartProducts.value!!.size)
            cartProductsToShow.value = cartProducts.value!!.toList()
        } catch (e: Exception) {
        }
    }

    private fun removeProductWithId(orderedProductId: Int?){
        cartProducts.value!!.forEachIndexed { index, cartProductModel ->
            if(cartProductModel.orderedProductId==orderedProductId) {
                try {
                    cartProducts.value!!.removeAt(index)
                    return
                } catch (e: Exception) {}
                return
            }
        }
    }

}