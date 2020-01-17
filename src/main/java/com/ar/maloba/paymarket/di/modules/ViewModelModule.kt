package com.ar.maloba.paymarket.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ar.maloba.paymarket.di.annotations.ViewModelKey
import com.ar.maloba.paymarket.ui.PaymentMethodsViewModel
import com.ar.maloba.paymarket.utils.SimpleViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(PaymentMethodsViewModel::class)
    abstract fun bindPaymentMethodsViewModel(userViewModel: PaymentMethodsViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(simpleViewModelFactory: SimpleViewModelFactory):
            ViewModelProvider.Factory
}