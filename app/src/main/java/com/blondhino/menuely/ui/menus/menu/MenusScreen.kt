package com.blondhino.menuely.ui.menus.menu

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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.blondhino.menuely.R
import com.blondhino.menuely.data.common.constants.NavigationRoutes.CATEGORY_SCREEN
import com.blondhino.menuely.data.common.enums.LoginStatus
import com.blondhino.menuely.data.common.enums.Mode
import com.blondhino.menuely.ui.components.*
import com.blondhino.menuely.ui.menus.MenusViewModel
import com.blondhino.menuely.ui.ui.theme.greenDark

@Composable
fun MenusScreen(navController: NavHostController, menusViewModel: MenusViewModel, loginStatus: LoginStatus) {
    val createMenuDialogVisible = remember { mutableStateOf(false) }
    val updateDeleteDialogVisible = remember { mutableStateOf(false) }
    val deleteAlertDialogVisible = remember { mutableStateOf(false) }
    val createUpdateDialogMode = remember { mutableStateOf(Mode.CREATE) }
    val context: Context = LocalContext.current
    menusViewModel.fetchMenus()

    Log.d("loginSTATUUS",loginStatus.name)

    Box() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {

            Text(
                text = stringResource(R.string.your_menus_manager),
                style = MaterialTheme.typography.h1,
                fontSize = 22.sp,
                modifier = Modifier.padding(16.dp)

            )
            MenuelyJumpingProgressBar(isLoading = menusViewModel.isLoading.value)

            LazyColumn() {
                itemsIndexed(items = menusViewModel.menus.value) { index, item ->
                    item.id?.let { id ->
                        item.name?.let { title ->
                            item.description?.let { description ->
                                MenuelyMenuTicket(
                                    onItemClick = {
                                        menusViewModel.selectedMenu.value = item
                                        navController.navigate(CATEGORY_SCREEN)
                                    },
                                    id = id,
                                    titleText = title,
                                    descText = description,
                                    onItemLongClick = {
                                        updateDeleteDialogVisible.value = true
                                        menusViewModel.selectedMenu.value = item
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
                if(loginStatus == LoginStatus.LOGGED_AS_RESTAURANT) {
                    createUpdateDialogMode.value = Mode.CREATE
                    menusViewModel.createMenuModel.clear()
                    createMenuDialogVisible.value = true
                }
            },
            shape = CircleShape,
            backgroundColor = greenDark,
            modifier = Modifier
                .align(
                    Alignment.BottomEnd
                )
                .padding(horizontal = 16.dp, vertical = 80.dp)
                .alpha(if(loginStatus==LoginStatus.LOGGED_AS_RESTAURANT)1F else 0F)
        ) {
            Icon(imageVector = Icons.Default.Add, "")
        }
    }

    if (createMenuDialogVisible.value) {
        MenuelyCreateMenuDialog(
            { createMenuDialogVisible.value = false },
            onSave = {
                if (menusViewModel.createMenuModel.validate()) {
                    createMenuDialogVisible.value = false
                    menusViewModel.createMenu()
                } else {
                    Toast.makeText(
                        context,
                        menusViewModel.createMenuModel.errorMessage.value,
                        Toast.LENGTH_LONG
                    ).show()
                }
            },

            onUpdate = {
                createMenuDialogVisible.value=false
                menusViewModel.updateMenu()
            },
            mode = createUpdateDialogMode.value
            ,menuNameValue = menusViewModel.createMenuModel.name.value,
            onMenuNameValueChange = { name -> menusViewModel.createMenuModel.name.value = name },
            currencyValue = menusViewModel.createMenuModel.currency.value,
            onCurrencyValueChange = { currency ->
                menusViewModel.createMenuModel.currency.value = currency
            },
            numberOfTablesValue = menusViewModel.createMenuModel.numberOfTables.value,
            onNumberOfTablesChange = { num ->
                menusViewModel.createMenuModel.numberOfTables.value = num
            },
            descriptionValue = menusViewModel.createMenuModel.description.value,
            onDescriptionValueChange = { description ->
                menusViewModel.createMenuModel.description.value = description
            })
    }


    if (updateDeleteDialogVisible.value) {
        MenuelyUpdateDeleteDialog(
            unitName = stringResource(R.string.menu),
            onUpdateCLick = {
                createUpdateDialogMode.value=Mode.EDIT
                createMenuDialogVisible.value=true
                updateDeleteDialogVisible.value=false
               menusViewModel.prepareForUpdate()
            },
            onDeleteClick = {
                updateDeleteDialogVisible.value = false
                deleteAlertDialogVisible.value = true
            },
            onDismiss = { updateDeleteDialogVisible.value = false }
        )
    }

    if (deleteAlertDialogVisible.value) {
        MenuelyDeleteAlertDialog(
            unitName = stringResource(id = R.string.menu),
            unitTitle = menusViewModel.selectedMenu.value.name.toString(),
            onDeleteClick = {
                deleteAlertDialogVisible.value = false
                menusViewModel.deleteMenu()
            },
            onDismiss = { deleteAlertDialogVisible.value = false }
        )
    }


}