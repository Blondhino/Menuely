package com.blondhino.menuely.ui.employees

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blondhino.menuely.data.common.model.RestaurantModel
import com.blondhino.menuely.data.common.model.SearchModel
import com.blondhino.menuely.data.common.model.UserModel
import com.blondhino.menuely.data.repo.EmployeesRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmployeesViewModel @Inject constructor(private val repo: EmployeesRepo) : ViewModel() {
    val searchModel: SearchModel = SearchModel()
    val isLoading = mutableStateOf(false)
    val emptyStateVisible = mutableStateOf(false)
    val users: MutableState<List<UserModel>> = mutableStateOf(ArrayList())
    private val initialSearchDone = mutableStateOf(false)
    private var searchUsersJob: Job? = null


    fun search(){
        searchUsersJob?.cancel()
        searchUsersJob = viewModelScope.launch {
            isLoading.value=true
            delay(500)
            val response = repo.searchUsers(searchModel.search.value)
            response.data?.let {
                users.value=it
                emptyStateVisible.value = it.size==0
            }
            isLoading.value=false
        }
    }

    fun initialSearch() {
        if (!initialSearchDone.value) {
            isLoading.value=true
            viewModelScope.launch {
                val response = repo.searchUsers("")
                response.data?.let {
                    users.value=it
                    isLoading.value=false
                }
            }
            initialSearchDone.value = true
        }
    }

    fun sendJobInvitation(employeeId: Int)= viewModelScope.launch {
        val response = repo.createJobInvitation(employeeId)
    }

}

