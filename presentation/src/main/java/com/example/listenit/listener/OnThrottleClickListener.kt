package com.example.listenit.listener

import android.util.Log
import android.view.View

class OnThrottleClickListener(
    private val clickListener: View.OnClickListener,
    private val delay : Long = 300
) : View.OnClickListener {

    private var clickable : Boolean = false

    override fun onClick(view: View?) {
        if(clickable) {
            clickable = false
            view?.run {
                postDelayed({
                    clickable = true
                }, delay)
                clickListener.onClick(view)
            }
        } else {
            Log.d("Click", "waiting delay")
        }
    }

    fun View.onThrottleClick(action : (v:View) -> Unit) {
        val listener = View.OnClickListener { action(it) }
        setOnClickListener { OnThrottleClickListener(listener) }
    }

    fun View.onThrottleClick(action : (v:View) -> Unit, delay: Long) {
        val listener = View.OnClickListener { action(it) }
        setOnClickListener { OnThrottleClickListener(listener, delay) }
    }
}