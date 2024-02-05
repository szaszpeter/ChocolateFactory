package com.codarchy.data.errorhandling

import retrofit2.HttpException
import java.io.IOException

suspend fun <T> safeApiCall(apiCall: suspend () -> T): ResultWrapper<T> {
    return try {
        ResultWrapper.Success(apiCall.invoke())
    } catch (throwable: Throwable) {
        when (throwable) {
            is IOException -> ResultWrapper.NetworkError
            is HttpException -> {
                val code = throwable.code()
                val errorResponse = ErrorResponse("HttpException")
                ResultWrapper.GenericError(code, errorResponse)
            }
            else -> {
                ResultWrapper.GenericError(null, null)
            }
        }
    }
}
