package com.mohamadamin.learningkt.data.network

import android.content.Context
import com.mohamadamin.learningkt.data.ServerDataMapper
import com.mohamadamin.learningkt.database.sql.ForecastDb
import com.mohamadamin.learningkt.domain.datasource.ForecastDataSource
import com.mohamadamin.learningkt.domain.entity.Forecast
import com.mohamadamin.learningkt.domain.entity.ForecastList

/**
 * @author MohamadAmin Mohamadi (mohammadi.mohamadamin@gmail.com) on 2/21/17.
 */
class ForecastServer(val dataMapper: ServerDataMapper = ServerDataMapper(), context: Context) : ForecastDataSource {

    var forecastDb: ForecastDb? = null

    init {
        forecastDb = ForecastDb(context)
    }

    override fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList? {
        val result = ForecastByZipCodeRequest(zipCode).execute()
        val converted = dataMapper.convertToDomain(zipCode, result)
        forecastDb!!.saveForecast(converted)
        return forecastDb!!.requestForecastByZipCode(zipCode, date)
    }

    override fun requestDayForecast(id: Long): Forecast? =
            throw UnsupportedOperationException("Should be provided via the local database")

}