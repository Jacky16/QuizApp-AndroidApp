package com.example.quizapp

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import eu.tutorials.quizapp.Constants

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {
    private var mCurrentPosition: Int = 1
    private var mQuestionsList: ArrayList<Question>? = null
    private var mSelectedOptionPosition: Int = 0
    private var progressbar: ProgressBar? = null
    private var tvProgressbar: TextView? = null
    private var tvQuestion: TextView? = null
    private var ivImage: ImageView? = null

    private var tvOptionOne: TextView? = null
    private var tvOptionTwo: TextView? = null
    private var tvOptionThree: TextView? = null
    private var tvOptionFour: TextView? = null

    private var btnSubmit: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        progressbar = findViewById(R.id.progressBar)
        tvProgressbar = findViewById(R.id.tv_progressBar)
        tvQuestion = findViewById(R.id.tv_question)

        ivImage = findViewById(R.id.iv_image)

        tvOptionOne = findViewById(R.id.tv_option_one)
        tvOptionTwo = findViewById(R.id.tv_option_two)
        tvOptionThree = findViewById(R.id.tv_option_three)
        tvOptionFour = findViewById(R.id.tv_option_four)

        btnSubmit = findViewById(R.id.btn_submit)

        tvOptionOne?.setOnClickListener(this)
        tvOptionTwo?.setOnClickListener(this)
        tvOptionThree?.setOnClickListener(this)
        tvOptionFour?.setOnClickListener(this)
        btnSubmit?.setOnClickListener(this)

        mQuestionsList = Constants.getQuestions()

        setQuestion()
        defaultOptionView()
    }

    private fun setQuestion() {
        Log.i("Questions Size is", "${mQuestionsList?.size}")


        mCurrentPosition = 1
        val question: Question = mQuestionsList?.get(mCurrentPosition - 1) ?: return

        progressbar?.progress = mCurrentPosition
        tvProgressbar?.text = "$mCurrentPosition / ${progressbar?.max}"

        tvQuestion?.text = question.question

        ivImage?.setImageResource(question.image)

        //Set questions
        tvOptionOne?.text = question.optionOne
        tvOptionTwo?.text = question.optionTwo
        tvOptionThree?.text = question.optionThree
        tvOptionFour?.text = question.optionFour

        if(mCurrentPosition == mQuestionsList?.size){
            btnSubmit?.text = "FINISH"
        }else{
            btnSubmit?.text = "SUBMIT"
        }
    }
    private fun defaultOptionView(){
        val options = ArrayList<TextView>()

        tvOptionOne?.let{
            options.add(0,it)
        }
        tvOptionTwo?.let{
            options.add(1,it)
        }
        tvOptionThree?.let{
            options.add(2,it)
        }
        tvOptionFour?.let{
            options.add(3,it)
        }

        options.forEach {
            it.setTextColor(Color.parseColor("#7A8089"))
            it.typeface = Typeface.DEFAULT
            it.background= ContextCompat.getDrawable(
                this,R.drawable.default_option_border_bg
            )
        }
    }

    private fun selectOptionView(tv:TextView,selectedOptionNum:Int){
        defaultOptionView()
        mSelectedOptionPosition = selectedOptionNum

        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface,Typeface.BOLD)
        tv.background= ContextCompat.getDrawable(
            this,R.drawable.selected_option_border_bg
        )
    }
    override fun onClick(view: View?) {
        when(view?.id){
            R.id.tv_option_one -> {
                tvOptionOne?.let{
                    selectOptionView(it,1)
                }
            }
            R.id.tv_option_two -> {
                tvOptionTwo?.let{
                    selectOptionView(it,2)
                }
            }
            R.id.tv_option_three -> {
                tvOptionThree?.let{
                    selectOptionView(it,3)
                }
            }
            R.id.tv_option_four -> {
                tvOptionFour?.let{
                    selectOptionView(it,4)
                }
            }
            R.id.btn_submit ->{
                
            }
        }
    }
}