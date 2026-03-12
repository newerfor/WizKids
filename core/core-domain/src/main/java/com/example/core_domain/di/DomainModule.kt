package com.example.core_domain.di

import com.example.core_domain.repository.DeleteDataRepository
import com.example.core_domain.repository.GetDataRepository
import com.example.core_domain.repository.SaveDataRepository
import com.example.core_domain.usecase.deleteUseCase.childUsecase.DeleteChildByIdUseCase
import com.example.core_domain.usecase.deleteUseCase.userUsecase.DeleteUserUseCase
import com.example.core_domain.usecase.deleteUseCase.visitUsecase.DeleteVisitByIdUseCase
import com.example.core_domain.usecase.getUseCase.childUsecase.GetChildByIdUseCase
import com.example.core_domain.usecase.getUseCase.childUsecase.GetChildrenUseCase
import com.example.core_domain.usecase.getUseCase.userUsecase.GetUserUseCase
import com.example.core_domain.usecase.getUseCase.visitUsecase.GetVisitByChildIdUseCase
import com.example.core_domain.usecase.getUseCase.visitUsecase.GetVisitsUseCase
import com.example.core_domain.usecase.saveUseCase.childUsecase.SaveChildUseCase
import com.example.core_domain.usecase.saveUseCase.userUsecase.SaveUserUseCase
import com.example.core_domain.usecase.saveUseCase.visitUsecase.SaveVisitUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { GetChildByIdUseCase(get()) }
    factory { GetChildrenUseCase(get()) }
    factory { GetVisitsUseCase(get()) }
    factory { GetUserUseCase(get()) }
    factory { SaveChildUseCase(get()) }
    factory { SaveVisitUseCase(get()) }
    factory { SaveUserUseCase(get()) }
    factory { GetVisitByChildIdUseCase(get()) }
    factory { DeleteVisitByIdUseCase(get()) }
    factory { DeleteUserUseCase(get()) }
    factory { DeleteChildByIdUseCase(get()) }
}