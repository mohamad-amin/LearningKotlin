package com.mohamadamin.learningkotlin

import android.app.Application
import com.mohamadamin.learningkt.domain.delegate.DelegatesExt

/**
 * @author MohamadAmin Mohamadi (mohammadi.mohamadamin@gmail.com) on 2/19/17.
 */
class App: Application() {

    companion object {
        var instance: App by DelegatesExt.notNullSingleValue()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

}