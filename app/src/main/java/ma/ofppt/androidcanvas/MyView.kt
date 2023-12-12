package ma.ofppt.androidcanvas

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class MyView(context : Context,attrs : AttributeSet) : View(context,attrs) {

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        var paint = Paint()
        paint.color = Color.RED
        paint.style = Paint.Style.FILL
        paint.textSize = 72f
        canvas.drawRect(100f,20f,500f,1000f,paint)
        canvas.drawCircle(1000f,200f,200f,paint)
        canvas.drawText("DEV201",300f,200f,paint)
    }
}