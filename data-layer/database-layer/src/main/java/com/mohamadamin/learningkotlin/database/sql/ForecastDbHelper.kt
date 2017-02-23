package com.mohamadamin.learningkotlin

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

/**
 * @author MohamadAmin Mohamadi (mohammadi.mohamadamin@gmail.com) on 2/19/17.
 */
class ForecastDbHelper private constructor(context: Context) : ManagedSQLiteOpenHelper(context,
        Companion.DB_NAME, null, Companion.DB_VERSION) {

    companion object {

        private var context: Context? = null
        val DB_NAME = "forecast.db"
        val DB_VERSION = 1

        fun initialize(context: Context) {
            this.context = context
        }

        val instance by lazy {
            ForecastDbHelper(context!!)
        }

    }

    override fun onCreate(db: SQLiteDatabase) {

        db.createTable(CityForecastTable.NAME, true,
            CityForecastTable.ID to INTEGER + PRIMARY_KEY,
            CityForecastTable.CITY to TEXT,
            CityForecastTable.COUNTRY to TEXT
        )

        db.createTable(DayForecastTable.NAME, true,
            DayForecastTable.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            DayForecastTable.DATE to INTEGER,
            DayForecastTable.DESCRIPTION to TEXT,
            DayForecastTable.HIGH to INTEGER,
            DayForecastTable.LOW to INTEGER,
            DayForecastTable.ICON_URL to TEXT,
            DayForecastTable.CITY_ID to INTEGER
        )

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(CityForecastTable.NAME)
        db.dropTable(DayForecastTable.NAME)
        onCreate(db)
    }

}