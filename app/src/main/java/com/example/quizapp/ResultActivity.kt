package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import eu.tutorials.quizapp.Constants

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val tvName = findViewById<TextView>(R.id.tv_name)
        val tvScore = findViewById<TextView>(R.id.tv_score)
        val btnFinish = findViewById<Button>(R.id.btn_finish)

        val mUserName = intent.getStringExtra(Constants.USER_NAME)
        val mCorrectAnswers = intent.getStringExtra(Constants.CORRECT_ANSWER)
        val mTotalQuestions = intent.getStringExtra(Constants.TOTAL_QUESTIONS)

        tvName.text = mUserName
        tvScore.text = "Your Score is $mCorrectAnswers out of $mTotalQuestions"
        btnFinish.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }
    }
}