package com.mohamadamin.learningkt.data.network.command

import com.mohamadamin.learningkt.data.executor.Command
import com.mohamadamin.learningkt.data.interactor.ForecastProvider
import com.mohamadamin.learningkt.domain.entity.ForecastList

/**
 * @author MohamadAmin Mohamadi (mohammadi.mohamadamin@gmail.com) on 2/11/17.
 */
class RequestForecastCommand(val zipCode: Long, val forecastProvider: ForecastProvider): Command<ForecastList> {

    companion object {
        val DAYS = 7
    }

    override fun execute() = forecastProvider.requestByZipCode(zipCode, DAYS)

}