package com.blondhino.menuely.ui.cart

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentRecomposeScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.blondhino.menuely.R
import com.blondhino.menuely.data.common.constants.NavigationRoutes
import com.blondhino.menuely.ui.components.*

@Composable
fun CartScreen(cartViewModel: CartViewModel) {
    val items = cartViewModel.cartProductsToShow.value
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = stringResource(R.string.your_cart),
            style = MaterialTheme.typography.h1,
            fontSize = 22.sp,
            modifier = Modifier.padding(16.dp)

        )
        LazyColumn() {
            itemsIndexed(items = items) { index, item ->
                item.orderedProductId?.let { id ->
                    item.quantity?.let { amount ->
                        MenuelyCartProductTicket(
                            id = id,
                            titleText = item.name.toString(),
                            imageUrl = item.imageUrl.toString(),
                            priceText = item.price.toString(),
                            currency = item.currency.toString(),
                            amount = amount,
                            onIncrement = {

                                //item.quantity = item.quantity!! +1
                                cartViewModel.incrementProduct(it)


                            },
                            onDecrement = {
                                cartViewModel.decrementProduct(it)
                            }
                            /*      onProductAmountChanged = {
                                      id, newAmount ->
                                      if(amount!=0){
                                          cartViewModel.changeAmountForProduct(id,newAmount)

                                      }
                                  }*/
                        )
                    }
                }
            }

        }
    }
}