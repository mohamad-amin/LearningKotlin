package com.mohamadamin.learningkt.data.interactor

import android.content.Context
import android.text.format.DateUtils
import com.mohamadamin.learningkt.data.ServerDataMapper
import com.mohamadamin.learningkt.data.network.ForecastServer
import com.mohamadamin.learningkt.database.sql.ForecastDb
import com.mohamadamin.learningkt.domain.datasource.ForecastDataSource
import com.mohamadamin.learningkt.domain.entity.Forecast
import com.mohamadamin.learningkt.domain.entity.ForecastList
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