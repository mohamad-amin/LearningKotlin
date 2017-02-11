package com.mohamadamin.learningkotlin.data.entity

/**
 * @author MohamadAmin Mohamadi (mohammadi.mohamadamin@gmail.com) on 2/11/17.
 */
data class ForecastList(val city: String, val country: String, val dailyForecast:List<Forecast>)

data class Forecast(val date: String, val description: String, val high: Int, val low: Int)