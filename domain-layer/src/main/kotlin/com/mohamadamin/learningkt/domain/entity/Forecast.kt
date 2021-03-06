package com.mohamadamin.learningkt.domain.entity

/**
 * @author MohamadAmin Mohamadi (mohammadi.mohamadamin@gmail.com) on 2/11/17.
 */
data class ForecastList(val id: Long, val city: String, val country: String, val dailyForecast: List<Forecast>) {

    operator fun get(position: Int) : Forecast {
        return dailyForecast[position]
    }

    fun size(): Int = dailyForecast.size

}

data class Forecast(val id: Long, val date: Long, val description: String, val high: Int, val low: Int, val iconUrl: String)