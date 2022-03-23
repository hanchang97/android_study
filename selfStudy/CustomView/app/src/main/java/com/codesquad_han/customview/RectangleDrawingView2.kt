package com.codesquad_han.customview

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.core.graphics.drawable.toBitmap

private const val TAG = "AppTest"

class RectangleDrawingView2 @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var startPointFList = mutableListOf<PointF>(PointF(600f, 700f), PointF(100F, 850F))
    var pointSelected: PointF? = null
    var isDoubleTouchedExist = false
    var tempPoint : PointF? = null
    var textSize = 100f

    val paint = Paint().also {
        it.color = Color.GREEN
        it.style = Paint.Style.FILL
        it.strokeWidth = 5f
    }

    val textPaint = Paint().also {
        it.textSize = textSize
        it.style = Paint.Style.FILL

    }

    val tempPaint = Paint().also {
        it.color = Color.GREEN
        it.style = Paint.Style.FILL
        it.strokeWidth = 5f
        it.alpha = 5 * 25.5.toInt()
    }

    override fun onDraw(canvas: Canvas) {
        // 배경 채우기
        super.onDraw(canvas)

        //canvas.drawCircle(0f, 0f, 150f, paint)
        canvas.drawRect(100f, 100f, 400f, 200F, paint)
        canvas.drawRect(100f, 400f, 400f, 530F, paint)

        canvas.drawText("hello bee", 100f, 200f, textPaint)
        var rect = Rect()
        textPaint.getTextBounds("hello bye", 0, 9, rect)
        Log.d("AppTest", "drawText width/ ${rect.width()}, ${rect.height()}")

        canvas.drawText("yellow candy", 100f, 500f, textPaint)



        startPointFList.forEach {
                canvas.drawRect(it.x, it.y, it.x + 400f, it.y + 400f, paint)
        }
        tempPoint?.let {
            canvas.drawRect(it.x, it.y, it.x + 400f, it.y + 400f, tempPaint)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val currentP = PointF(event.x, event.y)
        var action = ""

        var pointCount = event.pointerCount
        Log.d(TAG, "pointerCount = $pointCount")

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                action = "ACTION_DOWN"
                // 새로운 사각형 그리기 위해 터치 시 초기화
                checkPoint(PointF(event.getX(0), event.getY(0)))

            }
            MotionEvent.ACTION_MOVE -> {
                action = "ACTION_MOVE"
                if (pointCount == 2) {
                    if (twoPointCheck(PointF(event.getX(0), event.getY(0))) &&
                        twoPointCheck(PointF(event.getX(1), event.getY(1)))
                    ) {
                        Log.d("AppTest", "two pointer true!!!!")

                        if (!isDoubleTouchedExist) {
                            isDoubleTouchedExist = true
                            //startPointFList.add(PointF(pointSelected!!.x, pointSelected!!.y))
                            tempPoint = (PointF(pointSelected!!.x, pointSelected!!.y))
                            invalidate()
                        }

                        tempPoint!!.x = event.getX(0) - 200
                        tempPoint!!.y = event.getY(0) - 200
                        invalidate()
                    }
                }
            }
            MotionEvent.ACTION_UP -> {
                action = "ACTION_UP"

                if (isDoubleTouchedExist) {
                    pointSelected!!.x = tempPoint!!.x
                    pointSelected!!.y = tempPoint!!.y
                    tempPoint = null
                    isDoubleTouchedExist = false
                    invalidate()
                }
            }
            MotionEvent.ACTION_CANCEL -> {
                action = "ACTION_CANCEL"

            }
        }

        when (pointCount) {
            1 -> {
                Log.i(TAG, "2/ $action/ pointer1/  x = ${event.getX(0)}, y = ${event.getY(0)}")
                Log.i(TAG, "2/ $action/ pointer2/  x =  , y = ")
            }
            2 -> {
                Log.i(TAG, "2/ $action/ pointer1/  x = ${event.getX(0)}, y = ${event.getY(0)}")
                Log.i(TAG, "2/ $action/ pointer2/  x = ${event.getX(1)}, y = ${event.getY(1)}")
            }
        }


        return true
    }

    fun checkPoint(pointF: PointF) {

        for (i in startPointFList.size - 1 downTo 0) {
            if (pointF.x >= startPointFList[i].x && pointF.x <= startPointFList[i].x + 400
                && pointF.y >= startPointFList[i].y && pointF.y <= startPointFList[i].y + 400
            ) {
                pointSelected = startPointFList[i]
                Log.d("AppTest", "내부 터치")
                break
            }

        }

    }

    fun twoPointCheck(pointF: PointF): Boolean {
        if (!isDoubleTouchedExist) {
            return (pointF.x >= pointSelected!!.x && pointF.x <= pointSelected!!.x + 400
                    && pointF.y >= pointSelected!!.y && pointF.y <= pointSelected!!.y + 400)
        } else {
            return (pointF.x >= tempPoint!!.x && pointF.x <= tempPoint!!.x + 400
                    && pointF.y >= tempPoint!!.y && pointF.y <= tempPoint!!.y + 400)
        }
    }
}