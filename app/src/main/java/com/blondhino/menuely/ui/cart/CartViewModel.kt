package com.blondhino.menuely.ui.cart

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blondhino.menuely.data.common.enums.Status
import com.blondhino.menuely.data.common.model.CartProductModel
import com.blondhino.menuely.data.common.response.MenuProductsResponse
import com.blondhino.menuely.data.database.dao.UserDao
import com.blondhino.menuely.data.repo.OrdersRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.round

@HiltViewModel
class CartViewModel @Inject constructor(userDao: UserDao, private val ordersRepo: OrdersRepo) :
    ViewModel() {
    val scannedTableId = mutableStateOf(0)
    val scannedRestaurantId = mutableStateOf(0)
    val activeMenuId = mutableStateOf(0)
    val totalPrice = mutableStateOf("--")
    val isLoading = mutableStateOf(false)
    val submitted = mutableStateOf(false)
    private var price = 0.0
    val client = userDao.getUser()?.firstname + " " + userDao.getUser()?.lastname
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
                calculatePrice()
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
        calculatePrice()
    }

    fun submitOrder() = viewModelScope.launch {
        isLoading.value=true
        submitted.value=false
        val response = cartProducts.value?.let {
            ordersRepo.submitOrder(
                restaurantId = scannedRestaurantId.value,
                tableId = scannedTableId.value,
                totalPrice = price.round(2),
                orderedProducts = it
            )
        }
        if(response?.status==Status.SUCCESS){
            submitted.value=true
        }
        isLoading.value=false
    }

    private fun calculatePrice() {
        price=0.0
        for (product in cartProducts.value!!) {
            price += (product.quantity?.times(product.price!!)!!)
        }
        if (cartProducts.value!!.size > 0) {
            totalPrice.value = price.round(2).toString() + " " + cartProducts.value!![0].currency
        } else {
            totalPrice.value = "--"
        }
    }

    fun clearCart() {
        cartProducts.value?.clear()
        cartProductsToShow.value = cartProducts.value!!.toList()
    }

    fun isCartEmpty(): Boolean = cartProducts.value?.size == 0

    fun incrementProduct(id: Int) = viewModelScope.launch {
        for (product in cartProducts.value!!) {
            if (product.orderedProductId == id) {
                product.quantity = product.quantity?.plus(1)
            }
        }
        cartProductsToShow.value = cartProductsToShow.value.drop(cartProducts.value!!.size)
        cartProductsToShow.value = cartProducts.value!!.toList()
        calculatePrice()
    }

    fun decrementProduct(id: Int) {
        for (product in cartProducts.value!!) {
            if (product.orderedProductId == id) {
                if (product.quantity == 1) {
                    removeProductWithId(product.orderedProductId)
                    cartProductsToShow.value =
                        cartProductsToShow.value.drop(cartProducts.value!!.size)
                    cartProductsToShow.value = cartProducts.value!!.toList()
                    calculatePrice()
                    return
                } else {
                    product.quantity = product.quantity?.minus(1)
                    calculatePrice()
                }
            }
        }
        try {
            cartProductsToShow.value = cartProductsToShow.value.drop(cartProducts.value!!.size)
            cartProductsToShow.value = cartProducts.value!!.toList()
            calculatePrice()
        } catch (e: Exception) {
        }
    }

    private fun removeProductWithId(orderedProductId: Int?) {
        cartProducts.value!!.forEachIndexed { index, cartProductModel ->
            if (cartProductModel.orderedProductId == orderedProductId) {
                try {
                    cartProducts.value!!.removeAt(index)
                    calculatePrice()
                    return
                } catch (e: Exception) {
                }
                calculatePrice()
                return
            }
        }
    }


    fun Double.round(decimals: Int): Double {
        var multiplier = 1.0
        repeat(decimals) { multiplier *= 10 }
        return round(this * multiplier) / multiplier
    }

    fun resetPrice() {
        totalPrice.value="--"
    }
}