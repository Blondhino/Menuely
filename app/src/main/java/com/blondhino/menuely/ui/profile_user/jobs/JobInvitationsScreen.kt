package com.blondhino.menuely.ui.profile_user.jobs

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.blondhino.menuely.ui.employees.EmployeesViewModel

@Composable
fun JobInvitationsScreen(navController: NavHostController, employeesViewModel: EmployeesViewModel) {
employeesViewModel.getJobInvitations()
}
