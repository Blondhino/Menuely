package com.blondhino.menuely.ui

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
import com.blondhino.menuely.ui.components.MenuelyCreateMenuDialog
import com.blondhino.menuely.ui.components.MenuelyMenuTicket
import com.blondhino.menuely.ui.menus.MenusViewModel
import com.blondhino.menuely.ui.ui.theme.greenDark

@Composable
fun MenusScreen(navController: NavHostController, menusViewModel: MenusViewModel) {
    val createMenuDialogVisible = remember { mutableStateOf(false) }
    val context: Context = LocalContext.current
    menusViewModel.fetchMenus()

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

            LazyColumn() {
                itemsIndexed(items = menusViewModel.menus.value) { index, item ->
                    item.id?.let { id ->
                        item.name?.let { title ->
                            item.description?.let {
                                description->
                                MenuelyMenuTicket(
                                    onItemClick = {},
                                    id = id,
                                    titleText = title,
                                    descText = description
                                )
                            }
                        }
                    }
                }
            }


        }

        FloatingActionButton(
            onClick = {
                createMenuDialogVisible.value = true
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

    if (createMenuDialogVisible.value) {
        MenuelyCreateMenuDialog(
            { createMenuDialogVisible.value = false },
            onSave = {
                if (menusViewModel.createMenuModel.validate()) {
                    createMenuDialogVisible.value = false
                } else {
                    Toast.makeText(
                        context,
                        menusViewModel.createMenuModel.errorMessage.value,
                        Toast.LENGTH_LONG
                    ).show()
                }
            },
            menuNameValue = menusViewModel.createMenuModel.name.value,
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


}