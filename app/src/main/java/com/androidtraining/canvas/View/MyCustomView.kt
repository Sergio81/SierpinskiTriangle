package com.androidtraining.canvas.View

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.support.annotation.FloatRange
import android.util.AttributeSet
import android.view.View
import com.androidtraining.canvas.model.Point
import java.util.*

class MyCustomView(context: Context, attrs: AttributeSet) : View(context, attrs), MyCustomViewContract.View{
    private lateinit var canvas:Canvas
    private val animationDuration: Long = attrs.getAttributeIntValue(2, 500).toLong()
    private val presenter = MyCustomViewPresenter(
        this,
        attrs.getAttributeBooleanValue(3, true),
        attrs.getAttributeIntValue(4, 80000)
    )

    override fun ShowPoint(point: Point) {
        canvas.drawPoint(point.x, point.y, point.style)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        presenter.maxWidth = w
        presenter.maxHeight = h
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        this.canvas = canvas
        presenter.paintChaos()
        if(!presenter.fixedPosition)
            this.postInvalidateDelayed(animationDuration)
    }
}