package com.antigravity.multigame.levels

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.antigravity.multigame.ProgressManager
import com.antigravity.multigame.R
import kotlin.random.Random

class Level9AvoidObstaclesActivity : AppCompatActivity() {

    private lateinit var gameArea: FrameLayout
    private lateinit var scoreText: TextView
    
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level9)

        gameArea = findViewById(R.id.gameArea)
        scoreText = findViewById(R.id.scoreText)

        val gameView = AvoidObstaclesView(this) { points ->
            score = points
            scoreText.text = "Puntuación: $score"
            
            if (score >= 30) {
                ProgressManager(this).completeLevel(9)
                Toast.makeText(this, "¡Nivel completado!", Toast.LENGTH_LONG).show()
                finish()
            }
        }
        
        gameArea.addView(gameView)
    }

    class AvoidObstaclesView(context: Context, private val onScoreUpdate: (Int) -> Unit) : View(context) {
        
        private var playerX = 0f
        private var playerY = 0f
        private val playerRadius = 30f
        private val obstacles = mutableListOf<Obstacle>()
        private var score = 0
        private var isGameOver = false
        
        private val playerPaint = Paint().apply { color = Color.GREEN }
        private val obstaclePaint = Paint().apply { color = Color.RED }
        private val handler = Handler(Looper.getMainLooper())

        data class Obstacle(var x: Float, var y: Float, val radius: Float)

        init {
            startGame()
        }

        private fun startGame() {
            playerX = 200f
            playerY = 800f
            spawnObstacles()
            updateGame()
        }

        private fun spawnObstacles() {
            handler.postDelayed({
                if (!isGameOver) {
                    obstacles.add(Obstacle(
                        Random.nextFloat() * width,
                        -50f,
                        30f + Random.nextFloat() * 20f
                    ))
                    spawnObstacles()
                }
            }, 1000)
        }

        private fun updateGame() {
            handler.postDelayed({
                if (!isGameOver) {
                    obstacles.forEach { it.y += 10f }
                    obstacles.removeAll { it.y > height }
                    
                    obstacles.forEach { obstacle ->
                        val distance = Math.sqrt(
                            Math.pow((playerX - obstacle.x).toDouble(), 2.0) +
                            Math.pow((playerY - obstacle.y).toDouble(), 2.0)
                        ).toFloat()
                        
                        if (distance < playerRadius + obstacle.radius) {
                            isGameOver = true
                            handler.post {
                                Toast.makeText(context, "¡Chocaste! Puntuación: $score", Toast.LENGTH_SHORT).show()
                                (context as? AppCompatActivity)?.finish()
                            }
                            return@postDelayed
                        }
                    }
                    
                    if (obstacles.size > score / 5) {
                        score++
                        onScoreUpdate(score)
                    }
                    
                    invalidate()
                    updateGame()
                }
            }, 50)
        }

        override fun onDraw(canvas: Canvas) {
            super.onDraw(canvas)
            
            canvas.drawCircle(playerX, playerY, playerRadius, playerPaint)
            
            obstacles.forEach { obstacle ->
                canvas.drawCircle(obstacle.x, obstacle.y, obstacle.radius, obstaclePaint)
            }
        }

        override fun onTouchEvent(event: MotionEvent): Boolean {
            if (event.action == MotionEvent.ACTION_MOVE || event.action == MotionEvent.ACTION_DOWN) {
                playerX = event.x
                playerY = event.y
                invalidate()
            }
            return true
        }
    }
}
