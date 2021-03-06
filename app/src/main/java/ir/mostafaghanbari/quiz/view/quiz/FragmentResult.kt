package ir.mostafaghanbari.quiz.view.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import ir.mostafaghanbari.quiz.R
import ir.mostafaghanbari.quiz.model.entities.QuizHistory
import ir.mostafaghanbari.quiz.presenter.UserPresenter
import ir.mostafaghanbari.quiz.view.utils.MyFragment
import ir.mostafaghanbari.quiz.view.utils.getColorBaseScore
import kotlinx.android.synthetic.main.fragment_result.*

class FragmentResult : MyFragment() {

    private val act = ctx as ActivityQuiz
    private var totalScore = 0
    private var answerCounter = 0
    private var trueCounter = 0
    private var questionCount = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_result, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkScore()

        storeResult()

        btnResultConfirm.setOnClickListener {
            act.onBackPressed()
        }

    }

    private fun storeResult() {
        UserPresenter().storeUserHistory(
            act.user,
            QuizHistory(questionCount, answerCounter, trueCounter, totalScore)
        )
    }

    private fun checkScore() {
        act.apply {
            questionCount = questions.size
            questions.forEach { question ->
                question.answers.forEach { answer ->
                    if (answer.choosed)
                        ++answerCounter
                    if (answer.itsAnswer && answer.choosed)
                        ++trueCounter

                }
            }
        }

        totalScore = ((trueCounter / questionCount.toFloat()) * 100).toInt()

        setUpView()
    }

    private fun setUpView() {
        resultScore.text = totalScore.toString()
        resultCount.text = "${resultCount.text} $questionCount"
        resultAnswerNumber.text = "${resultAnswerNumber.text} $answerCounter"
        resultTrueNumber.text = "${resultTrueNumber.text} $trueCounter"
        resultMistakeNumber.text = "${resultMistakeNumber.text} ${answerCounter - trueCounter}"

        setColor()

    }

    private fun setColor() {
        when (totalScore) {
            in 0..50 -> {
                SVResult.setBackgroundColor(ContextCompat.getColor(ctx, R.color.redLight))
                resultScore.setTextColor(getColorBaseScore(totalScore))
                resultScorePercent.setTextColor(getColorBaseScore(totalScore))
            }
            in 50..70 -> {
                SVResult.setBackgroundColor(ContextCompat.getColor(ctx, R.color.orangeLight))
                resultScore.setTextColor(getColorBaseScore(totalScore))
                resultScorePercent.setTextColor(getColorBaseScore(totalScore))
            }
            else -> {
                SVResult.setBackgroundColor(ContextCompat.getColor(ctx, R.color.greenLight))
                resultScore.setTextColor(getColorBaseScore(totalScore))
                resultScorePercent.setTextColor(getColorBaseScore(totalScore))
            }
        }
    }

}