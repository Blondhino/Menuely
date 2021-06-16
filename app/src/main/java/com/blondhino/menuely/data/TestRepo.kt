package com.blondhino.menuely.data

import android.util.Log
import javax.inject.Inject

class TestRepo @Inject constructor() {

    fun repoFunTest(){
        Log.d("diTest","repoFunCalled")
    }
}