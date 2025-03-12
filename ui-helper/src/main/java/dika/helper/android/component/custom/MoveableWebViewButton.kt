/*
 * Copyright (c) 2020. The Axisnet Project.
 *
 * Licensed under XL Axiata Inc.
 * Software distributed under the license .
 */

package dika.helper.android.component.custom

import android.annotation.TargetApi
import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.webkit.WebView
import kotlin.math.max
import kotlin.math.min

class MoveableWebViewButton : WebView, OnTouchListener {
    private var downRawX = 0f
    private var downRawY = 0f
    private var dX = 0f
    private var dY = 0f

    constructor(context: Context) : super(
        getFixedContext(
            context
        )
    ) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(
        getFixedContext(
            context
        ),
        attrs
    ) {
        init()
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(
        getFixedContext(
            context
        ),
        attrs,
        defStyleAttr,
        defStyleRes
    )

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        setOnTouchListener(this)
    }

    override fun onTouch(view: View, motionEvent: MotionEvent): Boolean {
        val layoutParams = view.layoutParams as MarginLayoutParams
        val action = motionEvent.action
        return if (action == MotionEvent.ACTION_DOWN) {
            downRawX = motionEvent.rawX
            downRawY = motionEvent.rawY
            dX = view.x - downRawX
            dY = view.y - downRawY
            true
        } else if (action == MotionEvent.ACTION_MOVE) {
            val viewWidth = view.width
            val viewHeight = view.height
            val viewParent = view.parent as View
            val parentWidth = viewParent.width
            val parentHeight = viewParent.height
            var newX = motionEvent.rawX + dX
            newX = max(layoutParams.leftMargin.toFloat(), newX)
            newX = min(
                parentWidth - viewWidth - layoutParams.rightMargin.toFloat(),
                newX
            )
            var newY = motionEvent.rawY + dY
            newY = max(layoutParams.topMargin.toFloat(), newY)
            newY = min(
                parentHeight - viewHeight - layoutParams.bottomMargin.toFloat(),
                newY
            )
            view.animate()
                .x(newX)
                .y(newY)
                .setDuration(0)
                .start()
            true
        } else if (action == MotionEvent.ACTION_UP) {
            val upRawX = motionEvent.rawX
            val upRawY = motionEvent.rawY
            val upDX = upRawX - downRawX
            val upDY = upRawY - downRawY
            if (Math.abs(upDX) < CLICK_DRAG_TOLERANCE && Math.abs(
                    upDY
                ) < CLICK_DRAG_TOLERANCE
            ) {
                performClick()
            } else {
                true
            }
        } else {
            super.onTouchEvent(motionEvent)
        }
    }

    companion object {
        private const val CLICK_DRAG_TOLERANCE = 10f
        private fun getFixedContext(context: Context): Context {
            return if (Build.VERSION.SDK_INT in 21..22) context.createConfigurationContext(
                Configuration()
            ) else context
        }
    }
}