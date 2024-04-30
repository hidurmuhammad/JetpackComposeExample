package com.example.jetpackcompose_example.di

import com.example.jetpackcompose_example.viemodel.ReceipeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule= module {
    viewModel{ ReceipeViewModel(get(),get()) }
}