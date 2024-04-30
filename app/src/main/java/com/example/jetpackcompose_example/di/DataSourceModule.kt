package com.example.jetpackcompose_example.di

import com.example.jetpackcompose_example.api.RemoteDataSource
import org.koin.dsl.module

val remoteDataSourceModule= module {
    factory {  RemoteDataSource(get()) }
}