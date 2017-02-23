package com.mohamadamin.learningkt.database.mapper

import com.mohamadamin.learningkt.database.entity.CityForecast
import com.mohamadamin.learningkt.database.entity.DayForecast
import com.mohamadamin.learningkt.domain.entity.Forecast
import com.mohamadamin.learningkt.domain.entity.ForecastList
import com.mohamadamin.learningkt.domain.entity.api.Forecast as ModelForecast

/**
 * @author MohamadAmin Mohamadi (mohammadi.mohamadamin@gmail.com) on 2/21/17.
 */
class DbDataMapper {

    fun convertToDomain(forecast: CityForecast) = with(forecast) {
        val daily = dailyForecast.map { convertDayToDomain(it) }
        ForecastList(_id, city, country, daily)
    }

    fun convertDayToDomain(dayForecast: DayForecast) = with(dayForecast) {
        Forecast(_id, date, description, high, low, iconUrl)
    }

    fun convertFromDomain(forecast: ForecastList) = with(forecast) {
        val daily = dailyForecast.map { convertDayFromDomain(id, it) }
        CityForecast(id, city, country, daily)
    }

    private fun convertDayFromDomain(cityId: Long, forecast: Forecast) = with(forecast) {
        DayForecast(date, description, high, low, iconUrl, cityId)
    }

}