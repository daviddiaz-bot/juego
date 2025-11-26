package com.antigravity.multigame.levels

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.antigravity.multigame.ProgressManager
import com.antigravity.multigame.R

class Level7QuizActivity : AppCompatActivity() {

    private lateinit var questionCounter: TextView
    private lateinit var questionText: TextView
    private lateinit var answer1Button: Button
    private lateinit var answer2Button: Button
    private lateinit var answer3Button: Button
    private lateinit var answer4Button: Button
    private lateinit var nextButton: Button
    
    private var currentQuestion = 0
    private var correctAnswers = 0
    
    private val questions = listOf(
        Question("¿Cuál es la capital de Francia?", 
            listOf("Londres", "Berlín", "París", "Madrid"), 2),
        Question("¿Cuántos continentes hay?", 
            listOf("5", "6", "7", "8"), 2),
        Question("¿Quién pintó La Mona Lisa?", 
            listOf("Van Gogh", "Picasso", "Da Vinci", "Dalí"), 2),
        Question("¿Cuál es el planeta más grande?", 
            listOf("Marte", "Júpiter", "Saturno", "Tierra"), 1),
        Question("¿En qué año llegó el hombre a la Luna?", 
            listOf("1965", "1969", "1972", "1975"), 1)
    )

    data class Question(val text: String, val answers: List<String>, val correctIndex: Int)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level7)

        questionCounter = findViewById(R.id.questionCounter)
        questionText = findViewById(R.id.questionText)
        answer1Button = findViewById(R.id.answer1Button)
        answer2Button = findViewById(R.id.answer2Button)
        answer3Button = findViewById(R.id.answer3Button)
        answer4Button = findViewById(R.id.answer4Button)
        nextButton = findViewById(R.id.nextButton)

        val answerButtons = listOf(answer1Button, answer2Button, answer3Button, answer4Button)
        
        answerButtons.forEachIndexed { index, button ->
            button.setOnClickListener { checkAnswer(index) }
        }
        
        nextButton.setOnClickListener { nextQuestion() }
        
        showQuestion()
    }

    private fun showQuestion() {
        if (currentQuestion >= questions.size) {
            if (correctAnswers >= 3) {
                ProgressManager(this).completeLevel(7)
                Toast.makeText(this, "¡Nivel completado! $correctAnswers/5 correctas", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Necesitas al menos 3 respuestas correctas. Obtuviste $correctAnswers/5", Toast.LENGTH_LONG).show()
            }
            finish()
            return
        }
        
        val question = questions[currentQuestion]
        questionCounter.text = "Pregunta ${currentQuestion + 1}/5"
        questionText.text = question.text
        answer1Button.text = question.answers[0]
        answer2Button.text = question.answers[1]
        answer3Button.text = question.answers[2]
        answer4Button.text = question.answers[3]
        
        answer1Button.isEnabled = true
        answer2Button.isEnabled = true
        answer3Button.isEnabled = true
        answer4Button.isEnabled = true
        
        answer1Button.setBackgroundColor(getColor(R.color.purple_500))
        answer2Button.setBackgroundColor(getColor(R.color.purple_500))
        answer3Button.setBackgroundColor(getColor(R.color.purple_500))
        answer4Button.setBackgroundColor(getColor(R.color.purple_500))
        
        nextButton.visibility = View.GONE
    }

    private fun checkAnswer(selectedIndex: Int) {
        val question = questions[currentQuestion]
        val buttons = listOf(answer1Button, answer2Button, answer3Button, answer4Button)
        
        buttons.forEach { it.isEnabled = false }
        
        if (selectedIndex == question.correctIndex) {
            buttons[selectedIndex].setBackgroundColor(Color.GREEN)
            correctAnswers++
            Toast.makeText(this, "¡Correcto!", Toast.LENGTH_SHORT).show()
        } else {
            buttons[selectedIndex].setBackgroundColor(Color.RED)
            buttons[question.correctIndex].setBackgroundColor(Color.GREEN)
            Toast.makeText(this, "Incorrecto", Toast.LENGTH_SHORT).show()
        }
        
        nextButton.visibility = View.VISIBLE
    }

    private fun nextQuestion() {
        currentQuestion++
        showQuestion()
    }
}
