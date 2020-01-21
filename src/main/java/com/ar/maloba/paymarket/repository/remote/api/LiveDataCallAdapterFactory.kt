package com.ar.maloba.paymarket.repository.remote.api

import androidx.lifecycle.LiveData
import com.ar.maloba.paymarket.utils.RESOURCE_MUST_BE_PARAMETERIZED
import com.ar.maloba.paymarket.utils.TYPE_MUST_BE_A_RESOURCE
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class LiveDataCallAdapterFactory : CallAdapter.Factory() {
    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        if (CallAdapter.Factory.getRawType(returnType) != LiveData::class.java) {
            return null
        }
        val observableType = CallAdapter.Factory.getParameterUpperBound(0, returnType as ParameterizedType)
        val rawObservableType = CallAdapter.Factory.getRawType(observableType)
        if (rawObservableType != ApiResponse::class.java) {
            throw IllegalArgumentException(TYPE_MUST_BE_A_RESOURCE)
        }
        if (observableType !is ParameterizedType) {
            throw IllegalArgumentException(RESOURCE_MUST_BE_PARAMETERIZED)
        }
        val bodyType = CallAdapter.Factory.getParameterUpperBound(0, observableType)
        return LiveDataCallAdapter<Any>(bodyType)
    }
}