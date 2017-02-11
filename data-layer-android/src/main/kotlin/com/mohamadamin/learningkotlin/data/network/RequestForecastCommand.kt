package com.mohamadamin.learningkotlin.data.network

import com.mohamadamin.learningkotlin.data.entity.ForecastList
import com.mohamadamin.learningkotlin.data.executor.Command
import com.mohamadamin.learningkotlin.data.mapper.ForecastDataMapper

/**
 * @author MohamadAmin Mohamadi (mohammadi.mohamadamin@gmail.com) on 2/11/17.
 */
class RequestForecastCommand(val zipCode: String): Command<ForecastList> {

    override fun execute(): ForecastList {
        val forecastRequest = ForecastRequest(zipCode)
        return ForecastDataMapper().convertFromDataModel(forecastRequest.execute())
    }
}