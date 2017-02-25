package com.mohamadamin.learningkotlin.base

import android.support.v7.graphics.drawable.DrawerArrowDrawable
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.View

/**
 * @author MohamadAmin Mohamadi (mohammadi.mohamadamin@gmail.com) on 2/25/17.
 */
interface ToolbarManager {

    val toolbar: Toolbar

    fun View.slideExit() {
        if (translationY == 0f) animate().translationY(-height.toFloat())
    }

    fun View.slideEnter() {
        if (translationY < 0f) animate().translationY(0f)
    }

    var toolbarTitle: String
        get() = toolbar.title.toString()
        set(value) {
            toolbar.title = value
        }

    fun initToolbar(resId: Int? = null, itemClick: ((menuId: Int) -> Boolean)? = null) {
        if (resId != null) toolbar.inflateMenu(resId)
        if (itemClick != null) toolbar.setOnMenuItemClickListener { itemClick(it.itemId) }
    }

    fun enableHomeAsUp(up: () -> Unit) {
        toolbar.navigationIcon = createUpDrawable()
        toolbar.setNavigationOnClickListener { up() }
    }

    fun createUpDrawable() = DrawerArrowDrawable(toolbar.context).apply {
        progress = 1f
    }

    fun attachToScroll(recyclerView: RecyclerView) {
        recyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                if (dy > 0) toolbar.slideExit() else toolbar.slideEnter()
            }
        })
    }

}