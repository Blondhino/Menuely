package com.blondhino.menuely.ui.search_restaurant

import androidx.lifecycle.ViewModel
import com.blondhino.menuely.data.common.model.SearchModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(): ViewModel() {

     val searchModel : SearchModel = SearchModel()
}