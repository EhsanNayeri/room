package ir.mostafaghanbari.quiz.view.quiz

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import ir.mostafaghanbari.quiz.R
import ir.mostafaghanbari.quiz.model.entities.AnswerModel
import ir.mostafaghanbari.quiz.presenter.QuestionPresenter
import ir.mostafaghanbari.quiz.view.utils.MyFragment
import ir.mostafaghanbari.quiz.view.utils.startAnimatedVector
import ir.mostafaghanbari.quiz.view.utils.toast
import kotlinx.android.synthetic.main.activity_quiz.*
import kotlinx.android.synthetic.main.fragment_quiz.*
import java.lang.Math.abs

class FragmentQuiz : MyFragment() {

    private val act = ctx as ActivityQuiz
    private var questionNumber = 0 //array index
    private var questionCount = 0
    private var answerCount = 0
    private val timeInSecond: Long = 5 * 60
    private lateinit var timer: CountDownTimer

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_quiz, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        act.quizFinished = false

        setUpButton()

        getQuestions()

        setUpTimer()

    }

    private fun setUpTimer() {
        timerProgress.max = timeInSecond.toInt()
        timer = object : CountDownTimer(timeInSecond * 1000, 1000) {
            override fun onFinish() {
                toast("اتمام زمان آزمون")
                finishQuiz()
            }

            override fun onTick(mili: Long) {
                val minute = mili / 60000
                val second = abs((mili - minute * 60000) / 1000)
                txtTimer.text = "$second : $minute"
                timerProgress.progress = mili.toInt() / 1000
            }
        }.start()
    }

    private fun finishQuiz(ConfirmDialog: Boolean = false) {
        if (ConfirmDialog) {
            showConfirmFinishDialog()
            return
        }
        timer.cancel()
        act.quizFinished = true
        act.changeContent(FragmentResult(), true)
    }

    private fun showConfirmFinishDialog() {
        MaterialAlertDialogBuilder(ctx)
            .setTitle("پایان آزمون")
            .setMessage("آیا از اتمام آزمون مطمئن هستید؟")
            .setPositiveButton("بله") { d, w ->
                finishQuiz()
            }
            .setNegativeButton("خیر", null)
            .show()
    }

    private fun getQuestions() {
        act.questions = QuestionPresenter().getQuestions()
        questionCount = act.questions.size

        setQuestion()
    }

    private fun setQuestion() {//questionNumber is array index

        val question = act.questions[questionNumber]

        txtQuestion.text = question.question.text

        rbAnswer1.apply {
            text = question.answers[0].text
            isChecked = question.answers[0].choosed
            setOnClickListener {
                startAnimatedVector(act.quizBack)
                checkAnswerCount(question.answers)
                question.answers[0].choosed = true
            }
        }
        rbAnswer2.apply {
            text = question.answers[1].text
            isChecked = question.answers[1].choosed
            setOnClickListener {
                startAnimatedVector(act.quizBack)
                checkAnswerCount(question.answers)
                question.answers[1].choosed = true
            }
        }
        rbAnswer3.apply {
            text = question.answers[2].text
            isChecked = question.answers[2].choosed
            setOnClickListener {
                startAnimatedVector(act.quizBack)
                checkAnswerCount(question.answers)
                question.answers[2].choosed = true
            }
        }
        rbAnswer4.apply {
            text = question.answers[3].text
            isChecked = question.answers[3].choosed
            setOnClickListener {
                startAnimatedVector(act.quizBack)
                checkAnswerCount(question.answers)
                question.answers[3].choosed = true
            }
        }

        txtQuestionNumber.text = "${questionNumber + 1} از $questionCount"

    }

    private fun checkAnswerCount(answers: List<AnswerModel>) {
        answers.forEach { answer ->
            if (answer.choosed)
                return
        }

        ++answerCount

        fabAnswerCounter.text = answerCount.toString()

    }

    private fun setUpButton() {

        fabNextQuestion.setOnClickListener {
            checkHaveNext()
        }

        fabPrevQuestion.setOnClickListener {
            checkHavePrev()
        }

        fabFinishQuiz.setOnClickListener {
            finishQuiz(true)
        }

        fabCloseQuiz.setOnClickListener {
            act.onBackPressed()
        }

    }

    private fun checkHaveNext() {
        if (questionNumber + 1 < questionCount) {
            ++questionNumber
            setQuestion()
        }
        if (questionNumber + 1 == questionCount) {
            fabFinishQuiz.visibility = View.VISIBLE
            fabNextQuestion.visibility = View.GONE

            fabPrevQuestion.visibility = View.VISIBLE
            fabCloseQuiz.visibility = View.GONE
        }
    }

    private fun checkHavePrev() {
        if (questionNumber > 0) {
            --questionNumber
            setQuestion()
        }
        if (questionNumber == 0) {
            fabFinishQuiz.visibility = View.GONE
            fabNextQuestion.visibility = View.VISIBLE

            fabPrevQuestion.visibility = View.GONE
            fabCloseQuiz.visibility = View.VISIBLE
        }
    }

}