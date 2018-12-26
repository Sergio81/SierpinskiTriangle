package com.androidtraining.canvas.View

import android.graphics.Point

interface MyCustomViewContract {
    interface View{
        fun InitPoints(A:Point, B:Point, C:Point)
        fun ShowPoint(MyPoint:Point)
    }
    interface Presenter{
        fun Init()

    }
}