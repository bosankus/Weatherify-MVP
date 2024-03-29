package com.androidplay.services.model.repository

import com.androidplay.services.model.model.Weather

/**
 * Author: Ankush Bose
 * Company: Androidplay.in
 * Created on: 11,March,2022
 */
interface WeatherRepository {

    suspend fun getWeather(areaName: String): Weather?
}