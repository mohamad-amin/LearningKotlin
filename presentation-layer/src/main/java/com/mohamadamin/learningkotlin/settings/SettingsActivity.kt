package com.mohamadamin.learningkotlin.settings

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.mohamadamin.learningkotlin.R
import com.mohamadamin.learningkotlin.base.ToolbarManager
import com.mohamadamin.learningkt.data.delegate.DelegatesExt
import kotlinx.android.synthetic.main.activity_settings.*
import org.jetbrains.anko.find

class SettingsActivity : AppCompatActivity(), ToolbarManager {

    companion object {
        val ZIP_CODE = "zipCode" to 94043L
    }

    override val toolbar: Toolbar by lazy { find<Toolbar>(R.id.toolbar) }
    var zipCode: Long by DelegatesExt.preference(this, ZIP_CODE.first, ZIP_CODE.second)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        setSupportActionBar(toolbar)
        enableHomeAsUp { onBackPressed() }
        cityCode.setText(zipCode.toString())
    }

    override fun onBackPressed() {
        super.onBackPressed()
        zipCode = cityCode.text.toString().toLong()
    }

}
