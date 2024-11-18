package com.example.geoquiz

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultSummary : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_summary)

        val tvName: TextView = findViewById(R.id.tv_name)
        val tvQuestions: TextView = findViewById(R.id.tv_questions)
        val tvScore: TextView = findViewById(R.id.tv_score)
        val tvCheatScore: TextView = findViewById(R.id.tv_cheat)
        val btnReturn: Button = findViewById(R.id.btn_return)

        tvName.text = intent.getStringExtra(QuestionStore.USERNAME)
        val answersCorrect = intent.getIntExtra(QuestionStore.ANSWER_CORRECT, 0)
        val totalQuestions = intent.getIntExtra(QuestionStore.QUESTIONS_TOTAL, 0)
        val cheatScore = intent.getIntExtra(QuestionStore.CHEAT_TOKENS, 0)

        // Total score of the player
        val scoreTotal = 100 * (answersCorrect.toDouble() / totalQuestions.toDouble())

        tvQuestions.text = "You Scored $answersCorrect out of $totalQuestions"

        tvScore.text = "That makes up ${"%.1f".format(scoreTotal)}%"

        tvCheatScore.text = "Cheat Tokens Remaining: $cheatScore"

        btnReturn.setOnClickListener {
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra(QuestionStore.USERNAME, tvName.text.toString())
            intent.putExtra(QuestionStore.ANSWER_CORRECT, answersCorrect)
            intent.putExtra(QuestionStore.QUESTIONS_TOTAL, totalQuestions)
            intent.putExtra(QuestionStore.CHEAT_TOKENS, cheatScore)
            startActivity(intent)
        }
    }
}
