
package ma.ofppt.androidcanvas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ma.ofppt.androidcanvas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        GlobalScope.launch(Dispatchers.Main) {
            while(true){
                binding.snakeCanvas.move()
                binding.snakeCanvas.invalidate()
                delay(200)
            }
        }

        binding.btnLeft.setOnClickListener {
            binding.snakeCanvas.turnToLeft()
            binding.snakeCanvas.invalidate()
        }
        binding.btnRight.setOnClickListener {
            binding.snakeCanvas.turnToRight()
            binding.snakeCanvas.invalidate()

        }
    }
}