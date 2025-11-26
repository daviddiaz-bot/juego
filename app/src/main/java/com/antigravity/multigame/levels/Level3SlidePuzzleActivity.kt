package com.antigravity.multigame.levels

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.GridLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.antigravity.multigame.ProgressManager
import com.antigravity.multigame.R

class Level3SlidePuzzleActivity : AppCompatActivity() {

    private lateinit var puzzleGrid: GridLayout
    private lateinit var movesText: TextView
    private lateinit var shuffleButton: Button
    
    private val tiles = mutableListOf<Button>()
    private var emptyPosition = 8
    private var moves = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level3)

        puzzleGrid = findViewById(R.id.puzzleGrid)
        movesText = findViewById(R.id.movesText)
        shuffleButton = findViewById(R.id.shuffleButton)

        setupPuzzle()
        shuffleButton.setOnClickListener { shufflePuzzle() }
    }

    private fun setupPuzzle() {
        tiles.clear()
        puzzleGrid.removeAllViews()
        
        for (i in 0 until 9) {
            val button = Button(this).apply {
                layoutParams = GridLayout.LayoutParams().apply {
                    width = 100
                    height = 100
                    setMargins(2, 2, 2, 2)
                }
                textSize = 24f
                gravity = Gravity.CENTER
                
                if (i < 8) {
                    text = (i + 1).toString()
                    setBackgroundColor(getColor(R.color.purple_500))
                    setTextColor(Color.WHITE)
                    setOnClickListener { moveTile(tiles.indexOf(this)) }
                } else {
                    text = ""
                    setBackgroundColor(Color.TRANSPARENT)
                    isEnabled = false
                }
            }
            tiles.add(button)
            puzzleGrid.addView(button)
        }
        emptyPosition = 8
        moves = 0
        movesText.text = "Movimientos: $moves"
    }

    private fun shufflePuzzle() {
        repeat(50) {
            val neighbors = getNeighbors(emptyPosition)
            if (neighbors.isNotEmpty()) {
                swapTiles(emptyPosition, neighbors.random())
            }
        }
        moves = 0
        movesText.text = "Movimientos: $moves"
    }

    private fun moveTile(position: Int) {
        if (getNeighbors(emptyPosition).contains(position)) {
            swapTiles(position, emptyPosition)
            moves++
            movesText.text = "Movimientos: $moves"
            
            if (isSolved()) {
                ProgressManager(this).completeLevel(3)
                Toast.makeText(this, "¡Nivel completado en $moves movimientos!", Toast.LENGTH_LONG).show()
                finish()
            }
        }
    }

    private fun swapTiles(pos1: Int, pos2: Int) {
        val temp = tiles[pos1].text
        tiles[pos1].text = tiles[pos2].text
        tiles[pos2].text = temp
        
        val tempColor = tiles[pos1].isEnabled
        tiles[pos1].isEnabled = tiles[pos2].isEnabled
        tiles[pos2].isEnabled = tempColor
        
        if (tiles[pos1].isEnabled) {
            tiles[pos1].setBackgroundColor(getColor(R.color.purple_500))
        } else {
            tiles[pos1].setBackgroundColor(Color.TRANSPARENT)
        }
        
        if (tiles[pos2].isEnabled) {
            tiles[pos2].setBackgroundColor(getColor(R.color.purple_500))
        } else {
            tiles[pos2].setBackgroundColor(Color.TRANSPARENT)
        }
        
        emptyPosition = if (pos1 == emptyPosition) pos2 else pos1
    }

    private fun getNeighbors(position: Int): List<Int> {
        val neighbors = mutableListOf<Int>()
        val row = position / 3
        val col = position % 3
        
        if (row > 0) neighbors.add(position - 3) // arriba
        if (row < 2) neighbors.add(position + 3) // abajo
        if (col > 0) neighbors.add(position - 1) // izquierda
        if (col < 2) neighbors.add(position + 1) // derecha
        
        return neighbors
    }

    private fun isSolved(): Boolean {
        for (i in 0 until 8) {
            if (tiles[i].text.toString() != (i + 1).toString()) {
                return false
            }
        }
        return true
    }
}
