package com.blondhino.menuely.ui.menus.category

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.blondhino.menuely.data.common.constants.NavigationRoutes
import com.blondhino.menuely.data.common.constants.NavigationRoutes.PRODUCTS_SCREEN
import com.blondhino.menuely.ui.components.MenuelyCategoryTicket
import com.blondhino.menuely.ui.components.MenuelyCreateCategoryDialog
import com.blondhino.menuely.ui.components.MenuelyJumpingProgressBar
import com.blondhino.menuely.ui.components.MenuelyMenuTicket
import com.blondhino.menuely.ui.menus.MenusViewModel
import com.blondhino.menuely.ui.ui.theme.greenDark

@Composable
fun CategoryScreen(navController: NavHostController, menusViewModel: MenusViewModel) {
    val createCategoryDialogVisible = remember { mutableStateOf(false) }
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
                            item.image?.url?.let {image->
                                MenuelyCategoryTicket(
                                    id = id,
                                    titleText = title,
                                    imageUrl = image,
                                    onItemClick = {
                                        menusViewModel.selectedCategory.value=item
                                        navController.navigate(PRODUCTS_SCREEN)
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
                createCategoryDialogVisible.value = true
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
        MenuelyCreateCategoryDialog(
            onDismiss = { createCategoryDialogVisible.value = false },
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
            onCategoryNameValueChange = {
                menusViewModel.createCategoryModel.name.value = it
            },
            onCategoryImageValueChanged = { bitmap, uri, multipart ->
                menusViewModel.createCategoryModel.image.value = multipart
            },
            categoryNameValue = menusViewModel.createCategoryModel.name.value
        )
    }
}