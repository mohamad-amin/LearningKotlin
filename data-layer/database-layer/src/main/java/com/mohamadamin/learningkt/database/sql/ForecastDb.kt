package com.mohamadamin.learningkt.database.sql

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.mohamadamin.learningkt.database.entity.CityForecast
import com.mohamadamin.learningkt.database.entity.DayForecast
import com.mohamadamin.learningkt.database.mapper.DbDataMapper
import com.mohamadamin.learningkt.domain.datasource.ForecastDataSource
import com.mohamadamin.learningkt.domain.entity.Forecast
import com.mohamadamin.learningkt.domain.entity.ForecastList
import org.jetbrains.anko.db.MapRowParser
import org.jetbrains.anko.db.SelectQueryBuilder
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import java.util.*

/**
 * @author MohamadAmin Mohamadi (mohammadi.mohamadamin@gmail.com) on 2/21/17.
 */
class ForecastDb(context: Context, val dataMapper: DbDataMapper = DbDataMapper()) : ForecastDataSource {

    var forecastDbHelper: ForecastDbHelper? = null

    fun SelectQueryBuilder.byId(id: Long) =
            whereSimple("_id = ?", id.toString())

    fun <T : Any> SelectQueryBuilder.parseList(parser: (Map<String, Any?>) -> T) : List<T> =
            parseList(object: MapRowParser<T> {
                override fun parseRow(columns: Map<String, Any?>): T = parser(columns)
            })

    fun <T : Any> SelectQueryBuilder.parseOpt(parser: (Map<String, Any?>) -> T) : T? =
            parseOpt(object: MapRowParser<T> {
                override fun parseRow(columns: Map<String, Any?>): T = parser(columns)
            })

    fun SQLiteDatabase.clear(tableName: String) {
        execSQL("delete from $tableName")
    }

    fun <K, V : Any> MutableMap<K, V?>.toVarargArray() : Array<out Pair<K, V>> =
        map({ Pair(it.key, it.value!!) }).toTypedArray()

    init {
        ForecastDbHelper.initialize(context)
        forecastDbHelper = ForecastDbHelper.instance
    }

    override fun requestForecastByZipCode(zipCode: Long, date: Long) = forecastDbHelper!!.use {

        val dailyRequest = "${DayForecastTable.CITY_ID} = ? AND ${DayForecastTable.DATE} >= ?"
        val dailyForecast = select(DayForecastTable.NAME)
                .whereSimple(dailyRequest, zipCode.toString(), date.toString())
                .parseList {
                    DayForecast(HashMap(it))
                }

        val city = select(CityForecastTable.NAME)
                .whereSimple("${CityForecastTable.ID} = ?", zipCode.toString())
                .parseOpt {
                    CityForecast(HashMap(it), dailyForecast)
                }

        if (city != null) dataMapper.convertToDomain(city) else null

    }

    fun saveForecast(forecast: ForecastList) = forecastDbHelper!!.use {

        clear(DayForecastTable.NAME)
        clear(CityForecastTable.NAME)

        with (dataMapper.convertFromDomain(forecast)) {
            insert(CityForecastTable.NAME, *map.toVarargArray())
            dailyForecast.forEach {
                insert(DayForecastTable.NAME, *it.map.toVarargArray())
            }
        }

    }

    override fun requestDayForecast(id: Long): Forecast? = forecastDbHelper!!.use {
        Log.d(javaClass.simpleName, "1")
        val forecast = select(DayForecastTable.NAME).byId(id)
                .parseOpt { DayForecast(HashMap(it)) }
        if (forecast == null) Log.d(javaClass.simpleName, "Null forecast from db for id: $id")
        if (forecast != null) dataMapper.convertDayToDomain(forecast) else null
    }

}