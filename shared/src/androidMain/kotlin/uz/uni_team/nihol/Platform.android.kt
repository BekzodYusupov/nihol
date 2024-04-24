package uz.uni_team.nihol

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.okhttp.OkHttp
import java.security.SecureRandom
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext

actual fun httpClient(config: HttpClientConfig<*>.() -> Unit) = HttpClient(OkHttp) {
    config(this)

    engine {
        config {
            retryOnConnectionFailure(true)
            connectTimeout(30, TimeUnit.SECONDS)

            val trustAllCert = AllCertsTrustManager()
            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, arrayOf(trustAllCert), SecureRandom())
            sslSocketFactory(sslContext.socketFactory, trustAllCert)
        }
    }
}