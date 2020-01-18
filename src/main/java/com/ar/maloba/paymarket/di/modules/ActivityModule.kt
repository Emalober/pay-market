package com.ar.maloba.paymarket.di.modules

import com.ar.maloba.paymarket.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(
        modules = [ MainFragmentModule::class ]
    )
    abstract fun contributeMainActivity(): MainActivity
}