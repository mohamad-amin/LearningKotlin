package com.mohamadamin.learningkotlin.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.mohamadamin.learningkotlin.R
import com.mohamadamin.learningkotlin.detail.DetailActivity
import com.mohamadamin.learningkt.data.interactor.ForecastProvider
import com.mohamadamin.learningkt.data.network.command.RequestForecastCommand
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {

    var forecastProvider: ForecastProvider? = null
    val zipCode = 94043L

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        forecastProvider = ForecastProvider(this, listOf())

        val forecastList = find<RecyclerView>(R.id.forecast_list)
        forecastList.layoutManager = LinearLayoutManager(this)

        doAsync {
            val result = RequestForecastCommand(zipCode, forecastProvider!!).execute()
            uiThread {
                forecastList.adapter = ForecastListAdapter(result) {
                    startActivity<DetailActivity>(
                            DetailActivity.ID to result.id,
                            DetailActivity.CITY_NAME to result.city
                    )
                }
            }
        }

    }

}

