package com.xmbsmdsj.client

import com.xmbsmdsj.pojo.OneCallResponse
import jdk.nashorn.internal.codegen.CompilerConstants
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

val BASE_URL = "https://api.openweathermap.org/data/2.5"

interface OneCallApi{

    @GET("onecall")
    fun currentWeather(@Query("lat") lat: Double, @Query("lon") lon: Double): Call<OneCallResponse>

}
