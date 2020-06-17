package com.therajanmaurya.fallingwords.di

import com.therajanmaurya.fallingwords.di.fragmentbuilder.MainBuilderModule
import com.therajanmaurya.fallingwords.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [MainBuilderModule::class])
    abstract fun contributeMainActivity(): MainActivity
}
