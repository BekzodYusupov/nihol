package uz.uni_team.nihol

import org.koin.core.context.startKoin
import uz.uni_team.nihol.di.mainViewModelModule

fun initKoin(){
    startKoin {
        modules(mainViewModelModule)
    }
}