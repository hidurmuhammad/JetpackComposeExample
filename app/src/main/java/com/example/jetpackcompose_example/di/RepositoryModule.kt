package com.example.jetpackcompose_example.di

import com.example.jetpackcompose_example.repository.ReceipesRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory {  ReceipesRepository(get()) }
}