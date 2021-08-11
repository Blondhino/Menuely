package com.blondhino.menuely.ui.orders.userOrderDetails

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.blondhino.menuely.R
import com.blondhino.menuely.ui.components.MenuelyJumpingProgressBar
import com.blondhino.menuely.ui.components.MenuelyOrderedProductDetailsTicket
import com.blondhino.menuely.ui.orders.OrdersViewModel
import com.blondhino.menuely.ui.ui.theme.greyMedium

@Composable
fun UserOrderDetailScreen(ordersViewModel: OrdersViewModel) {
    ordersViewModel.getUserOrderDetails()
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (progress, content, details) = createRefs()

        MenuelyJumpingProgressBar(
            isLoading = ordersViewModel.isLoading.value,
            modifier = Modifier.constrainAs(progress) {
                top.linkTo(parent.top)
            }
        )

        Card(modifier = Modifier
            .constrainAs(details) {
                bottom.linkTo(parent.bottom, 16.dp)
                end.linkTo(parent.end, 8.dp)
                start.linkTo(parent.start, 8.dp)
                width = Dimension.fillToConstraints
            }
            .height(150.dp), backgroundColor = greyMedium, shape = RoundedCornerShape(15.dp)) {
            Column(verticalArrangement = Arrangement.SpaceEvenly) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(R.string.waiter),
                        style = MaterialTheme.typography.h5,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )

                    Text(
                        text = if (ordersViewModel.userOrderDetails.value?.employeeName != null) {
                            ordersViewModel.userOrderDetails.value?.employeeName.toString()
                        } else {
                            stringResource(R.string.not_assignet_yet)
                        },
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
                        text = if (ordersViewModel.userOrderDetails.value?.tableId != null) {
                            ordersViewModel.userOrderDetails.value?.tableId.toString()
                        } else {
                            "--"
                        },
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
                        text = if (ordersViewModel.userOrderDetails.value?.totalPrice != null) {
                            ordersViewModel.userOrderDetails.value?.totalPrice.toString() + " " + ordersViewModel.orderedCurrency.value
                        } else {
                            "--"
                        },
                        style = MaterialTheme.typography.h3,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                }

            }
        }


        LazyColumn(modifier = Modifier
            .padding(8.dp)
            .constrainAs(content) {
                top.linkTo(progress.bottom)
                bottom.linkTo(details.top, 8.dp)
                height = Dimension.fillToConstraints
            }) {
            itemsIndexed(items = ordersViewModel.userOrderedProducts.value) { index, item ->

                item.quantity?.let { item.price?.div(it).toString() }?.let {
                    MenuelyOrderedProductDetailsTicket(
                        id = 0,
                        titleText = item.name.toString(),
                        priceText = it,
                        currency = ordersViewModel.orderedCurrency.value,
                        imageUrl = item.imageUrl.toString(),
                        amount = item.quantity.toString()
                    )
                }

            }
        }

    }


}