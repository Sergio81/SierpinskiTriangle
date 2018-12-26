package com.androidtraining.canvas.View

import android.graphics.Canvas
import com.androidtraining.canvas.model.Point

interface MyCustomViewContract {
    interface View{
        fun ShowPoint(MyPoint:Point)
    }

    interface Presenter{
        fun paintChaos()

    }
}