package com.rakshitjain.presentation

import android.app.Application
import com.rakshitjain.presentation.di.*
import org.koin.android.ext.android.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        loadKoin()
    }

    private fun loadKoin() {
        startKoin(this,
                listOf(mNetworkModules,
                        mViewModels,
                        mRepositoryModules,
                        mUseCaseModules,
                        mLocalModules)

        )
    }
}