package com.mohamadamin.learningkotlin.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.mohamadamin.learningkotlin.R
import com.mohamadamin.learningkotlin.data.network.RequestForecastCommand
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {

    inline fun <T> with(t:T, body: T.() -> Unit) {
        t.body()
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val forecastList = find<RecyclerView>(R.id.forecast_list)
        forecastList.layoutManager = LinearLayoutManager(this)

        doAsync {
            val result = RequestForecastCommand("94043").execute()
            uiThread {
                forecastList.adapter = ForecastListAdapter(result) {
                    toast(it.date)
                }
            }
        }

    }

}

