package com.xmbsmdsj.interceptors

import okhttp3.Interceptor
import okhttp3.Response

class TokenAuthInterceptor(var token: String): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val newReq = chain.request().newBuilder().url(
            chain.request().url().newBuilder().addQueryParameter("appid", token).build()
        ).build()
        return chain.proceed(newReq)
    }

}