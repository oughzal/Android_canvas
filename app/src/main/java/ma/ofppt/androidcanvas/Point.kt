package ma.ofppt.androidcanvas

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF

class Point(var X:Int,var Y:Int) {

   companion object{
       var squareSize = 0f
   }
    fun getRect(squareSize:Float) : RectF{
        return RectF(X*squareSize, Y*squareSize,
            (X+1)*squareSize,
            (Y+1)*squareSize)
    }

}