package com.antigravity.multigame.levels

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.antigravity.multigame.ProgressManager
import com.antigravity.multigame.R
import kotlin.random.Random

class Level2CatchStarsActivity : AppCompatActivity() {

    private lateinit var gameArea: FrameLayout
    private lateinit var scoreText: TextView
    private lateinit var backButton: Button
    
    private var score = 0
    private var isGameRunning = false
    private val handler = Handler(Looper.getMainLooper())
    private val stars = mutableListOf<View>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level2)

        gameArea = findViewById(R.id.gameArea)
        scoreText = findViewById(R.id.scoreText)
        backButton = findViewById(R.id.backButton)

        backButton.setOnClickListener { finish() }
        
        startGame()
    }

    private fun startGame() {
        score = 0
        scoreText.text = "Puntuación: $score"
        isGameRunning = true
        spawnStars()
    }

    private fun spawnStars() {
        if (!isGameRunning) return
        
        val star = View(this).apply {
            layoutParams = FrameLayout.LayoutParams(60, 60)
            setBackgroundColor(Color.YELLOW)
            x = Random.nextInt(0, gameArea.width - 60).toFloat()
            y = -60f
            setOnClickListener {
                score++
                scoreText.text = "Puntuación: $score"
                gameArea.removeView(this)
                stars.remove(this)
                
                if (score >= 20) {
                    ProgressManager(this@Level2CatchStarsActivity).completeLevel(2)
                    Toast.makeText(this@Level2CatchStarsActivity, "¡Nivel completado!", Toast.LENGTH_LONG).show()
                    handler.postDelayed({ finish() }, 2000)
                    isGameRunning = false
                }
            }
        }
        
        gameArea.addView(star)
        stars.add(star)
        animateStar(star)
        
        handler.postDelayed({ spawnStars() }, 1000)
    }

    private fun animateStar(star: View) {
        star.animate()
            .y(gameArea.height.toFloat())
            .setDuration(3000)
            .withEndAction {
                gameArea.removeView(star)
                stars.remove(star)
            }
            .start()
    }

    override fun onDestroy() {
        super.onDestroy()
        isGameRunning = false
        handler.removeCallbacksAndMessages(null)
    }
}
