package com.therajanmaurya.fallingwords.di

import android.app.Application
import com.therajanmaurya.core.di.AppModule
import com.therajanmaurya.fallingwords.FallingWordApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        ActivityModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(fallingWordApp: FallingWordApp)
}
