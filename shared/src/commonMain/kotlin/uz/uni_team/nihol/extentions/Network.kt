package uz.uni_team.nihol.extentions

import kotlinx.coroutines.CancellationException
import kotlinx.serialization.json.Json

inline fun <T, R> T.resultOf(block: T.() -> R): Result<R> {
    return try {
        Result.success(block())
    } catch (e: CancellationException) {
        throw e
    }
    catch (e: Exception) {
        Result.failure(e)
    }
}

inline fun <reified T> String.jsonToObject(): T {
    val json = Json {
        prettyPrint = true; ignoreUnknownKeys = true; isLenient = true
    }
    return try {
        json.decodeFromString(this)
    } catch (e: Exception) {
        json.decodeFromString("{}")
    }
}