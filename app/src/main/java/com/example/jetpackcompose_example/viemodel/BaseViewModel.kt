package com.example.jetpackcompose_example.viemodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.jetpackcompose_example.utils.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

open class BaseViewModel(application: Application) : AndroidViewModel(application) {
    protected val context
        get() = getApplication<Application>()

    suspend fun <T> fetchData(uiStateFlow: MutableStateFlow<UiState<T?>>, apiCall: suspend () -> Flow<UiState<T?>>) {
        uiStateFlow.value = UiState.Loading
        try {
            apiCall().collect {
                uiStateFlow.value = it
            }
        } catch (e: Exception) {
            uiStateFlow.value = UiState.Error(e.message?:"")
        }
    }
}