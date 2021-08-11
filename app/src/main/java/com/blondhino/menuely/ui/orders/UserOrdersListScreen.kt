package com.blondhino.menuely.ui.orders

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.blondhino.menuely.data.common.constants.NavigationRoutes.USER_ORDER_DETAILS_SCREEN
import com.blondhino.menuely.data.common.enums.LoginStatus
import com.blondhino.menuely.data.common.enums.Mode
import com.blondhino.menuely.ui.components.MenuelyOrderTicket
import com.blondhino.menuely.ui.components.MenuelyProductTicket

@Composable
fun UserOrdersListScreen(navController: NavHostController, ordersViewModel: OrdersViewModel) {
    ordersViewModel.getUserOrders()

    LazyColumn(modifier = Modifier.padding(8.dp)) {
        itemsIndexed(items = ordersViewModel.userOrders.value) { index, item ->

            item.id?.let { id ->
                MenuelyOrderTicket(
                    id = id,
                    titleText = item.employerName.toString(),
                    priceText = item.totalPrice.toString(),
                    currency = item.currency.toString(),
                    onOrderClicked = { orderId ->
                        ordersViewModel.lastSelectedOrderId.value=0
                        ordersViewModel.selectedOrderId.value = orderId
                        navController.navigate(USER_ORDER_DETAILS_SCREEN)
                    }
                )
            }
        }
    }
}