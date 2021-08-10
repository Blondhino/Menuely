package com.blondhino.menuely.data.common.request

import com.blondhino.menuely.data.common.model.CartProductModel

class CreateOrderRequest(
    val restaurantId: Int,
    val tableId: Int,
    val totalPrice: Double,
    val orderedProducts: ArrayList<CartProductModel>,
)
