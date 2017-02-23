package com.mohamadamin.learningkt.database.entity

import java.util.*

/**
 * @author MohamadAmin Mohamadi (mohammadi.mohamadamin@gmail.com) on 2/20/17.
 */
class CityForecast(val map: MutableMap<String, Any?>, val dailyForecast: List<DayForecast>) {

    var _id: Long by map
    var city: String by map
    var country: String by map

    constructor(id: Long, city: String, country: String,
                dailyForecast: List<DayForecast>) : this(HashMap(), dailyForecast) {

        this._id = id
        this.city = city
        this.country = country

    }

}