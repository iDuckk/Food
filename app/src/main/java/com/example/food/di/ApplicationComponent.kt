package com.example.food.di

import android.app.Application
import com.example.food.presentation.mainFragment.MainFragment
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [DataModule::class])
interface ApplicationComponent {

    fun inject(fragment: MainFragment)

    @Component.Factory
    interface Factory{

        fun  create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}