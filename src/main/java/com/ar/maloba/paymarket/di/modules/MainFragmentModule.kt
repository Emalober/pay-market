package com.ar.maloba.paymarket.di.modules

import com.ar.maloba.paymarket.ui.WelcomeFragment
import com.ar.maloba.paymarket.ui.payment.AmountFragment
import com.ar.maloba.paymarket.ui.payment.PaymentMethodsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeAmountFragment(): AmountFragment

    @ContributesAndroidInjector
    abstract fun contributeWelcomeFragment(): WelcomeFragment

    @ContributesAndroidInjector
    abstract fun contributePaymentMethodsFragment(): PaymentMethodsFragment

}