package com.antigravity.multigame.levels

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.widget.Button
import android.widget.GridLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.antigravity.multigame.ProgressManager
import com.antigravity.multigame.R

class Level10PatternLockActivity : AppCompatActivity() {

    private lateinit var patternGrid: GridLayout
    private lateinit var instructionText: TextView
    private lateinit var startButton: Button
    
    private val buttons = mutableListOf<Button>()
    private val pattern = mutableListOf<Int>()
    private val userPattern = mutableListOf<Int>()
    private var isShowingPattern = false
    private var isRecording = false
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level10)

        patternGrid = findViewById(R.id.patternGrid)
        instructionText = findViewById(R.id.instructionText)
        startButton = findViewById(R.id.startButton)

        setupGrid()
        startButton.setOnClickListener { showPattern() }
    }

    private fun setupGrid() {
        buttons.clear()
        patternGrid.removeAllViews()
        
        for (i in 0 until 9) {
            val button = Button(this).apply {
                layoutParams = GridLayout.LayoutParams().apply {
                    width = 90
                    height = 90
                    setMargins(4, 4, 4, 4)
                }
                text = ""
                setBackgroundColor(getColor(R.color.gray_light))
                setOnClickListener { 
                    if (isRecording) {
                        onButtonPressed(buttons.indexOf(this))
                    }
                }
            }
            buttons.add(button)
            patternGrid.addView(button)
        }
    }

    private fun showPattern() {
        if (isShowingPattern || isRecording) return
        
        isShowingPattern = true
        startButton.isEnabled = false
        userPattern.clear()
        
        pattern.clear()
        val patternLength = 4 + (0..2).random()
        while (pattern.size < patternLength) {
            val num = (0..8).random()
            if (!pattern.contains(num)) {
                pattern.add(num)
            }
        }
        
        instructionText.text = "Observa el patrón..."
        
        var delay = 0L
        pattern.forEach { index ->
            handler.postDelayed({
                buttons[index].setBackgroundColor(Color.YELLOW)
            }, delay)
            
            handler.postDelayed({
                buttons[index].setBackgroundColor(getColor(R.color.gray_light))
            }, delay + 500)
            
            delay += 800
        }
        
        handler.postDelayed({
            isShowingPattern = false
            isRecording = true
            instructionText.text = "Ahora repite el patrón"
            startButton.text = "Reiniciar"
            startButton.isEnabled = true
        }, delay + 500)
    }

    private fun onButtonPressed(index: Int) {
        if (!isRecording) return
        
        buttons[index].setBackgroundColor(Color.GREEN)
        userPattern.add(index)
        
        if (userPattern[userPattern.size - 1] != pattern[userPattern.size - 1]) {
            Toast.makeText(this, "¡Patrón incorrecto!", Toast.LENGTH_SHORT).show()
            resetButtons()
            isRecording = false
            startButton.text = "Mostrar Patrón"
            return
        }
        
        if (userPattern.size == pattern.size) {
            ProgressManager(this).completeLevel(10)
            Toast.makeText(this, "¡Nivel completado!", Toast.LENGTH_LONG).show()
            handler.postDelayed({ finish() }, 2000)
        }
    }

    private fun resetButtons() {
        buttons.forEach { it.setBackgroundColor(getColor(R.color.gray_light)) }
        userPattern.clear()
    }
}
