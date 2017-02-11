package com.mohamadamin.learningkotlin.main

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.mohamadamin.learningkotlin.R
import com.mohamadamin.learningkotlin.data.entity.Forecast
import org.jetbrains.anko.find

/**
 * @author MohamadAmin Mohamadi (mohammadi.mohamadamin@gmail.com) on 2/11/17.
 */
class ForecastViewHolder(val view: View, val itemClick: ForecastListAdapter.OnItemClickListener):
        RecyclerView.ViewHolder(view) {

    private val iconView: ImageView
    private val dateView: TextView
    private val descriptionView: TextView
    private val maxTempView: TextView
    private val minTempView: TextView

    init {
        iconView = view.find(R.id.forecast_item_icon)
        dateView = view.find(R.id.forecast_item_date)
        descriptionView = view.find(R.id.forecast_item_description)
        maxTempView = view.find(R.id.forecast_item_maxtemp)
        minTempView= view.find(R.id.forecast_item_mintemp)
    }

    fun bindForecast(forecast: Forecast) {

        with(forecast) {

            Glide.with(view.context)
                    .load(forecast.iconUrl)
                    .into(iconView)

            dateView.text = date
            descriptionView.text = description
            maxTempView.text = "${high.toString()}"
            minTempView.text = "${low.toString()}"
            itemView.setOnClickListener { itemClick(this) }

        }

    }

}