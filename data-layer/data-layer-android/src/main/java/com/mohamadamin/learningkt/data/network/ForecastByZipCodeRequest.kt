package com.mohamadamin.learningkt.data.network

import android.util.Log
import com.google.gson.Gson
import com.mohamadamin.learningkt.domain.entity.api.ForecastResult
import java.net.URL

/**
 * @author MohamadAmin Mohamadi (mohammadi.mohamadamin@gmail.com) on 2/11/17.
 */
class ForecastByZipCodeRequest(val zipCode: Long, val gson: Gson = Gson()) {

    companion object {
        private val APP_ID = "c3ab72e244bf04edf2855bbb88001901"
        private val URL = "http://api.openweathermap.org/data/2.5/forecast/daily?mode=json&units=metric&cnt=7"
        private val COMPLETE_URL = "$URL&APPID=$APP_ID&q="
    }

    fun execute(): ForecastResult {
        Log.d(javaClass.simpleName, "Before fucking request")
        val forecastJsonStr = URL(COMPLETE_URL + zipCode).readText()
        Log.d(javaClass.simpleName, "After fucking request")
        return gson.fromJson(forecastJsonStr, ForecastResult::class.java)
    }

}