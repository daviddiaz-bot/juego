package com.antigravity.multigame.levels

import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.view.Gravity
import android.view.View
import android.widget.GridLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.antigravity.multigame.ProgressManager
import com.antigravity.multigame.R
import kotlin.random.Random

class Level6ColorMatchActivity : AppCompatActivity() {

    private lateinit var targetColor: View
    private lateinit var colorOptionsGrid: GridLayout
    private lateinit var timeText: TextView
    
    private var score = 0
    private var timer: CountDownTimer? = null
    private val colors = listOf(
        Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW,
        Color.CYAN, Color.MAGENTA, Color.parseColor("#FFA500"),
        Color.parseColor("#800080")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level6)

        targetColor = findViewById(R.id.targetColor)
        colorOptionsGrid = findViewById(R.id.colorOptionsGrid)
        timeText = findViewById(R.id.timeText)

        startGame()
    }

    private fun startGame() {
        score = 0
        startRound()
    }

    private fun startRound() {
        timer?.cancel()
        
        val correctColor = colors.random()
        targetColor.setBackgroundColor(correctColor)
        
        val options = colors.shuffled().take(4)
        val finalOptions = if (correctColor !in options) {
            options.toMutableList().apply { set(0, correctColor) }.shuffled()
        } else {
            options.shuffled()
        }
        
        colorOptionsGrid.removeAllViews()
        
        finalOptions.forEach { color ->
            val colorView = View(this).apply {
                layoutParams = GridLayout.LayoutParams().apply {
                    width = 150
                    height = 150
                    setMargins(8, 8, 8, 8)
                }
                setBackgroundColor(color)
                setOnClickListener {
                    if (color == correctColor) {
                        score++
                        Toast.makeText(this@Level6ColorMatchActivity, "¡Correcto!", Toast.LENGTH_SHORT).show()
                        
                        if (score >= 10) {
                            ProgressManager(this@Level6ColorMatchActivity).completeLevel(6)
                            Toast.makeText(this@Level6ColorMatchActivity, "¡Nivel completado!", Toast.LENGTH_LONG).show()
                            finish()
                        } else {
                            startRound()
                        }
                    } else {
                        Toast.makeText(this@Level6ColorMatchActivity, "¡Incorrecto!", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                }
            }
            colorOptionsGrid.addView(colorView)
        }
        
        var timeLeft = 30
        timer = object : CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeft--
                timeText.text = "Tiempo: $timeLeft"
            }

            override fun onFinish() {
                Toast.makeText(this@Level6ColorMatchActivity, "¡Tiempo agotado!", Toast.LENGTH_SHORT).show()
                finish()
            }
        }.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        timer?.cancel()
    }
}
