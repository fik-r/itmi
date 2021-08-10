package com.mobile.itmi.di

import com.mobile.itmi.ui.order.OrderListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel {
        OrderListViewModel(
            getListMedicationUseCase = get()
        )
    }
}

val appModule = listOf(viewModelModule)