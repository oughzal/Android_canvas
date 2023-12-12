package ma.ofppt.androidcanvas

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class Snake(context : Context, attrs : AttributeSet) : View(context,attrs) {
    var nbcol= 20
    var nbrow = 0
    var squareSize = 0f

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        squareSize = (w/nbcol).toFloat()
        nbrow = (h/squareSize).toInt()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        var paint = Paint()
        paint.color = Color.rgb(200,200,200)
        paint.style = Paint.Style.STROKE
        paint.strokeWidth=2f
        for(i in 0 until  nbrow){
            for(j in 0 until  nbcol){
                val p = Point(j,i)
                canvas.drawRect(p.X*squareSize,p.Y*squareSize,(p.X+1)*squareSize,(p.Y+1)*squareSize,paint)
            }
        }
        paint.color = Color.RED
        paint.style = Paint.Style.FILL
        val p = Point(10,10)
        canvas.drawRect(p.X*squareSize,p.Y*squareSize,(p.X+1)*squareSize,(p.Y+1)*squareSize,paint)
    }
}