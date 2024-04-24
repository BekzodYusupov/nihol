package uz.uni_team.nihol.network

import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.defaultRequest
import uz.uni_team.nihol.httpClient

fun httpClient() =
   httpClient {
        expectSuccess = true
        logging()
        contentNegotiation()
//        defaultRequest(preferenceManager)
        httpTimeOutConfig()
       install(DefaultRequest){
       }
    }

