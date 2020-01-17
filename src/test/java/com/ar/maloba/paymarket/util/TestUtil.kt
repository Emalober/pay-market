package com.ar.maloba.paymarket.util

import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import okio.buffer
import okio.source
import java.io.IOException
import java.nio.charset.StandardCharsets

object TestUtil {


    fun getJsonFromString(jsonBodyString: String): JsonObject {
        return Gson().fromJson(jsonBodyString, JsonObject::class.java)
    }

    fun getJsonArrayFromString(jsonBodyString: String): JsonArray {
        return Gson().fromJson(jsonBodyString, JsonArray::class.java)
    }


    @Throws(IOException::class)
    fun initJsonArray(fileName: String): JsonArray {
        val inputStream = javaClass.classLoader?.getResourceAsStream("mock_responses/$fileName")
        inputStream.let {
            val source = inputStream!!.source().buffer()
            val jsonBodyString = source.readString(StandardCharsets.UTF_8)
            return getJsonArrayFromString(jsonBodyString)
        }
    }
}