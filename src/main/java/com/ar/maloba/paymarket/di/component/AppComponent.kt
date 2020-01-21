package com.ar.maloba.paymarket.di.component

import android.app.Application
import com.ar.maloba.paymarket.di.modules.ActivityModule
import com.ar.maloba.paymarket.di.modules.RetrofitModule
import com.ar.maloba.paymarket.di.modules.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    RetrofitModule::class,
    ViewModelModule::class,
    ActivityModule::class
])
interface AppComponent : AndroidInjector<DaggerApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}