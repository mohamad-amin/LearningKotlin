package com.mohamadamin.learningkotlin.database

import com.mohamadamin.learningkotlin.data.entity.ForecastList
import com.mohamadamin.learningkotlin.data.entity.api.Forecast
import com.mohamadamin.learningkotlin.data.entity.api.ForecastResult
import com.mohamadamin.learningkotlin.data.entity.Forecast as ModelForecast

/**
 * @author MohamadAmin Mohamadi (mohammadi.mohamadamin@gmail.com) on 2/11/17.
 */
class ForecastDataMapper {

    fun convertFromDataModel(forecast: ForecastResult): ForecastList {
        return ForecastList(-1, forecast.city.name, forecast.city.country,
                convertForecastListToDomain(forecast.list))
    }

    private fun convertForecastListToDomain(list: List<Forecast>): List<ModelForecast> {
        return list.map { convertForecastItemToDomain(it) }
    }

    private fun convertForecastItemToDomain(forecast: Forecast): ModelForecast {
        return ModelForecast(forecast.dt,
                forecast.weather[0].description, forecast.temp.max.toInt(),
        forecast.temp.min.toInt(), generateIconUrl(forecast.weather[0].icon))
    }

    fun generateIconUrl(iconCode: String): String = "http://openweathermap.org/img/w/$iconCode.png"

//    Todo: WTF
//    private fun convertDate(date: Long): String {
//        val df = DateFormat.getDateInstance(DateFormat.MEDIUM,
//                Locale.getDefault())
//        return df.format(date * 1000)
//    }

}