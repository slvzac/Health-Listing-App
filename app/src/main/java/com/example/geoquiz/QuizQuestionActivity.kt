package com.example.geoquiz

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class QuizQuestionActivity : AppCompatActivity(), View.OnClickListener {

    // Variables
    private var mCurrentPosition: Int = 1
    private var mQuestionStructureList: ArrayList<QuestionStructure>? = null
    private var mSelectedOptionPosition: Int = 0
    private var isSelectedAnswer: Boolean = false
    private var mUserName: String? = null
    private var mAnswerCorrect: Int = 0
    private var mCheatTokens: Int = 3

    // User Interface
    private var btnReset: Button? = null
    private var tvProgressBar: ProgressBar? = null
    private var tvProgress: TextView? = null
    private var tvQuestion: TextView? = null
    private var ivImage: ImageView? = null
    private var tvOptionOne: TextView? = null
    private var tvOptionTwo: TextView? = null
    private var tvOptionThree: TextView? = null
    private var tvOptionFour: TextView? = null
    private var btnSubmit: Button? = null
    private var btnBack: Button? = null
    private var tvCheatScore: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_question)

        // Sets up the initialization
        setPassingRetriveDataIntent()
        defaultViewModel()
        setViewModel()
        getQuestions()
        setQuestionList()
    }

    // Retrieves the data from the intent
    fun setPassingRetriveDataIntent() {
        mUserName = intent.getStringExtra(QuestionStore.USERNAME)
    }

    // Sets up the options
    private fun defaultViewModel() {
        val options = ArrayList<TextView>()
        tvOptionOne?.let {
            options.add(0, it)
        }
        tvOptionTwo?.let {
            options.add(1, it)
        }
        tvOptionThree?.let {
            options.add(2, it)
        }
        tvOptionFour?.let {
            options.add(3, it)
        }

        for (option in options) {
            option.setTextColor(Color.parseColor("#6F828A"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this, R.drawable.option_default_bg)
        }
    }

    // Sets up the view
    fun setViewModel() {
        btnReset = findViewById(R.id.btn_reset)
        tvProgressBar = findViewById(R.id.tv_progress_bar)
        tvProgress = findViewById(R.id.tv_progress)
        tvQuestion = findViewById(R.id.tv_question)
        ivImage = findViewById(R.id.iv_question)
        tvOptionOne = findViewById(R.id.tv_option_one)
        tvOptionTwo = findViewById(R.id.tv_option_two)
        tvOptionThree = findViewById(R.id.tv_option_three)
        tvOptionFour = findViewById(R.id.tv_option_four)
        btnSubmit = findViewById(R.id.btn_submit)
        btnBack = findViewById(R.id.btn_back)
        tvCheatScore = findViewById(R.id.tv_cheat)

        val tvQuesUser: TextView = findViewById(R.id.tv_quesname)
        tvQuesUser.text = intent.getStringExtra(QuestionStore.USERNAME)

        // Cheat Button
        val btnCheat: Button = findViewById(R.id.btn_cheat)
        btnCheat.text = "[$mCheatTokens] Cheat"
        btnCheat.isEnabled = mCheatTokens > 0

        btnCheat.setOnClickListener(this)
        tvOptionOne?.setOnClickListener(this)
        tvOptionTwo?.setOnClickListener(this)
        tvOptionThree?.setOnClickListener(this)
        tvOptionFour?.setOnClickListener(this)
        btnSubmit?.setOnClickListener(this)
        btnBack?.setOnClickListener(this)
        btnReset?.setOnClickListener(this)
    }

    // Retrieves the list of questions
    fun getQuestions() {
        mQuestionStructureList = QuestionStore.getQuestions()
    }

    // Sets the list of questions
    fun setQuestionList() {
        defaultViewModel()
        mQuestionStructureList?.let {
            val questionsList = it
            var currentPosition = mCurrentPosition
            val questionStructure: QuestionStructure = questionsList[currentPosition - 1]

            ivImage?.setImageResource(questionStructure.image)
            tvProgressBar?.progress = currentPosition

            tvProgress?.text = "$currentPosition/${questionsList.size}"
            tvQuestion?.text = questionStructure.question
            tvOptionOne?.text = questionStructure.optOne
            tvOptionTwo?.text = questionStructure.optTwo
            tvOptionThree?.text = questionStructure.optThree
            tvOptionFour?.text = questionStructure.optFour

            if (currentPosition > questionsList.size) {
                btnSubmit?.text = "FINISH"
            } else {
                btnSubmit?.text = "SUBMIT"
            }
        }
    }

    // Maintain appearance of options
    private fun answerView(answer: Int, drawableView: Int) {
        when (answer) {
            1 -> {
                tvOptionOne?.background = ContextCompat.getDrawable(this, drawableView)
            }

            2 -> {
                tvOptionTwo?.background = ContextCompat.getDrawable(this, drawableView)
            }

            3 -> {
                tvOptionThree?.background = ContextCompat.getDrawable(this, drawableView)
            }

            4 -> {
                tvOptionFour?.background = ContextCompat.getDrawable(this, drawableView)
            }
        }
    }

    // Handles the option selection
    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {
        defaultViewModel()
        mSelectedOptionPosition = selectedOptionNum
        isSelectedAnswer = true
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(this, R.drawable.option_select_bg)
    }

    // Handles the click events
    override fun onClick(view: View?) {
        val isAnswerNotSubmitDone: Boolean = (btnSubmit?.text == "SUBMIT")

        when (view?.id) {
            R.id.tv_option_one -> {
                if (isAnswerNotSubmitDone) {
                    tvOptionOne?.let {
                        selectedOptionView(it, 1)
                    }
                }
            }

            R.id.tv_option_two -> {
                if (isAnswerNotSubmitDone) {
                    tvOptionTwo?.let {
                        selectedOptionView(it, 2)
                    }
                }
            }

            R.id.tv_option_three -> {
                if (isAnswerNotSubmitDone) {
                    tvOptionThree?.let {
                        selectedOptionView(it, 3)
                    }
                }
            }

            R.id.tv_option_four -> {
                if (isAnswerNotSubmitDone) {
                    tvOptionFour?.let {
                        selectedOptionView(it, 4)
                    }
                }
            }

            R.id.btn_submit -> {
                btnSubmit?.let {
                    if (mSelectedOptionPosition == 0) {
                        Toast.makeText(this, "Please Select Answer", Toast.LENGTH_SHORT).show()
                    } else {
                        if (btnSubmit?.text == "BACK") {
                            if (mCurrentPosition > 1) {
                                mCurrentPosition--
                                setQuestionList()
                                btnSubmit?.text = "SUBMIT"
                                mSelectedOptionPosition = 0
                            } else {
                                Toast.makeText(this, "No Previous Question", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        } else {
                            val question = mQuestionStructureList?.get(mCurrentPosition - 1)
                            question?.let {
                                if (it.answerCorrect != mSelectedOptionPosition) {
                                    answerView(mSelectedOptionPosition, R.drawable.option_wrong_bg)
                                } else {
                                    mAnswerCorrect++
                                }

                                answerView(it.answerCorrect, R.drawable.option_correct_bg)

                                if (mCurrentPosition == mQuestionStructureList!!.size) {
                                    btnSubmit?.text = "FINISH"
                                    val intent = Intent(this, ResultActivity::class.java)
                                    intent.putExtra(QuestionStore.USERNAME, mUserName)
                                    intent.putExtra(QuestionStore.ANSWER_CORRECT, mAnswerCorrect)
                                    intent.putExtra(
                                        QuestionStore.QUESTIONS_TOTAL,
                                        mQuestionStructureList!!.size
                                    )
                                    intent.putExtra(QuestionStore.CHEAT_TOKENS, mCheatTokens)
                                    startActivity(intent)
                                    finish()
                                } else {
                                    mCurrentPosition++
                                    setQuestionList()
                                    btnSubmit?.text = "SUBMIT"
                                }
                                mSelectedOptionPosition = 0
                            }
                        }
                    }
                }
            }

            R.id.btn_back -> {
                if (mCurrentPosition > 1) {
                    mCurrentPosition--
                    setQuestionList()
                    mSelectedOptionPosition = 0
                } else {
                    Toast.makeText(this, "NO QUESTION AVAILABLE", Toast.LENGTH_SHORT).show()
                }
            }

            R.id.btn_reset -> {
                resetQuiz()
            }

            R.id.btn_cheat -> {
                if (mCheatTokens > 0) {
                    mCheatTokens--
                    updateCheatButton()

                    // Display the cheat
                    val question = mQuestionStructureList?.get(mCurrentPosition - 1)
                    question?.let {
                        answerView(it.answerCorrect, R.drawable.option_correct_bg)
                    }
                }
            }
        }
    }

    // Cheat Check
    private fun updateCheatButton() {
        val btnCheat: Button = findViewById(R.id.btn_cheat)
        btnCheat.text = "[$mCheatTokens] Cheat"
        btnCheat.isEnabled = mCheatTokens > 0
    }

    // Reset Quiz Check
    fun resetQuiz() {
        mCheatTokens = 3
        mCurrentPosition = 1
        mSelectedOptionPosition = 0
        mSelectedOptionPosition = 0
        mAnswerCorrect = 0
        isSelectedAnswer = false
        setQuestionList()
        defaultViewModel()
        updateCheatButton()
    }

}
