package com.mohamadamin.learningkotlin.data.delegate

import kotlin.properties.ReadWriteProperty

/**
 * @author MohamadAmin Mohamadi (mohammadi.mohamadamin@gmail.com) on 2/19/17.
 */
object DelegatesExt {

    fun <T> notNullSingleValue() :
            ReadWriteProperty<Any?, T> = NotNullSingleValueVar()

}