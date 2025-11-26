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

class Level4MemoryCardsActivity : AppCompatActivity() {

    private lateinit var cardsGrid: GridLayout
    private lateinit var pairsText: TextView
    private lateinit var resetButton: Button
    
    private val cards = mutableListOf<Button>()
    private val cardValues = listOf(1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6)
    private var flippedCards = mutableListOf<Int>()
    private var matchedPairs = 0
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level4)

        cardsGrid = findViewById(R.id.cardsGrid)
        pairsText = findViewById(R.id.pairsText)
        resetButton = findViewById(R.id.resetButton)

        setupCards()
        resetButton.setOnClickListener { setupCards() }
    }

    private fun setupCards() {
        cards.clear()
        cardsGrid.removeAllViews()
        flippedCards.clear()
        matchedPairs = 0
        pairsText.text = "Pares encontrados: 0/6"
        
        val shuffled = cardValues.shuffled()
        
        for (i in 0 until 12) {
            val card = Button(this).apply {
                layoutParams = GridLayout.LayoutParams().apply {
                    width = 100
                    height = 120
                    setMargins(4, 4, 4, 4)
                }
                text = "?"
                textSize = 24f
                gravity = Gravity.CENTER
                setBackgroundColor(getColor(R.color.blue))
                setTextColor(Color.WHITE)
                tag = shuffled[i]
                
                setOnClickListener { onCardClicked(cards.indexOf(this)) }
            }
            cards.add(card)
            cardsGrid.addView(card)
        }
    }

    private fun onCardClicked(position: Int) {
        val card = cards[position]
        
        if (flippedCards.size >= 2 || flippedCards.contains(position) || card.text != "?") {
            return
        }
        
        card.text = card.tag.toString()
        card.setBackgroundColor(getColor(R.color.green))
        flippedCards.add(position)
        
        if (flippedCards.size == 2) {
            val card1 = cards[flippedCards[0]]
            val card2 = cards[flippedCards[1]]
            
            if (card1.tag == card2.tag) {
                matchedPairs++
                pairsText.text = "Pares encontrados: $matchedPairs/6"
                flippedCards.clear()
                
                if (matchedPairs == 6) {
                    ProgressManager(this).completeLevel(4)
                    Toast.makeText(this, "¡Nivel completado!", Toast.LENGTH_LONG).show()
                    handler.postDelayed({ finish() }, 2000)
                }
            } else {
                handler.postDelayed({
                    card1.text = "?"
                    card1.setBackgroundColor(getColor(R.color.blue))
                    card2.text = "?"
                    card2.setBackgroundColor(getColor(R.color.blue))
                    flippedCards.clear()
                }, 1000)
            }
        }
    }
}
