package com.example.manymanymanycats.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiFactory {
    private val baseUrl = "https://api.thecatapi.com/"

    class ApiKeyInterceptor() : Interceptor {
        private val apiKey: String = "2d63512c-1c5f-496b-8250-71b91514da66"
        override fun intercept(chain: Interceptor.Chain): Response {
            var original = chain.request()
            original = original.newBuilder().addHeader("x-api-key", apiKey).build()
            return chain.proceed(original)
        }
    }

    fun getRetrofit(): Retrofit {
        val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor()
        interceptor.apply { interceptor.level = HttpLoggingInterceptor.Level.BODY }
        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(ApiKeyInterceptor())
            .build()

        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(baseUrl)
            .client(client)
            .build()
    }

    fun getApiService(): ApiService {
        return getRetrofit().create(ApiService::class.java)
    }
}