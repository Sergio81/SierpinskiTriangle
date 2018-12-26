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

class MyCustomView(context: Context, attrs: AttributeSet) : View(context, attrs){
    private val STROKE_WITH = 4f
    private val animationDuration: Long = attrs.getAttributeIntValue(2, 1000).toLong()
    private val fixedPosition: Boolean = attrs.getAttributeBooleanValue(3, true)
    private val numberOfPoints = attrs.getAttributeIntValue(4, 4000)


    private var maxWidth:Int = 0
    private var maxHeight:Int = 0

    private var xRange:IntRange = (0..10)
    private var yRange:IntRange = (0..10)

    private lateinit var A: Point
    private lateinit var B:Point
    private lateinit var C:Point

    private fun IntRange.random() = Random().nextInt((endInclusive + 1) - start) +  start

    private fun paintChaos(canvas: Canvas) {
        xRange = (0..maxWidth)
        yRange = (0..maxHeight)

        if(fixedPosition){
            A = Point(maxWidth.toFloat()/2f, 10f, Paint().apply { color = Color.RED; strokeWidth = STROKE_WITH })
            B = Point(10f, (maxHeight-10).toFloat(), Paint().apply { color = Color.BLUE; strokeWidth = STROKE_WITH })
            C = Point((maxWidth - 10).toFloat(), (maxHeight-10).toFloat(), Paint().apply { color = Color.GREEN; strokeWidth = STROKE_WITH })
        }else{
            A = Point(xRange.random().toFloat(), yRange.random().toFloat(), Paint().apply { color = Color.RED; strokeWidth = STROKE_WITH })
            B = Point(xRange.random().toFloat(), yRange.random().toFloat(), Paint().apply { color = Color.BLUE; strokeWidth = STROKE_WITH })
            C = Point(xRange.random().toFloat(), yRange.random().toFloat(), Paint().apply { color = Color.GREEN; strokeWidth = STROKE_WITH })
        }

        var nextPoint = Point(xRange.random().toFloat(), yRange.random().toFloat(), A.style)

        canvas.apply {
            //drawText("Hello World", A.x, A.y, paintStyle)
            drawPoint(A.x, A.y, A.style)
            drawPoint(B.x, B.y, B.style)
            drawPoint(C.x, C.y, C.style)

            for(i in 0..numberOfPoints){
                var primaryPoint = choosePrimaryPoint()

                nextPoint.x = (primaryPoint.x + nextPoint.x) / 2f
                nextPoint.y = (primaryPoint.y + nextPoint.y) / 2f
                nextPoint.style = primaryPoint.style

                drawPoint(nextPoint.x, nextPoint.y, nextPoint.style)
            }
        }
    }

    private fun choosePrimaryPoint():Point{
        var randomSelector = (0..2).random()

        return when(randomSelector){
            0 -> A
            1 -> B
            2 -> C
            else -> A
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        maxWidth = w
        maxHeight = h
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        paintChaos(canvas)
        if(!fixedPosition)
            this.postInvalidateDelayed(animationDuration)
    }
}