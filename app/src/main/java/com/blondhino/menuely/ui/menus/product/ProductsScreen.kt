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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.blondhino.menuely.R
import com.blondhino.menuely.data.common.enums.LoginStatus
import com.blondhino.menuely.data.common.enums.Mode
import com.blondhino.menuely.ui.cart.CartViewModel
import com.blondhino.menuely.ui.components.*
import com.blondhino.menuely.ui.menus.MenusViewModel
import com.blondhino.menuely.ui.ui.theme.greenDark

@Composable
fun ProductsScreen(
    navController: NavController,
    menusViewModel: MenusViewModel,
    loginStatus: LoginStatus,
    cartViewModel: CartViewModel
) {
    val context = LocalContext.current
    val selectedCategory = menusViewModel.selectedCategory.value
    var createUpdateProductDialogVisible = remember { mutableStateOf(false) }
    var updateDeleteProductDialogVisible = remember { mutableStateOf(false) }
    var deleteProductAlertDialogVisible = remember { mutableStateOf(false) }
    var addProductToCartDialogVisible = remember { mutableStateOf(false) }
    var createUpdateProductDialogMode = remember { mutableStateOf(Mode.CREATE) }
    var preloadProductImageForUpdate = remember { mutableStateOf("") }
    var ammount = remember { mutableStateOf(0) }
    menusViewModel.fetchProducts()

    Box() {

        Column(modifier = Modifier.fillMaxSize()) {
            MenuelyProductsHeader(
                titleText = selectedCategory?.name.toString(),
                headerUrl = selectedCategory?.image?.url.toString(),
                height = 220.dp
            )

            MenuelyJumpingProgressBar(isLoading = menusViewModel.isLoading.value)

            LazyColumn(modifier = Modifier.padding(top = 16.dp)) {
                itemsIndexed(items = menusViewModel.products.value) { index, item ->

                    item.id?.let { id ->
                        MenuelyProductTicket(
                            id = id,
                            titleText = item.name.toString(),
                            descriptionText = item.description.toString(),
                            priceText = item.price.toString(),
                            currency = menusViewModel.selectedMenu.value.currency.toString(),
                            imageUrl = item.image?.url.toString(),
                            onItemClick = { clickedProductId ->
                                menusViewModel.selectedProduct.value = item
                                if (cartViewModel.scannedRestaurantId.value != 0) {
                                    addProductToCartDialogVisible.value = true
                                }
                            },
                            onItemLongClick = {
                                if (loginStatus == LoginStatus.LOGGED_AS_RESTAURANT) {
                                    preloadProductImageForUpdate.value = item.image?.url.toString()
                                    createUpdateProductDialogMode.value = Mode.EDIT
                                    menusViewModel.selectedProduct.value = item
                                    updateDeleteProductDialogVisible.value = true
                                }

                            }
                        )
                    }
                }
            }

        }

        FloatingActionButton(
            onClick = {
                if (loginStatus == LoginStatus.LOGGED_AS_RESTAURANT) {
                    preloadProductImageForUpdate.value = ""
                    createUpdateProductDialogMode.value = Mode.CREATE
                    menusViewModel.createProductModel.clear()
                    createUpdateProductDialogVisible.value = true
                }
            },
            shape = CircleShape,
            backgroundColor = greenDark,
            modifier = Modifier
                .align(
                    Alignment.BottomEnd
                )
                .padding(horizontal = 16.dp, vertical = 80.dp)
                .alpha(if (loginStatus == LoginStatus.LOGGED_AS_RESTAURANT) 1F else 0F)
        ) {
            Icon(imageVector = Icons.Default.Add, "")
        }

    }


    if (createUpdateProductDialogVisible.value) {
        MenuelyCreateUpdateProductDialog(
            mode = createUpdateProductDialogMode.value,
            onDismiss = { createUpdateProductDialogVisible.value = false },
            onSave = {
                if (menusViewModel.createProductModel.validate()) {
                    menusViewModel.createProduct()
                    createUpdateProductDialogVisible.value = false
                } else {
                    Toast.makeText(
                        context,
                        menusViewModel.createProductModel.errorMessage.value,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            },

            onUpdate = {
                createUpdateProductDialogVisible.value = false
                menusViewModel.updateProduct()

            },
            currency = menusViewModel.selectedMenu.value.currency.toString(),
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
            },
            preloadImage = preloadProductImageForUpdate.value
        )
    }

    if (updateDeleteProductDialogVisible.value) {
        MenuelyUpdateDeleteDialog(
            unitName = stringResource(R.string.product),
            onUpdateCLick = {
                menusViewModel.prepareForProductUpdate()
                updateDeleteProductDialogVisible.value = false
                createUpdateProductDialogVisible.value = true
            },
            onDeleteClick = {
                updateDeleteProductDialogVisible.value = false
                deleteProductAlertDialogVisible.value = true
            },
            onDismiss = {
                updateDeleteProductDialogVisible.value = false
            })
    }

    if (deleteProductAlertDialogVisible.value) {
        MenuelyDeleteAlertDialog(
            unitName = stringResource(R.string.product),
            unitTitle = menusViewModel.selectedProduct.value?.name.toString(),
            onDeleteClick = {
                deleteProductAlertDialogVisible.value = false
                menusViewModel.deleteProduct()
            },
            onDismiss = {
                deleteProductAlertDialogVisible.value = false
            })
    }

    if (addProductToCartDialogVisible.value) {
        MenuelyAddProductToCartDialog(
            productName = menusViewModel.selectedProduct.value?.name.toString(),
            onDismiss = { addProductToCartDialogVisible.value = false },
            productImageUrl = menusViewModel.selectedProduct.value?.image?.url.toString()
        ) {
            menusViewModel.selectedProduct.value?.let { cartViewModel.addProductToCart(it, 1) }
            Toast.makeText(
                context,
                "${menusViewModel.selectedProduct.value?.name.toString()} added to cart",
                Toast.LENGTH_SHORT
            ).show()
            addProductToCartDialogVisible.value=false
        }
    }

}