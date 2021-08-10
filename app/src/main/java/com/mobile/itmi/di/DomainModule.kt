package com.mobile.itmi.di

import com.mobile.itmi.data.repository.AppRepository
import com.mobile.itmi.data.repository.AppRepositoryImpl
import com.mobile.itmi.domain.GetListMedicationUseCase
import com.mobile.itmi.domain.GetMedicationDetailUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { GetListMedicationUseCase(appRepository = get()) }
    single { GetMedicationDetailUseCase(appRepository = get()) }
}

val repositoryModule = module {
    single<AppRepository> { AppRepositoryImpl(appService = get()) }
}

val domainModule = listOf(repositoryModule, useCaseModule)