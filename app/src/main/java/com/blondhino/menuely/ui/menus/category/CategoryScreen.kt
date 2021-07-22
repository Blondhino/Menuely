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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.blondhino.menuely.R
import com.blondhino.menuely.data.common.constants.NavigationRoutes.PRODUCTS_SCREEN
import com.blondhino.menuely.data.common.enums.Mode
import com.blondhino.menuely.ui.components.*
import com.blondhino.menuely.ui.menus.MenusViewModel
import com.blondhino.menuely.ui.ui.theme.greenDark

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CategoryScreen(navController: NavHostController, menusViewModel: MenusViewModel) {
    val createCategoryDialogVisible = remember { mutableStateOf(false) }
    val updateDeleteDialogVisible = remember { mutableStateOf(false) }
    val deleteAlertDialogVisible = remember { mutableStateOf(false) }
    val preloadedImageForUpdateCategoryDialog = remember { mutableStateOf("") }
    val createUpdateCategoryDialogMode = remember { mutableStateOf(Mode.CREATE) }
    val context: Context = LocalContext.current
    menusViewModel.fetchCategory()

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
                itemsIndexed(items = menusViewModel.categories.value) { index, item ->
                    item.id?.let { id ->
                        item.name?.let { title ->
                            item.image?.url?.let { image ->
                                MenuelyCategoryTicket(
                                    id = id,
                                    titleText = title,
                                    imageUrl = image,
                                    onItemClick = {
                                        menusViewModel.selectedCategory.value = item
                                        Log.d("imageDetails","category: ${item.image.url} selected: ${menusViewModel.selectedCategory.value!!.image?.url.toString()}")
                                        navController.navigate(PRODUCTS_SCREEN)
                                    },
                                    onItemLongClick = {
                                        createUpdateCategoryDialogMode.value=Mode.EDIT
                                        menusViewModel.selectedCategory.value = item
                                        preloadedImageForUpdateCategoryDialog.value=item.image.url.toString()
                                        updateDeleteDialogVisible.value = true
                                    }
                                )
                            }
                        }
                    }
                }
            }

        }

        FloatingActionButton(
            onClick = {
                createUpdateCategoryDialogMode.value=Mode.CREATE
                createCategoryDialogVisible.value = true
                menusViewModel.createCategoryModel.clear()
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