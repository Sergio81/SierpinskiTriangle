package com.androidtraining.canvas.View

import android.graphics.Point
import java.util.*

class MyCustomViewPresenter(private val view: MyCustomViewContract.View) :MyCustomViewContract.Presenter {
    var range:IntRange = (0..10)
    private var A:Point = Point(range.random(), range.random())
    private var B:Point = Point(range.random(), range.random())
    private var C:Point = Point(range.random(), range.random())

    fun IntRange.random() =
        Random().nextInt((endInclusive + 1) - start) +  start

    override fun Init() {
        view.ShowPoint(A)
        view.ShowPoint(B)
        view.ShowPoint(C)
    }

}