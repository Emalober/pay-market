package com.ar.maloba.paymarket.repository.remote.api

import retrofit2.Response

class ApiResponse<T> {

    var code: Int = 0
    var body: T? = null
    var errorMessage: String? = null

    constructor(throwableError: Throwable) {
        code = 500
        body = null
        errorMessage = throwableError.message
    }

    constructor(response: Response<T>) {
        code = response.code()
        if (response.isSuccessful) {
            body = response.body()
            errorMessage = null
        } else {
            body = null
            errorMessage = response.errorBody()?.string() ?: response.message()
        }
    }

    fun isSuccessful(): Boolean = code in 200..299
}