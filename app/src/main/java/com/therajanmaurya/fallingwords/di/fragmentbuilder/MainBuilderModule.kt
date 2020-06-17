package com.therajanmaurya.fallingwords.di.fragmentbuilder

import com.therajanmaurya.fallingwords.ui.main.MainFragment
import com.therajanmaurya.fallingwords.ui.scoreview.ScoreViewFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class MainBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeMainFragment(): MainFragment

    @ContributesAndroidInjector
    abstract fun contributeScoreViewFragment(): ScoreViewFragment
}
