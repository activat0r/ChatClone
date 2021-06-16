package com.activator.chatclone.customviews

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.util.Log
import de.hdodenhof.circleimageview.CircleImageView


class ProgressBar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : CircleImageView(context, attrs, defStyleAttr) {

    init {
        setupPaint()

    }
    private val mcontext = context
    var readCount:Int =2
    var unReadcount:Int =2

    private lateinit var drawPaint: Paint



    private fun setupPaint(){
        drawPaint = Paint()
        drawPaint.color = Color.LTGRAY
        drawPaint.isAntiAlias = true
        drawPaint.strokeWidth = 10f
        drawPaint.style = Paint.Style.STROKE
        drawPaint.strokeCap = Paint.Cap.ROUND
    }


    override fun onDraw(canvas: Canvas?) {

        val count = readCount + unReadcount
        var startRadii :Float  = 0f
        var individualRadii = (360/count).toFloat()
        var arcRadiiGap = 10f
        var arcRadii : Float = individualRadii - (arcRadiiGap*2)

        val rect = RectF(50f, 50f, 500f, 500f)

        for (i in 0 until unReadcount){
            startRadii = (i*individualRadii) + (arcRadiiGap) - 90f
            Log.d("Canvas", "$startRadii   ---   $arcRadii")
            drawPaint.color = Color.LTGRAY
            canvas?.drawArc(rect, startRadii, arcRadii, false, drawPaint)
        }

        for (i in unReadcount until count){
            startRadii = (i*individualRadii) + (arcRadiiGap) - 90f
            Log.d("Canvas", "$startRadii   ---   $arcRadii")
            drawPaint.color = Color.DKGRAY
            canvas?.drawArc(rect, startRadii, arcRadii, false, drawPaint)
        }

        super.onDraw(canvas)


    }


}