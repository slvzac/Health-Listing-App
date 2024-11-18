package com.example.geoquiz

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val tvName: TextView = findViewById(R.id.tv_name)
        val tvQuestions: TextView = findViewById(R.id.tv_questions)
        val tvScore: TextView = findViewById(R.id.tv_score)
        val btnSummary: Button = findViewById(R.id.btn_summary)
        val btnComplete: Button = findViewById(R.id.btn_complete)

        tvName.text = intent.getStringExtra(QuestionStore.USERNAME)

        val answersCorrect = intent.getIntExtra(QuestionStore.ANSWER_CORRECT, 0)
        val totalQuestions = intent.getIntExtra(QuestionStore.QUESTIONS_TOTAL, 0)
        val CheatScore = intent.getIntExtra(QuestionStore.CHEAT_TOKENS, 0)

        // Total score of the player
        val scoreTotal = 100 * (answersCorrect.toDouble() / totalQuestions.toDouble())

        tvScore.text = "You scored ${"%.1f".format(scoreTotal)}%"

        Toast.makeText(this, "Score: ${"%.1f".format(scoreTotal)}%", Toast.LENGTH_SHORT).show()

        btnSummary.setOnClickListener {
            val intent = Intent(this, ResultSummary::class.java)
            intent.putExtra(QuestionStore.USERNAME, tvName.text.toString())
            intent.putExtra(QuestionStore.ANSWER_CORRECT, answersCorrect)
            intent.putExtra(QuestionStore.QUESTIONS_TOTAL, totalQuestions)
            intent.putExtra(QuestionStore.CHEAT_TOKENS, CheatScore)

            startActivity(intent)
        }

        btnComplete.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}