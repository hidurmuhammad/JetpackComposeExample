package com.example.jetpackcompose_example.api

class RemoteDataSource(private val apiService: ApiService) {
    suspend fun getReceipes() = apiService.getReceipes()
    suspend fun getReceipesDetail(id:Int?) = apiService.getReceipeDetails(id)
}