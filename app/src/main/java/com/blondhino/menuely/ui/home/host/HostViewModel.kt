package com.blondhino.menuely.ui.home.host

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HostViewModel @Inject constructor(): ViewModel() {
    val isDrawerOpen : MutableLiveData<Boolean> = MutableLiveData()
}