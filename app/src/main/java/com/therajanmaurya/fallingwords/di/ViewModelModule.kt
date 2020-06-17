package com.therajanmaurya.fallingwords.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.therajanmaurya.core.di.FallingWordViewModelFactory
import com.therajanmaurya.core.di.ViewModelKey
import com.therajanmaurya.fallingwords.ui.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: FallingWordViewModelFactory): ViewModelProvider.Factory
}
