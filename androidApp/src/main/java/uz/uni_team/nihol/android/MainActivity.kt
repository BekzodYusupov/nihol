package uz.uni_team.nihol.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import uz.uni_team.nihol.MainViewModel
import uz.uni_team.nihol.firebase.getUsers

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Firebase.initialize(this)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomeScreen()
                }
            }
        }
    }
}

@Composable
fun HomeScreen(
    viewModel: MainViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = uiState.qr ?: "nil")
        Text(text = uiState.counter.toString())
        Button(onClick = { viewModel.incCount() }) {
            Text(text = "+1")
        }
        Button(onClick = { viewModel.reduceCount() }) {
            Text(text = "-1")
        }

        Button(onClick = {
            scope.launch { getUsers() }
        }) {
            Text(text = "Firebase get id")
        }
    }
}


