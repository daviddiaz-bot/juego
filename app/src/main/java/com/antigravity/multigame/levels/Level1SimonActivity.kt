package com.antigravity.multigame.levels

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.antigravity.multigame.ProgressManager
import com.antigravity.multigame.R

class Level1SimonActivity : AppCompatActivity() {

    private lateinit var redButton: CardView
    private lateinit var greenButton: CardView
    private lateinit var blueButton: CardView
    private lateinit var yellowButton: CardView
    private lateinit var startButton: Button
    private lateinit var scoreText: TextView
    
    private val sequence = mutableListOf<Int>()
    private val playerSequence = mutableListOf<Int>()
    private var score = 0
    private var isPlaying = false
    private val handler = Handler(Looper.getMainLooper())
    
    private val RED = 0
    private val GREEN = 1
    private val BLUE = 2
    private val YELLOW = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level1)

        redButton = findViewById(R.id.redButton)
        greenButton = findViewById(R.id.greenButton)
        blueButton = findViewById(R.id.blueButton)
        yellowButton = findViewById(R.id.yellowButton)
        startButton = findViewById(R.id.startButton)
        scoreText = findViewById(R.id.scoreText)

        redButton.setOnClickListener { if (!isPlaying) onColorPressed(RED) }
        greenButton.setOnClickListener { if (!isPlaying) onColorPressed(GREEN) }
        blueButton.setOnClickListener { if (!isPlaying) onColorPressed(BLUE) }
        yellowButton.setOnClickListener { if (!isPlaying) onColorPressed(YELLOW) }
        startButton.setOnClickListener { startGame() }
    }

    private fun startGame() {
        score = 0
        sequence.clear()
        playerSequence.clear()
        scoreText.text = "Puntuación: $score"
        nextRound()
    }

    private fun nextRound() {
        playerSequence.clear()
        sequence.add((0..3).random())
        playSequence()
    }

    private fun playSequence() {
        isPlaying = true
        startButton.isEnabled = false
        
        var delay = 0L
        sequence.forEach { color ->
            handler.postDelayed({
                flashColor(color)
            }, delay)
            delay += 800
        }
        
        handler.postDelayed({
            isPlaying = false
            startButton.isEnabled = true
        }, delay)
    }

    private fun flashColor(color: Int) {
        val button = when (color) {
            RED -> redButton
            GREEN -> greenButton
            BLUE -> blueButton
            else -> yellowButton
        }
        
        button.alpha = 1.0f
        handler.postDelayed({ button.alpha = 0.6f }, 400)
    }

    private fun onColorPressed(color: Int) {
        flashColor(color)
        playerSequence.add(color)
        
        val currentIndex = playerSequence.size - 1
        if (playerSequence[currentIndex] != sequence[currentIndex]) {
            Toast.makeText(this, "¡Perdiste! Puntuación: $score", Toast.LENGTH_SHORT).show()
            startButton.text = "Reintentar"
        } else if (playerSequence.size == sequence.size) {
            score++
            scoreText.text = "Puntuación: $score"
            
            if (score >= 5) {
                ProgressManager(this).completeLevel(1)
                Toast.makeText(this, "¡Nivel completado!", Toast.LENGTH_LONG).show()
                handler.postDelayed({ finish() }, 2000)
            } else {
                handler.postDelayed({ nextRound() }, 1000)
            }
        }
    }
}
