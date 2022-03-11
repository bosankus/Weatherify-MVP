package com.androidplay.services.model.network

import com.androidplay.services.BuildConfig
import com.androidplay.services.model.model.Weather
import com.androidplay.services.utils.Constants.LOCATION
import com.androidplay.services.utils.Constants.WEATHER_URL
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Author: Ankush Bose
 * Company: Androidplay.in
 * Created on: 11,March,2022
 */
interface WeatherApiInterface {

    /*implementation method to get current temperature of hardcoded city*/
    @GET("weather")
    fun getWeather(
        @Query("q") location: String = LOCATION,
        @Query("APPID") AppId: String = BuildConfig.WEATHER_KEY
    ): Weather?

    companion object {

        private val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        operator fun invoke(): WeatherApiInterface {
            return Retrofit.Builder()
                .baseUrl(WEATHER_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
                .create(WeatherApiInterface::class.java)
        }
    }
}