package com.example.jetpackcompose_example.api

import com.example.jetpackcompose_example.model.Receipes
import com.example.jetpackcompose_example.utils.Constants
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ApiService(private val httpClient: HttpClient) {

    val recipes="recipes/"
    suspend fun getReceipes(): Receipes = httpClient.get("${Constants.BASE_URL}$recipes").body<Receipes>()
    suspend fun getReceipeDetails(id:Int?): Receipes.Recipe = httpClient.get("${Constants.BASE_URL}$recipes$id").body<Receipes.Recipe>()
}