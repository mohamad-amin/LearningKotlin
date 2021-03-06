package com.mohamadamin.learningkt.database.entity

import java.util.*

/**
 * @author MohamadAmin Mohamadi (mohammadi.mohamadamin@gmail.com) on 2/21/17.
 */
class DayForecast(val map: MutableMap<String, Any?>) {

    var _id: Long by map
    var date: Long by map
    var description: String by map
    var high: Int by map
    var low: Int by map
    var iconUrl: String by map
    var cityId: Long by map

    constructor(date: Long, description: String, high: Int,
                low: Int, iconUrl: String, cityId: Long) : this(HashMap()) {

        this.date = date
        this.description = description
        this.high = high
        this.low = low
        this.iconUrl = iconUrl
        this.cityId = cityId

    }

}