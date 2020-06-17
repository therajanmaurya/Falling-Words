package com.therajanmaurya.fallingwords.di.fragmentbuilder

import com.therajanmaurya.fallingwords.ui.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class MainBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeMainFragment(): MainFragment
}
