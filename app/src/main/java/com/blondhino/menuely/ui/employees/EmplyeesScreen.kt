package com.blondhino.menuely.ui.employees

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.blondhino.menuely.R
import com.blondhino.menuely.ui.components.*

@Composable
fun EmployeesScreen(navController: NavHostController, employeesViewModel: EmployeesViewModel){
    employeesViewModel.initialUserSearch()
    Column(modifier = Modifier.fillMaxSize()) {
        MenuelySearchBox(
            hintText = stringResource(R.string.search_for_users),
            value = employeesViewModel.searchModel.search.value,
            onValueChanged = { value ->
                employeesViewModel.searchModel.search.value = value
                employeesViewModel.searchUsers()
            })

        MenuelyJumpingProgressBar(isLoading = employeesViewModel.isLoading.value)

        MenuelyEmptyState(
            visible = employeesViewModel.emptyStateVisible.value,
            title = stringResource(id = R.string.oops),
            subtitle = stringResource(id = R.string.no_matches)
        )

        LazyColumn() {
            itemsIndexed(items = employeesViewModel.users.value) { index, item ->
                item.id?.let {id->
                    item.email?.let {email ->
                        MenuelySearchUserTicket(
                            id = id,
                            titleText = item.firstname+" "+item.lastname,
                            descText = email,
                            imageUrl = item.profileImage?.url.toString(),
                            onItemClick = {id->},
                            onInviteButtonClicked = {id->
                                employeesViewModel.sendJobInvitation(id)
                            }
                        )
                    }
                }
            }

        }

    }
}