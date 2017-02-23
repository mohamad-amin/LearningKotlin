package com.mohamadamin.learningkt.data.network.command

import com.mohamadamin.learningkt.data.executor.Command
import com.mohamadamin.learningkt.data.interactor.ForecastProvider
import com.mohamadamin.learningkt.domain.entity.Forecast

/**
 * @author MohamadAmin Mohamadi (mohammadi.mohamadamin@gmail.com) on 2/23/17.
 */
class RequestDayForecastCommand(val id: Long, val forecastProvider: ForecastProvider) : Command<Forecast> {

    override fun execute() = forecastProvider.requestForecast(id)

}