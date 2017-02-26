package com.mohamadamin.learningkotlin.detail

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.TextView
import com.bumptech.glide.Glide
import com.mohamadamin.learningkotlin.App
import com.mohamadamin.learningkotlin.R
import com.mohamadamin.learningkotlin.base.ToolbarManager
import com.mohamadamin.learningkotlin.settings.SettingsActivity
import com.mohamadamin.learningkt.data.interactor.ForecastProvider
import com.mohamadamin.learningkt.data.network.command.RequestDayForecastCommand
import com.mohamadamin.learningkt.domain.entity.Forecast
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.*
import java.text.DateFormat
import java.util.*

class DetailActivity : AppCompatActivity(), ToolbarManager {

    var TextView.textColor: Int
        get() = currentTextColor
        set(value) = setTextColor(value)

    companion object {
        val ID = "DetailActivity:id"
        val CITY_NAME = "DetailActivity:cityName"
    }

    var forecastProvider: ForecastProvider? = null
    override val toolbar: Toolbar by lazy { find<Toolbar>(R.id.toolbar) }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        initToolbar(R.menu.main) {
            when (it) {
                R.id.action_settings -> startActivity<SettingsActivity>()
                else -> App.instance.toast("Unknown option")
            }
            true
        }
        enableHomeAsUp { onBackPressed() }

        forecastProvider = ForecastProvider(this, listOf())
        toolbarTitle = intent.getStringExtra(CITY_NAME)

        doAsync {
            val forecastData = RequestDayForecastCommand(intent.getLongExtra(ID, -1), forecastProvider!!).execute()
            uiThread {
                bindForecast(forecastData)
            }
        }

    }

    private fun bindForecast(forecast: Forecast)  = with(forecast) {

        Glide.with(this@DetailActivity)
                .load(iconUrl)
                .into(detail_icon)

        supportActionBar?.subtitle = date.toDateString(DateFormat.FULL)
        detail_weatherdesc.text = description
        bindWeather(high to detail_maxtemp, low to detail_mintemp)

    }

    private fun Long.toDateString(dateFormat: Int = DateFormat.MEDIUM): String {
        return DateFormat.getDateInstance(dateFormat, Locale.getDefault()).format(this)
    }

    private fun bindWeather(vararg views: Pair<Int, TextView>) = views.forEach {
        it.second.text = it.first.toString()
        it.second.textColor = ContextCompat.getColor(this, when (it.first) {
            in -50..0 -> android.R.color.holo_red_dark
            in 0..15 -> android.R.color.holo_orange_dark
            else -> android.R.color.holo_green_dark
        })
    }

}
