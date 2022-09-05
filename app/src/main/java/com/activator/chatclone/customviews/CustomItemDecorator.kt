package com.activator.chatclone.customviews

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.activator.chatclone.R

class CustomItemDecorator(val context:Context, val view: Int):RecyclerView.ItemDecoration(){

    private var mOffsetLeft : Float = 0f
    private val mDivider = ContextCompat.getDrawable(context,R.drawable.item_chat_main)
    private var mOffsetRight : Float= context.resources.getDimension(R.dimen.default_padding)
    init {
        if(view==R.layout.item_status){
            mOffsetLeft = context.resources.getDimension(R.dimen.status_avatar_imageWidth)+context.resources.getDimension(R.dimen.default_padding)
        }
        else {
            mOffsetLeft = context.resources.getDimension(R.dimen.avatar_imageWidth)+context.resources.getDimension(R.dimen.default_padding)
        }
    }


    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State){
        val dividerLeft : Int = (parent.paddingLeft + mOffsetLeft).toInt()
        val dividerRight :Int = (parent.width - parent.paddingRight -mOffsetRight).toInt()

        val childCount = parent.childCount
        for (i in 0..childCount - 2) {
            val child = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams
            val dividerTop = child.bottom + params.bottomMargin
            val dividerBottom: Int = dividerTop + mDivider!!.intrinsicHeight
            mDivider.setBounds(dividerLeft, dividerTop, dividerRight, dividerBottom)
            mDivider.draw(c)
        }
    }




}
