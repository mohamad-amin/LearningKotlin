package com.mohamadamin.learningkotlin.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.mohamadamin.learningkotlin.R
import com.mohamadamin.learningkotlin.data.entity.Forecast
import com.mohamadamin.learningkotlin.data.entity.ForecastList

/**
 * @author MohamadAmin Mohamadi (mohammadi.mohamadamin@gmail.com) on 2/11/17.
 */
class ForecastListAdapter(val weekForecast: ForecastList, val itemClick: OnItemClickListener) :
        RecyclerView.Adapter<ForecastViewHolder>() {

    interface OnItemClickListener {
        operator fun invoke(forecast: Forecast)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder? {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.forecast_item, parent, false)
        return ForecastViewHolder(view, itemClick)
    }

    override fun onBindViewHolder(viewHolder: ForecastViewHolder, position: Int) {
        viewHolder.bindForecast(weekForecast[position])
    }

    override fun getItemCount(): Int = weekForecast.size()

}