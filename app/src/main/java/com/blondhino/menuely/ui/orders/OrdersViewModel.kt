package com.blondhino.menuely.ui.orders

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blondhino.menuely.data.common.enums.Status
import com.blondhino.menuely.data.common.model.CartProductModel
import com.blondhino.menuely.data.common.model.MenuModel
import com.blondhino.menuely.data.common.model.OrderModel
import com.blondhino.menuely.data.repo.OrdersRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrdersViewModel @Inject constructor(private val repo: OrdersRepo) : ViewModel() {

    val getUserOrdersFetched = mutableStateOf(false)
    val userOrders: MutableState<List<OrderModel>> = mutableStateOf(ArrayList())
    val userOrderDetails: MutableState<OrderModel?> = mutableStateOf(null)
    val orderedCurrency = mutableStateOf("")
    val userOrderedProducts: MutableState<List<CartProductModel>> = mutableStateOf(ArrayList())
    val selectedOrderId = mutableStateOf(0)
    val lastSelectedOrderId = mutableStateOf(-1)
    val isLoading = mutableStateOf(false)

    fun getUserOrders() {

        if (!getUserOrdersFetched.value) {
            isLoading.value=true
            viewModelScope.launch {
                val response = repo.getUserOrders()
                if(response.status==Status.SUCCESS)
                {
                    response.data?.let {
                        userOrders.value = it
                    }
                    isLoading.value=false
                }else{
                    isLoading.value=false
                }
            }
            getUserOrdersFetched.value = true
        }
    }

    fun getUserOrderDetails() {
        if (selectedOrderId.value != lastSelectedOrderId.value) {
            viewModelScope.launch {
                isLoading.value=true
                userOrderedProducts.value =
                    userOrderedProducts.value.drop(userOrderedProducts.value.size)
                userOrderDetails.value=null
                lastSelectedOrderId.value = selectedOrderId.value
                val response = repo.getUserOrderDetails(selectedOrderId.value)
                if(response.status==Status.SUCCESS){
                    response.data?.let {
                        userOrderDetails.value = it
                        userOrderedProducts.value = it.orderedProducts?.toList()!!
                        orderedCurrency.value = it.currency.toString()
                        isLoading.value=false
                    }
                }else{
                    isLoading.value=false
                }
            }
        }
    }
}