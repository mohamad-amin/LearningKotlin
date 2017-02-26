package com.mohamadamin.learningkotlin.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import com.mohamadamin.learningkotlin.App
import com.mohamadamin.learningkotlin.R
import com.mohamadamin.learningkotlin.base.ToolbarManager
import com.mohamadamin.learningkotlin.detail.DetailActivity
import com.mohamadamin.learningkotlin.settings.SettingsActivity
import com.mohamadamin.learningkt.data.delegate.DelegatesExt
import com.mohamadamin.learningkt.data.interactor.ForecastProvider
import com.mohamadamin.learningkt.data.network.command.RequestForecastCommand
import org.jetbrains.anko.*

class MainActivity : AppCompatActivity(), ToolbarManager {

    val zipCode: Long by DelegatesExt.preference(this,
            SettingsActivity.ZIP_CODE.first, SettingsActivity.ZIP_CODE.second)
    override val toolbar: Toolbar by lazy { find<Toolbar>(R.id.toolbar) }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initToolbar(R.menu.main) {
            when (it) {
                R.id.action_settings -> startActivity<SettingsActivity>()
                else -> App.instance.toast("Unknown option")
            }
            true
        }

        loadForecast()

    }

    fun loadForecast() {

        val forecastProvider = ForecastProvider(this, listOf())
        val forecastList = find<RecyclerView>(R.id.forecast_list)
        forecastList.layoutManager = LinearLayoutManager(this)
        attachToScroll(forecastList)

        doAsync {
            val result = RequestForecastCommand(zipCode, forecastProvider).execute()
            uiThread {
                forecastList.adapter = ForecastListAdapter(result) {
                    startActivity<DetailActivity>(
                            DetailActivity.ID to it.id,
                            DetailActivity.CITY_NAME to result.city
                    )
                }
                toolbarTitle = "${result.city} (${result.country})"
            }
        }

    }

}

