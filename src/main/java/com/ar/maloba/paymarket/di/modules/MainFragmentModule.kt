package com.ar.maloba.paymarket.di.modules

import com.ar.maloba.paymarket.ui.WelcomeFragment
import com.ar.maloba.paymarket.ui.payment.AmountFragment
import com.ar.maloba.paymarket.ui.payment.BankFragment
import com.ar.maloba.paymarket.ui.payment.PaymentMethodsFragment
import com.ar.maloba.paymarket.ui.payment.SuccessFragment
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

    @ContributesAndroidInjector
    abstract fun contributeBankFragment(): BankFragment

    @ContributesAndroidInjector
    abstract fun contributeSuccessFragment(): SuccessFragment

}