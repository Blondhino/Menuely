package com.blondhino.menuely.data.common.model

class OrderModel(
    var id: Int?,
    var totalPrice :Double?,
    val currency : String?,
    val employerName :String?,
    val tableId:String?,
    val employeeName:String?,
    val orderedProducts : ArrayList<CartProductModel>?
){

}





