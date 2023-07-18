package com.anifox

import android.app.Application
import com.features.anime.data.di.animeDataModule
import com.features.anime.presentation.di.animeVmModule
import com.schema.di.apolloModule
import org.koin.android.BuildConfig.DEBUG
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(if (DEBUG) Level.DEBUG else Level.NONE)
            androidContext(this@MyApp)
            modules(
                apolloModule,
                animeDataModule,
                animeVmModule,
            )
        }
    }
}