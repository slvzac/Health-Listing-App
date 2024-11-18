package com.example.geoquiz

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etName: EditText = findViewById(R.id.et_name)
        val btnStart: Button = findViewById(R.id.btn_start)

        // Start the quiz
        btnStart.setOnClickListener {
            if (etName.text.isNotEmpty()) {
                val intent = Intent(this, QuizQuestionActivity::class.java)
                intent.putExtra(QuestionStore.USERNAME, etName.text.toString())

                startActivity(intent)
                finish()

            } else {
                Toast.makeText(this, "Please enter your name", Toast.LENGTH_LONG).show()
            }
        }
    }
}