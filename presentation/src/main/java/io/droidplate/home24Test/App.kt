package io.droidplate.home24Test

import android.app.Application
import io.droidplate.home24Test.di.AppModule
import io.droidplate.home24Test.di.DaggerInjector
import io.droidplate.home24Test.di.Injector
import io.droidplate.home24test.BuildConfig
import timber.log.Timber

class App : Application() {

    lateinit var injector: Injector private set

    override fun onCreate() {
        super.onCreate()

        initDagger()

        Timber.plant(Timber.DebugTree())

    }

    /**
     * Initial Dagger Instance in the application class
     * Making this available anywhere in the app
     */
    private fun initDagger() {
        injector = DaggerInjector
                .builder()
                .appModule(AppModule(this))
                .build()
    }
}