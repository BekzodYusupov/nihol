package uz.uni_team.nihol

import dev.icerock.moko.mvvm.flow.cStateFlow
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uz.uni_team.nihol.extentions.resultOf

class MainViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(MainViewModelState())
    val uiState = _uiState.cStateFlow()

    private val client = HttpClient()

    init {
        onQrScan("")
    }

    fun onQrScan(qrUrl: String) {
        viewModelScope.launch {
            resultOf {
                client.get("https://ktor.io/docs/")
            }.onSuccess {response->
                _uiState.update { it.copy(qr = response.bodyAsText()) }
            }.onFailure {

            }
        }
    }

    fun incCount(){
        _uiState.update { it.copy(counter = it.counter+1) }
    }
    fun reduceCount(){
        _uiState.update { it.copy(counter = it.counter-1) }
    }
}

data class MainViewModelState(
    val qr: String? = null,
    val bookName: String? = null,
    val counter: Int = 0
)