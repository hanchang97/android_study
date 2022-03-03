package com.codesquad_han.canvas_graphic_study

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.ViewTreeObserver
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.graphics.*
import com.codesquad_han.canvas_graphic_study.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var a = Integer.toHexString(0)
        var b = Integer.toHexString(16)
        var c = Integer.toHexString(100)

        if(a.length == 1) a = "0" + a
        if(b.length == 1) a = "0" + b
        if(c.length == 1) a = "0" + c

        Log.d("AppTest", "$a $b $c")

        ////////////

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)

        var dpi = displayMetrics.densityDpi
        var density = displayMetrics.density


        binding.constraintLayoutMain.viewTreeObserver.addOnGlobalLayoutListener(object :
            ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                binding.constraintLayoutMain.viewTreeObserver.removeOnGlobalLayoutListener(this)
                val width = binding.constraintLayoutMain.width
                val height = binding.constraintLayoutMain.height
                Log.d("AppTest", "width:$width, height:$height")
            }
        })

        ///////
        var gradientDrawable = binding.iv.background as GradientDrawable
        //gradientDrawable.setStroke(5, Color.RED)
        gradientDrawable.setColor(ContextCompat.getColor(this, R.color.purple_700))

        setIv1()
        setBtn()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            setBtn2(density)
        }
    }

    fun setIv1() {
        binding.iv.setOnClickListener {
            var gradientDrawable = binding.iv.background as GradientDrawable
            gradientDrawable.setStroke(5, Color.RED)
        }
    }

    fun setBtn() {
        binding.btn.setOnClickListener {
            var gradientDrawable = binding.iv.background as GradientDrawable
            gradientDrawable.setStroke(5, null)
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun setBtn2(density: Float) {
        binding.btn2.setOnClickListener {
            val dynamicImageView = ImageView(this)
            val layoutParams = LinearLayout.LayoutParams(ConvertDPtoPX(this, 150), (150*density + 0.5).toInt())
            dynamicImageView.layoutParams = layoutParams
            dynamicImageView.setBackgroundResource(R.drawable.stroke1)

            var gradientDrawable = dynamicImageView.background as GradientDrawable
            //gradientDrawable.setColor(ContextCompat.getColor(this, R.color.purple_200))
            gradientDrawable.setColor(Color.parseColor("#1ABB86FC"))

            dynamicImageView.x = 400f // 처음 구한 전체 뷰의 값과 같은 단위 인듯 -> 해상도??
            dynamicImageView.y = 0f
            //dynamicImageView.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.purple_700))
            //dynamicImageView.backgroundTintMode = PorterDuff.Mode.SCREEN
            //dynamicImageView.alpha = 0.1f

            dynamicImageView.setOnClickListener {
                var gradientDrawable = dynamicImageView.background as GradientDrawable
                gradientDrawable.setStroke(5, Color.RED)

                Log.d("AppTest", "${gradientDrawable.color}")  // gradientDrawable.color 기능이 api24 부터 가능하다
                Log.d("AppTest", "${gradientDrawable.color?.defaultColor?.red}")
                Log.d("AppTest", "${gradientDrawable.color?.defaultColor?.green}")
                Log.d("AppTest", "${gradientDrawable.color?.defaultColor?.blue}")
                Log.d("AppTest", "${gradientDrawable.color?.defaultColor?.alpha}")

                val argb = android.graphics.Color.argb(255, 187, 134, 252)
                val rgb = android.graphics.Color.rgb(187, 134, 252)
                Log.d("AppTest", "${argb}, ${rgb}")

                Log.d("AppTest", "${Integer.toHexString(187)}${Integer.toHexString(134)}${Integer.toHexString(252)}")
            }

            binding.frameLayout.addView(dynamicImageView)
        }
    }

    fun ConvertDPtoPX(context: Context, dp: Int): Int {
        val density = context.resources.displayMetrics.density
        return Math.round(dp.toFloat() * density)
    }
}