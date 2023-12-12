package ma.ofppt.androidcanvas

import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GestureDetectorCompat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ma.ofppt.androidcanvas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), GestureDetector.OnGestureListener {
    lateinit var binding: ActivityMainBinding
    lateinit var gestureDetector: GestureDetectorCompat
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        gestureDetector = GestureDetectorCompat(this, this)

        newGame()



    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        gestureDetector.onTouchEvent(event!!)
        return true
    }

    override fun onDown(e: MotionEvent): Boolean {
        return false
    }

    override fun onShowPress(e: MotionEvent) {

    }

    override fun onSingleTapUp(event: MotionEvent): Boolean {
        if(binding.snakeCanvas.gameOver){
            newGame()
            return true
        }
        if (event.x < binding.snakeCanvas.width / 2) {
            binding.snakeCanvas.turnToLeft()
        } else {
            binding.snakeCanvas.turnToRight()
        }
        return true
    }

    override fun onScroll(
        e1: MotionEvent?,
        e2: MotionEvent,
        distanceX: Float,
        distanceY: Float
    ): Boolean {
        return false
    }

    override fun onLongPress(e: MotionEvent) {

    }

    override fun onFling(
        e1: MotionEvent?,
        e2: MotionEvent,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        return false
    }
    fun newGame(){
        binding.snakeCanvas.gameOver = false
        GlobalScope.launch(Dispatchers.Main) {
            while (true) {
                binding.snakeCanvas.move()
                binding.snakeCanvas.invalidate()
                if(binding.snakeCanvas.isOver()) break
                delay(200)
            }
        }
    }
}