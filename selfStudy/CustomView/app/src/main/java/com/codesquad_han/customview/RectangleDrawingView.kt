package com.codesquad_han.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View

private const val TAG = "RectangleDrawingView"

class RectangleDrawingView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var currentRectanglePoint: RectanglePoint? = null
    private val rectangleList = mutableListOf<RectanglePoint>()

    private val rectanglePaint = Paint().apply{
        color = 0x22ff0000.toInt()
    }
    private val backgroundPaint = Paint().apply {
        color = 0xfff8efe0.toInt()
    }

    override fun onDraw(canvas: Canvas) {
        // 배경 채우기
        canvas.drawPaint(backgroundPaint)

        rectangleList.forEach { rectangle ->
            canvas.drawRect(rectangle.left, rectangle.top, rectangle.right, rectangle.bottom, rectanglePaint)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val currentP = PointF(event.x, event.y)
        var action = ""
        when (event.action){
            MotionEvent.ACTION_DOWN ->{
                action = "ACTION_DOWN"
                // 새로운 사각형 그리기 위해 터치 시 초기화
                currentRectanglePoint = RectanglePoint(currentP).also {
                    rectangleList.add(it)
                }
            }
            MotionEvent.ACTION_MOVE ->{
                action = "ACTION_MOVE"
                updateCurrentRectangle(currentP)
            }
            MotionEvent.ACTION_UP ->{
                action = "ACTION_UP"
                updateCurrentRectangle(currentP)
                currentRectanglePoint = null
            }
            MotionEvent.ACTION_CANCEL ->{
                action = "ACTION_CANCEL"
                currentRectanglePoint = null
            }
        }
        Log.i(TAG, "$action at x = ${currentP.x}, y = ${currentP.y}")

        return true
    }

    private fun updateCurrentRectangle(currentP: PointF){
        currentRectanglePoint?.let{
            it.end = currentP
            invalidate()
        // 새로운 사각형을 생성하거나 크기를 조절할 때 invalidate 호출해서 RectangleDrawingView를 다시 그린다
        }
    }
}