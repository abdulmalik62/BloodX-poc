package com.inhlth.bloodx.config

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import org.springframework.stereotype.Component

@Component
class ApiClient(private val okHttpClient: OkHttpClient) {

    fun callApi(url: String, token: String): Response {
        val mediaType = "text/plain".toMediaTypeOrNull()
        val body = RequestBody.create(mediaType, "")
        val request = Request.Builder()
            .url(url)
            .get()
            .addHeader("Accept", "application/json")
            .addHeader("Authorization", "Bearer $token")
            .build()

        return okHttpClient.newCall(request).execute()
    }
}
