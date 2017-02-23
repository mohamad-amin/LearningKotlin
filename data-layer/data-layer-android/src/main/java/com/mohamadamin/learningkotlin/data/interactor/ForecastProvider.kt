package com.mohamadamin.learningkotlin.data.interactor

import android.content.Context
import android.text.format.DateUtils
import com.mohamadamin.learningkotlin.data.datasource.ForecastDataSource
import com.mohamadamin.learningkotlin.data.entity.Forecast
import com.mohamadamin.learningkotlin.data.entity.ForecastList
import com.mohamadamin.learningkotlin.data.network.ForecastServer
import com.mohamadamin.learningkotlin.database.ServerDataMapper
import com.mohamadamin.learningkotlin.database.sql.ForecastDb
import java.util.*

/**
 * @author MohamadAmin Mohamadi (mohammadi.mohamadamin@gmail.com) on 2/21/17.
 */
class ForecastProvider(context: Context, var sources: List<ForecastDataSource>) {

    inline fun <T, R : Any> Iterable<T>.firstResult(predicate: (T) -> R?): R {
        for (element in this) {
            val result = predicate(element)
            if (result != null) {
                return result
            }
        }
        throw NoSuchElementException("No element matching predicate was found.")
    }

    companion object {
        val DAY_IN_MILLS = 24 * 60 * 60 * 1000
    }

    init {
        if (sources.isEmpty()) {
            sources = listOf(ForecastDb(context), ForecastServer(ServerDataMapper(), context))
        }
    }

    private fun <T : Any> requestToSources(f: (ForecastDataSource) -> T?) : T =
            sources.firstResult{ f(it) }

    fun requestByZipCode(zipCode: Long, days: Int): ForecastList = requestToSources {
        val res = it.requestForecastByZipCode(zipCode, todayTimeSpan())
        if (res != null && res.size() >= days) res else null
    }

    fun requestForecast(id: Long) : Forecast = requestToSources {
        it.requestDayForecast(id)
    }

    private fun todayTimeSpan() = System.currentTimeMillis() / DateUtils.DAY_IN_MILLIS * DateUtils.DAY_IN_MILLIS

}