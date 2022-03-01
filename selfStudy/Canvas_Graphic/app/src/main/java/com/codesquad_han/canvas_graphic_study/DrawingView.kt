package com.codesquad_han.canvas_graphic_study

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class DrawingView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val paint = Paint()
        paint.color = Color.GREEN
        paint.style = Paint.Style.FILL
        paint.strokeWidth = 5f

        canvas?.drawCircle(0f, 0f, 150f, paint)
        canvas?.drawRect(0f,300f,300f, 200F, paint)

    }
}