package uz.uni_team.nihol.android.app

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import uz.uni_team.nihol.di.mainViewModelModule

class AndroidApplication:Application(){

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@AndroidApplication)
            modules(mainViewModelModule)
        }
    }
}