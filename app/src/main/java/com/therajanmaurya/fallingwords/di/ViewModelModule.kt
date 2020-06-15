package com.therajanmaurya.fallingwords.di

import androidx.lifecycle.ViewModelProvider
import com.therajanmaurya.core.di.FallingWordViewModelFactory
import dagger.Binds
import dagger.Module

@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: FallingWordViewModelFactory): ViewModelProvider.Factory
}
