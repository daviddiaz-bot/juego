package com.antigravity.multigame

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.antigravity.multigame.levels.*

class MainActivity : AppCompatActivity() {

    private lateinit var progressManager: ProgressManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressManager = ProgressManager(this)
        
        setupLevelCards()
    }

    override fun onResume() {
        super.onResume()
        updateLevelStates()
    }

    private fun setupLevelCards() {
        val levelCards = listOf(
            Triple(findViewById<CardView>(R.id.level1Card), findViewById<TextView>(R.id.level1Text), Level1SimonActivity::class.java),
            Triple(findViewById<CardView>(R.id.level2Card), findViewById<TextView>(R.id.level2Text), Level2CatchStarsActivity::class.java),
            Triple(findViewById<CardView>(R.id.level3Card), findViewById<TextView>(R.id.level3Text), Level3SlidePuzzleActivity::class.java),
            Triple(findViewById<CardView>(R.id.level4Card), findViewById<TextView>(R.id.level4Text), Level4MemoryCardsActivity::class.java),
            Triple(findViewById<CardView>(R.id.level5Card), findViewById<TextView>(R.id.level5Text), Level5MazeActivity::class.java),
            Triple(findViewById<CardView>(R.id.level6Card), findViewById<TextView>(R.id.level6Text), Level6ColorMatchActivity::class.java),
            Triple(findViewById<CardView>(R.id.level7Card), findViewById<TextView>(R.id.level7Text), Level7QuizActivity::class.java),
            Triple(findViewById<CardView>(R.id.level8Card), findViewById<TextView>(R.id.level8Text), Level8TapTimingActivity::class.java),
            Triple(findViewById<CardView>(R.id.level9Card), findViewById<TextView>(R.id.level9Text), Level9AvoidObstaclesActivity::class.java),
            Triple(findViewById<CardView>(R.id.level10Card), findViewById<TextView>(R.id.level10Text), Level10PatternLockActivity::class.java)
        )

        levelCards.forEachIndexed { index, (card, textView, activityClass) ->
            val levelNumber = index + 1
            card.setOnClickListener {
                if (progressManager.isLevelUnlocked(levelNumber)) {
                    startActivity(Intent(this, activityClass))
                }
            }
        }
    }

    private fun updateLevelStates() {
        val levelCards = listOf(
            findViewById<CardView>(R.id.level1Card) to findViewById<TextView>(R.id.level1Text),
            findViewById<CardView>(R.id.level2Card) to findViewById<TextView>(R.id.level2Text),
            findViewById<CardView>(R.id.level3Card) to findViewById<TextView>(R.id.level3Text),
            findViewById<CardView>(R.id.level4Card) to findViewById<TextView>(R.id.level4Text),
            findViewById<CardView>(R.id.level5Card) to findViewById<TextView>(R.id.level5Text),
            findViewById<CardView>(R.id.level6Card) to findViewById<TextView>(R.id.level6Text),
            findViewById<CardView>(R.id.level7Card) to findViewById<TextView>(R.id.level7Text),
            findViewById<CardView>(R.id.level8Card) to findViewById<TextView>(R.id.level8Text),
            findViewById<CardView>(R.id.level9Card) to findViewById<TextView>(R.id.level9Text),
            findViewById<CardView>(R.id.level10Card) to findViewById<TextView>(R.id.level10Text)
        )

        levelCards.forEachIndexed { index, (card, textView) ->
            val levelNumber = index + 1
            when {
                progressManager.isLevelCompleted(levelNumber) -> {
                    textView.setBackgroundColor(getColor(R.color.level_completed))
                    card.alpha = 1.0f
                }
                progressManager.isLevelUnlocked(levelNumber) -> {
                    textView.setBackgroundColor(getColor(R.color.level_unlocked))
                    card.alpha = 1.0f
                }
                else -> {
                    textView.setBackgroundColor(getColor(R.color.level_locked))
                    card.alpha = 0.5f
                }
            }
        }
    }
}

class ProgressManager(context: Context) {
    private val prefs = context.getSharedPreferences("game_progress", Context.MODE_PRIVATE)

    fun isLevelUnlocked(level: Int): Boolean {
        if (level == 1) return true
        return isLevelCompleted(level - 1)
    }

    fun isLevelCompleted(level: Int): Boolean {
        return prefs.getBoolean("level_${level}_completed", false)
    }

    fun completeLevel(level: Int) {
        prefs.edit().putBoolean("level_${level}_completed", true).apply()
    }

    fun resetProgress() {
        prefs.edit().clear().apply()
    }
}
