package ma.ofppt.androidcanvas

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import kotlin.random.Random

class Snake(context : Context, attrs : AttributeSet) : View(context,attrs) {
    val sn = ArrayList<Point>()
    var nbcol= 20
    var nbrow = 0
    var gameOver = false
    var squareSize = 0f
    var head = Point(10,10)
    lateinit var bait : Point
    var direction = Direction.right
    init {
        sn.add(head)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        squareSize = (w/nbcol).toFloat()
        nbrow = (h/squareSize).toInt()
      bait = Point(Random.nextInt(0,nbcol),Random.nextInt(0,nbrow))
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        var paint = Paint()
        paint.color = Color.rgb(240,240,240)
        paint.style = Paint.Style.STROKE
        paint.strokeWidth=2f
        for(i in 0 until  nbrow){
            for(j in 0 until  nbcol){
                val p = Point(j,i)
                canvas.drawRect(p.getRect(squareSize),paint)
            }
        }

            paint.style = Paint.Style.FILL
            paint.color = Color.GREEN
            canvas.drawRect(bait.getRect(squareSize), paint)

            paint.color = Color.RED
            for (p in sn) {
                paint.color = Color.RED
                paint.style = Paint.Style.FILL
                canvas.drawRect(p.getRect(squareSize), paint)
                paint.color = Color.GRAY
                paint.style = Paint.Style.STROKE
                canvas.drawRect(p.getRect(squareSize), paint)
            }
        if(isOver()) {
            paint.textSize = 100f
            paint.color = Color.RED
            paint.style = Paint.Style.FILL
            canvas.drawText("GameOver", (width/2).toFloat(), (height/2).toFloat(), paint)
            sn.clear()
            gameOver=true
            bait = Point(Random.nextInt(0,nbcol),Random.nextInt(0,nbrow))
            head = Point(10,10)
            sn.add(head)
        }



    }
    fun turnToLeft(){
        when(direction){
            Direction.left -> direction = Direction.bottom
            Direction.bottom -> direction = Direction.right
            Direction.right -> direction = Direction.top
            Direction.top -> direction = Direction.left

        }
    }
    fun turnToRight(){
        when(direction){
            Direction.left -> direction = Direction.top
            Direction.bottom -> direction = Direction.left
            Direction.right -> direction = Direction.bottom
            Direction.top -> direction = Direction.right

        }

        }
    fun move(){

        for(i in 0 until sn.size -1){

           sn[i].X = sn[i+1].X
           sn[i].Y = sn[i+1].Y
        }
        if(isBait()){
            sn.add(bait)
            head = bait
            bait = Point(Random.nextInt(0,nbcol),Random.nextInt(0,nbrow))
        }
        else{
            when(direction){
                Direction.left -> {
                    head.X--
                    if(head.X <0) head.X = nbcol -1
                }
                Direction.bottom ->{
                    head.Y++
                    if(head.Y >=nbrow) head.Y =0
                }
                Direction.right -> {
                    head.X++
                    if(head.X >=nbcol) head.X =0
                }
                Direction.top -> {
                    head.Y--
                    if(head.Y <0) head.Y =nbrow -1
                }

            }
        }


    }
    fun isBait() : Boolean{
        var next = Point(head.X,head.Y)
        when(direction){
            Direction.left -> {
                next.X--
                if(next.X <0) next.X = nbcol -1
            }
            Direction.bottom ->{
                next.Y++
                if(next.X ==nbrow) next.X =0
            }
            Direction.right -> {
                next.X++
                if(next.X ==nbcol) next.X =0
            }
            Direction.top -> {
                next.Y--
                if(next.Y <0) next.Y =nbrow -1
            }

        }
        return next.X == bait.X && next.Y==bait.Y

    }

fun isOver():Boolean{
    for (p in sn){
        if(p!=head && p.X ==head.X && p.Y==head.Y) return true
    }
    return false
}
}