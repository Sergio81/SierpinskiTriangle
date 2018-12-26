package com.androidtraining.canvas.View

import android.graphics.Color
import android.graphics.Paint
import com.androidtraining.canvas.model.Point
import java.util.*

class MyCustomViewPresenter(
    private val view: MyCustomViewContract.View,
    val fixedPosition: Boolean,
    val numberOfPoints: Int
) : MyCustomViewContract.Presenter {

    private val STROKE_WITH = 4f

    var maxWidth: Int = 0
    var maxHeight: Int = 0

    private var xRange: IntRange = (0..10)
    private var yRange: IntRange = (0..10)

    private lateinit var A: Point
    private lateinit var B: Point
    private lateinit var C: Point

    private fun IntRange.random() = Random().nextInt((endInclusive + 1) - start) + start

    private fun choosePrimaryPoint(): Point {
        var randomSelector = (0..2).random()

        return when (randomSelector) {
            0 -> A
            1 -> B
            2 -> C
            else -> A
        }
    }

    override fun paintChaos() {
        xRange = (0..maxWidth)
        yRange = (0..maxHeight)

        if (fixedPosition) {
            A = Point(maxWidth.toFloat() / 2f, 10f, Paint().apply { color = Color.RED; strokeWidth = STROKE_WITH })
            B = Point(10f, (maxHeight - 10).toFloat(), Paint().apply { color = Color.BLUE; strokeWidth = STROKE_WITH })
            C = Point(
                (maxWidth - 10).toFloat(),
                (maxHeight - 10).toFloat(),
                Paint().apply { color = Color.GREEN; strokeWidth = STROKE_WITH })
        } else {
            A = Point(
                xRange.random().toFloat(),
                yRange.random().toFloat(),
                Paint().apply { color = Color.RED; strokeWidth = STROKE_WITH })
            B = Point(
                xRange.random().toFloat(),
                yRange.random().toFloat(),
                Paint().apply { color = Color.BLUE; strokeWidth = STROKE_WITH })
            C = Point(
                xRange.random().toFloat(),
                yRange.random().toFloat(),
                Paint().apply { color = Color.GREEN; strokeWidth = STROKE_WITH })
        }

        var nextPoint = Point(xRange.random().toFloat(), yRange.random().toFloat(), A.style)

        view.ShowPoint(A)
        view.ShowPoint(B)
        view.ShowPoint(C)

        for (i in 0..numberOfPoints) {
            var primaryPoint = choosePrimaryPoint()

            nextPoint.x = (primaryPoint.x + nextPoint.x) / 2f
            nextPoint.y = (primaryPoint.y + nextPoint.y) / 2f
            nextPoint.style = primaryPoint.style

            view.ShowPoint(nextPoint)
        }
    }
}