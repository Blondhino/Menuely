package com.blondhino.menuely.ui.cart

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor() : ViewModel() {
    val scannedTableId = mutableStateOf(0)
    val scannedRestaurantId = mutableStateOf(0)
    val activeMenuId = mutableStateOf(0)


}