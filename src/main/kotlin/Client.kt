package com.xmbsmdsj

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.xmbsmdsj.aop.Singleton
import com.xmbsmdsj.client.OneCallApi
import com.xmbsmdsj.interceptors.TokenAuthInterceptor
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.lang.RuntimeException

val BASE_URL = "https://api.openweathermap.org/data/2.5/"


class Client(token: String) {

    private var retrofit: Retrofit
    var interceptors: List<Interceptor> = listOf<Interceptor>(
        TokenAuthInterceptor(token)
    )

    init {
        val contentType = MediaType.parse("application/json")?:throw RuntimeException("Invalid MediaType")
        val okHttpClientBuilder = OkHttpClient.Builder()
        for (interceptor in interceptors) {
            okHttpClientBuilder.addInterceptor(interceptor)
        }
        retrofit = Retrofit.Builder()
            .client(okHttpClientBuilder.build())
            .addConverterFactory(Json{ignoreUnknownKeys=true}.asConverterFactory(contentType))
            .baseUrl(BASE_URL).build()
    }


    @Singleton
    fun oneCallApi(): OneCallApi = retrofit.create(OneCallApi::class.java)

}