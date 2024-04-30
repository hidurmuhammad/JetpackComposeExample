package com.example.jetpackcompose_example.viemodel

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.example.jetpackcompose_example.model.Receipes
import com.example.jetpackcompose_example.repository.ReceipesRepository
import com.example.jetpackcompose_example.utils.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ReceipeViewModel(private val repository: ReceipesRepository, application: Application): BaseViewModel(application) {

    val _uiStateReceipeList = MutableStateFlow<UiState<Receipes?>>(UiState.Loading)
    val uiStateReceipeList: StateFlow<UiState<Receipes?>> = _uiStateReceipeList

    val _uiStateReceipeDetail = MutableStateFlow<UiState<Receipes.Recipe?>>(UiState.Loading)
    val uiStateReceipeDetail: StateFlow<UiState<Receipes.Recipe?>> = _uiStateReceipeDetail


    fun getReceipesList() = viewModelScope.launch {
        fetchData(_uiStateReceipeList) { repository.getReceipes(context) }
    }

    fun getReceipeDetail(id: Int?) = viewModelScope.launch {
        fetchData(_uiStateReceipeDetail,) { repository.getReceipesDetail(context, id) }
    }
}