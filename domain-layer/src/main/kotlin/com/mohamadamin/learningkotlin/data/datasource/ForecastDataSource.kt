package com.mohamadamin.learningkotlin.data.datasource

import com.mohamadamin.learningkotlin.data.entity.Forecast
import com.mohamadamin.learningkotlin.data.entity.ForecastList

/**
 * @author MohamadAmin Mohamadi (mohammadi.mohamadamin@gmail.com) on 2/21/17.
 */
interface ForecastDataSource {

    fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList?
    fun requestDayForecast(id: Long) : Forecast?

}