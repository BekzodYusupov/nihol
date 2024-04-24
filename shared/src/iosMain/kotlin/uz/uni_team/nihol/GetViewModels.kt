package uz.uni_team.nihol

import org.koin.core.component.KoinComponent
import org.koin.core.component.get

object GetViewModels: KoinComponent {
    fun getMainViewModel() = get<MainViewModel>()
}