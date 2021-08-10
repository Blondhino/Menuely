package com.blondhino.menuely.ui.cart

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavHostController
import com.blondhino.menuely.R
import com.blondhino.menuely.data.common.constants.NavigationRoutes
import com.blondhino.menuely.data.common.constants.NavigationRoutes.RESTAURANT_SCREEN_SINGLE
import com.blondhino.menuely.data.common.constants.NavigationRoutes.SCAN_SCREEN
import com.blondhino.menuely.ui.components.*
import com.blondhino.menuely.ui.ui.theme.greyMedium

@Composable
fun CartScreen(cartViewModel: CartViewModel, navController: NavHostController) {
    val items = cartViewModel.cartProductsToShow.value
    val context = LocalContext.current
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            Text(
                text = stringResource(R.string.your_cart),
                style = MaterialTheme.typography.h1,
                fontSize = 22.sp,
                modifier = Modifier.padding(16.dp)

            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.6f)
            ) {
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
                                )
                            }
                        }
                    }

                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Bottom
            ) {
                ConstraintLayout(modifier = Modifier.fillMaxSize()) {
                    val (info, button) = createRefs()


                    MenuelyButton(
                        text = stringResource(R.string.submit_order),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(70.dp)
                            .padding(8.dp)
                            .constrainAs(button) {
                                bottom.linkTo(parent.bottom)
                            }
                    ) {
                        if (cartViewModel.isCartEmpty()) {
                            Toast.makeText(
                                context,
                                context.getString(R.string.emptyCart),
                                Toast.LENGTH_SHORT
                            ).show()
                        }else {
                            cartViewModel.submitOrder()
                        }
                    }

                    Card(modifier = Modifier.constrainAs(info) {
                        top.linkTo(parent.top)
                        bottom.linkTo(button.top)
                        end.linkTo(parent.end, 8.dp)
                        start.linkTo(parent.start, 8.dp)
                        width = Dimension.fillToConstraints
                        height = Dimension.fillToConstraints
                    }, backgroundColor = greyMedium, shape = RoundedCornerShape(15.dp)) {
                        Column(verticalArrangement = Arrangement.SpaceEvenly) {
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = stringResource(R.string.client),
                                    style = MaterialTheme.typography.h5,
                                    modifier = Modifier.padding(horizontal = 8.dp)
                                )

                                Text(
                                    text = cartViewModel.client,
                                    style = MaterialTheme.typography.h3,
                                    fontSize = 14.sp,
                                    modifier = Modifier.padding(horizontal = 8.dp)
                                )
                            }

                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = stringResource(R.string.table_num),
                                    style = MaterialTheme.typography.h5,
                                    modifier = Modifier.padding(horizontal = 8.dp)
                                )

                                Text(
                                    text = cartViewModel.scannedTableId.value.toString(),
                                    style = MaterialTheme.typography.h3,
                                    fontSize = 14.sp,
                                    modifier = Modifier.padding(horizontal = 8.dp)
                                )
                            }

                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = stringResource(R.string.total),
                                    style = MaterialTheme.typography.h5,
                                    modifier = Modifier.padding(horizontal = 8.dp)
                                )

                                Text(
                                    text = cartViewModel.totalPrice.value,
                                    style = MaterialTheme.typography.h3,
                                    fontSize = 14.sp,
                                    modifier = Modifier.padding(horizontal = 8.dp)
                                )
                            }

                        }
                    }

                }

            }

        }
        MenuelyCircularProgressBar(isLoading = cartViewModel.isLoading.value)
    }

    if (cartViewModel.submitted.value) {
        navController.navigateUp()
        cartViewModel.clearCart()
        Toast.makeText(
            LocalContext.current,
            stringResource(R.string.order_submited),
            Toast.LENGTH_SHORT
        ).show()
        cartViewModel.submitted.value=false
        cartViewModel.resetPrice()
    }
}