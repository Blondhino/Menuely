package com.blondhino.menuely.ui.home

import androidx.lifecycle.ViewModel
import com.blondhino.menuely.data.database.dao.RestaurantDao
import com.blondhino.menuely.data.database.dao.UserDao
import com.blondhino.menuely.data.repo.OnBoardingRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repo: OnBoardingRepo,
    private val userDao: UserDao,
    private val restaurantDao: RestaurantDao
): ViewModel(){

}