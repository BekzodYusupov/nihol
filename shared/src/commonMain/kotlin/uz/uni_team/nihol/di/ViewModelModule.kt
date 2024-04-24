package uz.uni_team.nihol.di

import org.koin.dsl.module
import uz.uni_team.nihol.MainViewModel

val mainViewModelModule = module {
    factory {
        MainViewModel()
    }
}