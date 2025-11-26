package com.antigravity.multigame.levels

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.antigravity.multigame.ProgressManager
import com.antigravity.multigame.R

class Level5MazeActivity : AppCompatActivity() {

    private lateinit var mazeContainer: FrameLayout
    private lateinit var upButton: Button
    private lateinit var downButton: Button
    private lateinit var leftButton: Button
    private lateinit var rightButton: Button
    
    private var playerX = 0
    private var playerY = 0
    private val maze = arrayOf(
        intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        intArrayOf(0, 1, 1, 1, 0, 1, 1, 1, 1, 0),
        intArrayOf(0, 1, 0, 1, 0, 1, 0, 0, 1, 0),
        intArrayOf(0, 1, 0, 1, 1, 1, 1, 0, 1, 0),
        intArrayOf(0, 1, 0, 0, 0, 0, 1, 0, 1, 0),
        intArrayOf(0, 1, 1, 1, 1, 0, 1, 1, 1, 0),
        intArrayOf(0, 0, 0, 0, 1, 0, 0, 0, 1, 0),
        intArrayOf(0, 1, 1, 1, 1, 1, 1, 0, 1, 0),
        intArrayOf(0, 1, 0, 0, 0, 0, 1, 1, 1, 0),
        intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
    )
    private val goalX = 8
    private val goalY = 8

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level5)

        mazeContainer = findViewById(R.id.mazeContainer)
        upButton = findViewById(R.id.upButton)
        downButton = findViewById(R.id.downButton)
        leftButton = findViewById(R.id.leftButton)
        rightButton = findViewById(R.id.rightButton)

        playerX = 1
        playerY = 1
        
        val mazeView = MazeView(this, maze, playerX, playerY, goalX, goalY)
        mazeContainer.addView(mazeView)

        upButton.setOnClickListener { movePlayer(0, -1, mazeView) }
        downButton.setOnClickListener { movePlayer(0, 1, mazeView) }
        leftButton.setOnClickListener { movePlayer(-1, 0, mazeView) }
        rightButton.setOnClickListener { movePlayer(1, 0, mazeView) }
    }

    private fun movePlayer(dx: Int, dy: Int, mazeView: MazeView) {
        val newX = playerX + dx
        val newY = playerY + dy
        
        if (newX >= 0 && newX < maze[0].size && newY >= 0 && newY < maze.size && maze[newY][newX] == 1) {
            playerX = newX
            playerY = newY
            mazeView.updatePlayer(playerX, playerY)
            
            if (playerX == goalX && playerY == goalY) {
                ProgressManager(this).completeLevel(5)
                Toast.makeText(this, "¡Nivel completado!", Toast.LENGTH_LONG).show()
                finish()
            }
        }
    }

    class MazeView(context: Context, private val maze: Array<IntArray>, 
                   private var playerX: Int, private var playerY: Int,
                   private val goalX: Int, private val goalY: Int) : View(context) {
        
        private val wallPaint = Paint().apply { color = Color.BLACK }
        private val pathPaint = Paint().apply { color = Color.WHITE }
        private val playerPaint = Paint().apply { color = Color.BLUE }
        private val goalPaint = Paint().apply { color = Color.GREEN }
        private var cellSize = 0f

        fun updatePlayer(x: Int, y: Int) {
            playerX = x
            playerY = y
            invalidate()
        }

        override fun onDraw(canvas: Canvas) {
            super.onDraw(canvas)
            
            cellSize = Math.min(width.toFloat() / maze[0].size, height.toFloat() / maze.size)
            
            for (y in maze.indices) {
                for (x in maze[y].indices) {
                    val left = x * cellSize
                    val top = y * cellSize
                    val right = left + cellSize
                    val bottom = top + cellSize
                    
                    canvas.drawRect(left, top, right, bottom, 
                        if (maze[y][x] == 0) wallPaint else pathPaint)
                }
            }
            
            canvas.drawCircle(
                goalX * cellSize + cellSize / 2,
                goalY * cellSize + cellSize / 2,
                cellSize / 3,
                goalPaint
            )
            
            canvas.drawCircle(
                playerX * cellSize + cellSize / 2,
                playerY * cellSize + cellSize / 2,
                cellSize / 3,
                playerPaint
            )
        }
    }
}
