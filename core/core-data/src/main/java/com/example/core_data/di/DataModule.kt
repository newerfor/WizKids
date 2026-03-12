package com.example.core_data.di

import com.example.core_data.local.converters.Converters
import com.example.core_data.local.dao.WizKidsDao
import com.example.core_data.local.database.DataBaseProvider
import com.example.core_data.local.database.WizKidsDatabase
import com.example.core_data.local.impl.DeleteDataRepositoryImpl
import com.example.core_data.local.impl.GetDataRepositoryImpl
import com.example.core_data.local.impl.SaveDataRepositoryImpl
import com.example.core_data.local.mapper.GetMapper
import com.example.core_data.local.mapper.SaveMapper
import com.example.core_domain.repository.DeleteDataRepository
import com.example.core_domain.repository.GetDataRepository
import com.example.core_domain.repository.SaveDataRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {
    single<DeleteDataRepository> { DeleteDataRepositoryImpl(get()) }
    single<GetDataRepository> { GetDataRepositoryImpl(get(), get()) }
    single<SaveDataRepository> { SaveDataRepositoryImpl(get(), get()) }
    single { DataBaseProvider.getDatabase(androidContext()) }
    single<WizKidsDao> { get<WizKidsDatabase>().wizKidsDao() }
    factory { GetMapper() }
    factory { SaveMapper() }
    factory { Converters() }
}