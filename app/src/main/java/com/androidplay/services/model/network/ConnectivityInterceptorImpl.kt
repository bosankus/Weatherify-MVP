package com.androidplay.services.model.network

import android.content.Context
import com.androidplay.services.utils.NetworkInfo.isOnline
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * Author: Ankush Bose
 * Company: Androidplay.in
 * Created on: 11,March,2022
 */
class ConnectivityInterceptorImpl(context: Context) : ConnectivityInterceptor {

    private val appContext = context.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isOnline(appContext)) throw IOException()
        return chain.proceed(chain.request())
    }
}