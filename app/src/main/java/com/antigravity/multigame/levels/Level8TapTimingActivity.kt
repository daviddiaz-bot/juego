package com.antigravity.multigame.levels

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.antigravity.multigame.ProgressManager
import com.antigravity.multigame.R

class Level8TapTimingActivity : AppCompatActivity() {

    private lateinit var timingBar: ProgressBar
    private lateinit var tapButton: Button
    private lateinit var scoreText: TextView
    
    private var score = 0
    private var progress = 0
    private var isIncreasing = true
    private val handler = Handler(Looper.getMainLooper())
    private var isRunning = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level8)

        timingBar = findViewById(R.id.timingBar)
        tapButton = findViewById(R.id.tapButton)
        scoreText = findViewById(R.id.scoreText)

        tapButton.setOnClickListener { checkTiming() }
        
        startAnimation()
    }

    private fun startAnimation() {
        isRunning = true
        animateBar()
    }

    private fun animateBar() {
        if (!isRunning) return
        
        handler.postDelayed({
            if (isIncreasing) {
                progress += 2
                if (progress >= 100) {
                    progress = 100
                    isIncreasing = false
                }
            } else {
                progress -= 2
                if (progress <= 0) {
                    progress = 0
                    isIncreasing = true
                }
            }
            
            timingBar.progress = progress
            animateBar()
        }, 20)
    }

    private fun checkTiming() {
        val inGreenZone = progress in 45..55
        
        if (inGreenZone) {
            score++
            scoreText.text = "Puntuación: $score"
            Toast.makeText(this, "¡Perfecto!", Toast.LENGTH_SHORT).show()
            
            if (score >= 10) {
                isRunning = false
                ProgressManager(this).completeLevel(8)
                Toast.makeText(this, "¡Nivel completado!", Toast.LENGTH_LONG).show()
                handler.postDelayed({ finish() }, 2000)
            }
        } else {
            Toast.makeText(this, "¡Fallaste! Puntuación final: $score", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        isRunning = false
        handler.removeCallbacksAndMessages(null)
    }
}
