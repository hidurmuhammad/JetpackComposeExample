package com.example.jetpackcompose_example.repository

import android.content.Context
import com.example.jetpackcompose_example.api.RemoteDataSource
import com.example.jetpackcompose_example.model.Receipes
import kotlinx.coroutines.flow.Flow
import com.example.jetpackcompose_example.utils.UiState
import com.example.jetpackcompose_example.api.toResultFlow

class ReceipesRepository(private val remoteDataSource: RemoteDataSource) {

    suspend fun getReceipes(context: Context): Flow<UiState<Receipes?>> {
        return toResultFlow(context){
            remoteDataSource.getReceipes()
        }
    }

    suspend fun getReceipesDetail(context: Context,id:Int?): Flow<UiState<Receipes.Recipe?>> {
        return toResultFlow(context){
            remoteDataSource.getReceipesDetail(id)
        }
    }

}