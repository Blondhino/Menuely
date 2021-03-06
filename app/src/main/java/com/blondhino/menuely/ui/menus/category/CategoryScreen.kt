package com.blondhino.menuely.ui.menus.category

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.blondhino.menuely.R
import com.blondhino.menuely.data.common.constants.NavigationRoutes.CATEGORY_SCREEN
import com.blondhino.menuely.data.common.constants.NavigationRoutes.PRODUCTS_SCREEN
import com.blondhino.menuely.data.common.enums.LoginStatus
import com.blondhino.menuely.data.common.enums.Mode
import com.blondhino.menuely.data.common.model.CategoryModel
import com.blondhino.menuely.data.common.response.MenuCategoryResponse
import com.blondhino.menuely.ui.cart.CartViewModel
import com.blondhino.menuely.ui.components.*
import com.blondhino.menuely.ui.menus.MenusViewModel
import com.blondhino.menuely.ui.ui.theme.greenDark
import com.blondhino.menuely.util.swapList

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CategoryScreen(navController: NavHostController,
                   menusViewModel: MenusViewModel,
                   loginStatus: LoginStatus,
                   cartViewModel: CartViewModel
) {
    val createCategoryDialogVisible = remember { mutableStateOf(false) }
    val updateDeleteDialogVisible = remember { mutableStateOf(false) }
    val deleteAlertDialogVisible = remember { mutableStateOf(false) }
    val preloadedImageForUpdateCategoryDialog = remember { mutableStateOf("") }
    val createUpdateCategoryDialogMode = remember { mutableStateOf(Mode.CREATE) }
    val context: Context = LocalContext.current
    var categoriesSnapShot = remember{ mutableStateListOf<MenuCategoryResponse>()}
    Log.d("currentRoute","${currentRoute(navController = navController)}")
    if (currentRoute(navController = navController)== CATEGORY_SCREEN) {
        if(cartViewModel.activeMenuId.value!=0){
            menusViewModel.fetchSingleMenu(cartViewModel.activeMenuId.value)
        }else{
            menusViewModel.fetchCategory()
        }
    }

    Box() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {

            Text(
                text = menusViewModel.selectedMenu.value.name.toString(),
                style = MaterialTheme.typography.h1,
                fontSize = 22.sp,
                modifier = Modifier.padding(16.dp)

            )
            MenuelyJumpingProgressBar(isLoading = menusViewModel.isLoading.value)

            LazyColumn() {
                for (category in menusViewModel.categories.value) {
                    Log.d(
                        "categoryDetails",
                        "in list--> name: ${category.name} id: ${category.id} "
                    )
                }
                categoriesSnapShot.swapList(menusViewModel.categories.value)

                itemsIndexed(items = categoriesSnapShot) { index, item ->
                    MenuelyCategoryTicket(
                        id = item.id,
                        titleText = item.name,
                        imageUrl = item.image?.url,
                        onItemClick = {
                            menusViewModel.selectedCategory.value = item
                            navController.navigate(PRODUCTS_SCREEN)
                        },
                        onItemLongClick = {
                            if (loginStatus==LoginStatus.LOGGED_AS_RESTAURANT) {
                                createUpdateCategoryDialogMode.value = Mode.EDIT
                                menusViewModel.selectedCategory.value = item
                                preloadedImageForUpdateCategoryDialog.value = item.image?.url.toString()
                                updateDeleteDialogVisible.value = true
                            }
                        }
                    )
                }

            }
        }

        FloatingActionButton(
            onClick = {
                if(loginStatus==LoginStatus.LOGGED_AS_RESTAURANT) {
                    createUpdateCategoryDialogMode.value = Mode.CREATE
                    createCategoryDialogVisible.value = true
                    menusViewModel.createCategoryModel.clear()
                }
            },
            shape = CircleShape,
            backgroundColor = greenDark,
            modifier = Modifier
                .align(
                    Alignment.BottomEnd
                )
                .padding(horizontal = 16.dp, vertical = 80.dp)
                .alpha(if (loginStatus == LoginStatus.LOGGED_AS_USER) 0F else 1F)
        ) {
            Icon(imageVector = Icons.Default.Add, "")
        }
    }



    if (createCategoryDialogVisible.value) {
        menusViewModel.selectedMenu.value.id?.let {
            menusViewModel.createCategoryModel.menuId.value = it
        }
        MenuelyCreateUpdateCategoryDialog(
            mode = createUpdateCategoryDialogMode.value,
            onDismiss = { createCategoryDialogVisible.value = false },
            preloadImageUrl = preloadedImageForUpdateCategoryDialog.value,
            onSave = {
                if (menusViewModel.createCategoryModel.validate()) {
                    createCategoryDialogVisible.value = false
                    menusViewModel.createCategory()
                } else {
                    Toast.makeText(
                        context,
                        menusViewModel.createCategoryModel.errorMessage.value,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            },
            onUpdate ={
                createCategoryDialogVisible.value=false
                menusViewModel.updateMenuCategory()
            },
            onCategoryNameValueChange = {
                menusViewModel.createCategoryModel.name.value = it
            },
            onCategoryImageValueChanged = { bitmap, uri, multipart ->
                menusViewModel.createCategoryModel.image.value = multipart
            },
            categoryNameValue = menusViewModel.createCategoryModel.name.value
        )
    }

    if (updateDeleteDialogVisible.value) {
        MenuelyUpdateDeleteDialog(
            unitName = stringResource(R.string.category),
            onUpdateCLick = {
                updateDeleteDialogVisible.value = false
                menusViewModel.prepareForCategoryUpdate()
                createCategoryDialogVisible.value=true
            },
            onDeleteClick = {
                updateDeleteDialogVisible.value = false
                deleteAlertDialogVisible.value = true
            },
            onDismiss = {
                updateDeleteDialogVisible.value = false
            }
        )
    }
    if (deleteAlertDialogVisible.value) {
        MenuelyDeleteAlertDialog(
            unitName = stringResource(R.string.category),
            unitTitle = menusViewModel.selectedCategory.value?.name.toString(),
            onDeleteClick = {
                deleteAlertDialogVisible.value = false
                menusViewModel.deleteMenuCategory()
            },
            onDismiss = { deleteAlertDialogVisible.value = false }
        )
    }
}

@Composable
private fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}