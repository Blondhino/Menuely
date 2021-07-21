package com.blondhino.menuely.ui.menus.product

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.blondhino.menuely.ui.components.MenuelyCreateProductDialog
import com.blondhino.menuely.ui.components.MenuelyJumpingProgressBar
import com.blondhino.menuely.ui.components.MenuelyProductTicket
import com.blondhino.menuely.ui.components.MenuelyProductsHeader
import com.blondhino.menuely.ui.menus.MenusViewModel
import com.blondhino.menuely.ui.ui.theme.greenDark

@Composable
fun ProductsScreen(navController: NavController, menusViewModel: MenusViewModel) {
    val context = LocalContext.current
    val selectedCategory = menusViewModel.selectedCategory.value
    var createNewProductDialogVisible = remember { mutableStateOf(false) }
    menusViewModel.fetchProducts()

    Box() {

        Column(modifier = Modifier.fillMaxSize()) {
            MenuelyProductsHeader(
                titleText = selectedCategory?.name.toString(),
                headerUrl = selectedCategory?.image?.url.toString(),
                height = 220.dp
            )

            MenuelyJumpingProgressBar(isLoading = menusViewModel.isLoading.value)

            LazyColumn(modifier= Modifier.padding(top=16.dp)) {
                itemsIndexed(items = menusViewModel.products.value) { index, item ->

                    item.id?.let { id ->
                        MenuelyProductTicket(
                            id = id,
                            titleText = item.name.toString(),
                            descriptionText = item.description.toString(),
                            priceText = item.price.toString(),
                            currency = item.currency.toString(),
                            imageUrl =  item.image?.url.toString(),
                            onItemClick = {
                                clickedProductId->
                            }
                        )
                    }
                }
            }

        }

        FloatingActionButton(
            onClick = {
                createNewProductDialogVisible.value = true
            },
            shape = CircleShape,
            backgroundColor = greenDark,
            modifier = Modifier
                .align(
                    Alignment.BottomEnd
                )
                .padding(horizontal = 16.dp, vertical = 80.dp)
        ) {
            Icon(imageVector = Icons.Default.Add, "")
        }

    }


    if (createNewProductDialogVisible.value) {
        MenuelyCreateProductDialog(
            onDismiss = { createNewProductDialogVisible.value = false },
            onSave = {
                if (menusViewModel.createProductModel.validate()) {
                    menusViewModel.createProduct()
                    createNewProductDialogVisible.value = false
                } else {
                    Toast.makeText(
                        context,
                        menusViewModel.createProductModel.errorMessage.value,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            },

            currency = menusViewModel.selectedCategory.value?.currency.toString(),
            productNameValue = menusViewModel.createProductModel.name.value,
            onProductNameValueChange = { value ->
                menusViewModel.createProductModel.name.value = value
            },
            priceValue = menusViewModel.createProductModel.price.value,
            onPriceValueChange = { value ->
                menusViewModel.createProductModel.price.value =
                    value; menusViewModel.createProductModel.price
            },
            descriptionValue = menusViewModel.createProductModel.description.value,
            onDescriptionValueChange = { value ->
                menusViewModel.createProductModel.description.value = value
            },
            onProductImageValueChanged = { bitmap, uri, multipart ->
                menusViewModel.createProductModel.image.value = multipart
            }
        )
    }

}