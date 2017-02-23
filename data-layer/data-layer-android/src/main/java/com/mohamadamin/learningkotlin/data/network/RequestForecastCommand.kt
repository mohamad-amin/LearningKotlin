package com.mohamadamin.learningkotlin.data.network

import com.mohamadamin.learningkotlin.data.entity.ForecastList
import com.mohamadamin.learningkotlin.data.executor.Command
import com.mohamadamin.learningkotlin.data.interactor.ForecastProvider

/**
 * @author MohamadAmin Mohamadi (mohammadi.mohamadamin@gmail.com) on 2/11/17.
 */
class RequestForecastCommand(val zipCode: Long, val forecastProvider: ForecastProvider): Command<ForecastList> {

    companion object {
        val DAYS = 7
    }

    override fun execute(): ForecastList {
        return forecastProvider.requestByZipCode(zipCode, DAYS)
    }

}