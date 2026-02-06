package com.example.wizkids.di

import com.example.wizkids.data.local.converters.Converters
import com.example.wizkids.data.local.dao.WizKidsDao
import com.example.wizkids.data.local.database.DataBaseProvider
import com.example.wizkids.data.local.database.WizKidsDatabase
import com.example.wizkids.data.local.impl.DeleteDataRepositoryImpl
import com.example.wizkids.data.local.impl.GetDataRepositoryImpl
import com.example.wizkids.data.local.impl.SaveDataRepositoryImpl
import com.example.wizkids.data.local.mapper.GetMapper
import com.example.wizkids.data.local.mapper.SaveMapper
import com.example.wizkids.domain.mapper.GetDomainMapper
import com.example.wizkids.domain.mapper.SaveDomainMapper
import com.example.wizkids.domain.repository.DeleteDataRepository
import com.example.wizkids.domain.repository.GetDataRepository
import com.example.wizkids.domain.repository.SaveDataRepository
import com.example.wizkids.domain.usecase.deleteUseCase.childUsecase.DeleteChildByIdUseCase
import com.example.wizkids.domain.usecase.deleteUseCase.userUsecase.DeleteUserUseCase
import com.example.wizkids.domain.usecase.deleteUseCase.visitUsecase.DeleteVisitByIdUseCase
import com.example.wizkids.domain.usecase.getUseCase.childUsecase.GetChildByIdUseCase
import com.example.wizkids.domain.usecase.getUseCase.childUsecase.GetChildrenUseCase
import com.example.wizkids.domain.usecase.getUseCase.userUsecase.GetUserUseCase
import com.example.wizkids.domain.usecase.getUseCase.visitUsecase.GetVisitByChildIdUseCase
import com.example.wizkids.domain.usecase.getUseCase.visitUsecase.GetVisitsUseCase
import com.example.wizkids.domain.usecase.saveUseCase.childUsecase.SaveChildUseCase
import com.example.wizkids.domain.usecase.saveUseCase.userUsecase.SaveUserUseCase
import com.example.wizkids.domain.usecase.saveUseCase.visitUsecase.SaveVisitUseCase
import com.example.wizkids.presentation.viewModel.child.ChildViewModel
import com.example.wizkids.presentation.viewModel.user.UserViewModel
import com.example.wizkids.presentation.viewModel.visit.VisitViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object AppModule {
    val module = module {
        single { DataBaseProvider.getDatabase(androidContext()) }
        single<WizKidsDao> { get<WizKidsDatabase>().wizKidsDao() }
        factory { GetMapper() }
        factory { SaveMapper() }
        factory { Converters() }
        factory { GetDomainMapper() }
        factory { SaveDomainMapper() }
        single<DeleteDataRepository> { DeleteDataRepositoryImpl(get()) }
        single<GetDataRepository> { GetDataRepositoryImpl(get(), get()) }
        single<SaveDataRepository> { SaveDataRepositoryImpl(get(), get()) }
        factory { GetChildByIdUseCase(get(), get()) }
        factory { GetChildrenUseCase(get(), get()) }
        factory { GetVisitsUseCase(get(), get()) }
        factory { GetUserUseCase(get(), get()) }
        factory { SaveChildUseCase(get(), get()) }
        factory { SaveVisitUseCase(get(), get()) }
        factory { SaveUserUseCase(get(), get()) }
        factory { GetVisitByChildIdUseCase(get(), get()) }
        factory { DeleteVisitByIdUseCase(get()) }
        factory { DeleteUserUseCase(get()) }
        factory { DeleteChildByIdUseCase(get()) }
        viewModel { ChildViewModel(get(), get(), get(), get()) }
        viewModel { VisitViewModel(get(), get(), get(), get()) }
        viewModel { UserViewModel(get(), get(), get()) }
    }
}