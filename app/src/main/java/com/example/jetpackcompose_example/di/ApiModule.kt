package com.example.jetpackcompose_example.di

import com.example.jetpackcompose_example.api.ApiService
import org.koin.dsl.module

val apiServiceModule= module {
    factory { ApiService(get()) }
}