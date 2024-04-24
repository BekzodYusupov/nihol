package uz.uni_team.nihol.network

import io.ktor.client.*
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.plugins.*
import io.ktor.http.isSuccess

internal fun HttpClientConfig<*>.httpTimeOutConfig(){
    install(HttpTimeout) {
        requestTimeoutMillis = 30000
        connectTimeoutMillis = 30000
        socketTimeoutMillis = 30000
    }
}