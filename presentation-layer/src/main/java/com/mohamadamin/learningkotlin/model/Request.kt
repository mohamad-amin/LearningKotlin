package com.mohamadamin.learningkotlin.model

import android.util.Log
import java.net.URL

/**
 * @author MohamadAmin Mohamadi (mohammadi.mohamadamin@gmail.com) on 2/11/17.
 */
class Request(val url: String) {

    fun run() {
        val forecastJsonStr = URL(url).readText()
        Log.d(javaClass.simpleName, "Json data from $url: \n $forecastJsonStr")
    }

}