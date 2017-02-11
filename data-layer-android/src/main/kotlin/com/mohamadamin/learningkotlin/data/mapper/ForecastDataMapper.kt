package com.mohamadamin.learningkotlin.data.mapper

import com.mohamadamin.learningkotlin.data.entity.ForecastList
import com.mohamadamin.learningkotlin.data.entity.api.Forecast
import com.mohamadamin.learningkotlin.data.entity.api.ForecastResult
import java.text.DateFormat
import java.util.*
import com.mohamadamin.learningkotlin.data.entity.Forecast as ModelForecast

/**
 * @author MohamadAmin Mohamadi (mohammadi.mohamadamin@gmail.com) on 2/11/17.
 */
class ForecastDataMapper {

    fun convertFromDataModel(forecast: ForecastResult): ForecastList {
        return ForecastList(forecast.city.name, forecast.city.country,
                convertForecastListToDomain(forecast.list))
    }

    private fun convertForecastListToDomain(list: List<Forecast>): List<ModelForecast> {
        return list.map { convertForecastItemToDomain(it) }
    }

    private fun convertForecastItemToDomain(forecast: Forecast): ModelForecast {
        return ModelForecast(convertDate(forecast.dt),
                forecast.weather[0].description, forecast.temp.max.toInt(),
        forecast.temp.min.toInt())
    }

    private fun convertDate(date: Long): String {
        val df = DateFormat.getDateInstance(DateFormat.MEDIUM,
                Locale.getDefault())
        return df.format(date * 1000)
    }

}