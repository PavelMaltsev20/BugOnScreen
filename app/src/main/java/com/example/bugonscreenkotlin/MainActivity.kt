package com.example.bugonscreenkotlin

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.WindowManager
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

@SuppressLint("ClickableViewAccessibility")
class MainActivity : AppCompatActivity() {

    private var xDelta = 0
    private var yDelta = 0
    private var x = 0
    private var y = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        setContentView(R.layout.activity_main)
        snakeHead.setOnTouchListener { v, m ->
            onTouchEvent(m)
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        x = event!!.rawX.toInt()
        y = event!!.rawY.toInt()
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> initializeDelta(event.rawX - x, event.rawY - y)
            MotionEvent.ACTION_UP -> Toast.makeText(this,"New position", Toast.LENGTH_SHORT).show()
            MotionEvent.ACTION_MOVE -> moveObject((x - xDelta), (y - yDelta))
            else -> makeConsoleLine(".")
        }
        return super.onTouchEvent(event)
    }

    private fun initializeDelta(rawX: Float, rawY: Float) {
        val lParams = snakeHead.layoutParams as RelativeLayout.LayoutParams

        xDelta = x - lParams.leftMargin
        yDelta = y - lParams.topMargin
        makeConsoleLine("Down clicked" + ", x=" + rawX + ", y=" + rawY)
    }

    private fun moveObject(left: Int, top: Int) {
        var param = snakeHead.layoutParams as RelativeLayout.LayoutParams
        param.setMargins(left, top, 0, 0)
        snakeHead.layoutParams = param
    }

    private fun makeConsoleLine(text: String) = Log.i("TAG", "Console notification: " + text)

}
