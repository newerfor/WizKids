package com.example.core_viewmodel.di

import com.example.core_viewmodel.child.ChildViewModel
import com.example.core_viewmodel.user.UserViewModel
import com.example.core_viewmodel.visit.VisitViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ChildViewModel(get(), get(), get(), get()) }
    viewModel { VisitViewModel(get(), get(), get(), get()) }
    viewModel { UserViewModel(get(), get(), get()) }
}