package com.mohamadamin.learningkt.data.delegate

import android.content.Context
import kotlin.properties.ReadWriteProperty

/**
 * @author MohamadAmin Mohamadi (mohammadi.mohamadamin@gmail.com) on 2/19/17.
 */
object DelegatesExt {

    fun <T> notNullSingleValue() : ReadWriteProperty<Any?, T> =
            NotNullSingleValueVar()

    fun <T> preference(context: Context, name: String, default: T) : ReadWriteProperty<Any?, T> =
            Preference(context, name, default)

}