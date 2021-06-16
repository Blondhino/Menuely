package com.blondhino.menuely.ui

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.blondhino.menuely.data.TestRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TestViewModel @Inject constructor(
    private val testRepo: TestRepo
): ViewModel() {

    fun getDataFromRepo(){
        Log.d("diTest","viewModel function called");
        testRepo.repoFunTest()
    }
}