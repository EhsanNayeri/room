package ir.mostafaghanbari.quiz.view.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ir.mostafaghanbari.quiz.R
import ir.mostafaghanbari.quiz.view.utils.MyFragment
import ir.mostafaghanbari.quiz.view.utils.startAnimatedVector
import kotlinx.android.synthetic.main.activity_quiz.*
import kotlinx.android.synthetic.main.fragment_quiz.*

class FragmentQuiz:MyFragment() {

    private val act = ctx as ActivityQuiz

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_quiz, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpButton()

    }

    private fun setUpButton() {

        fabNextQuestion.setOnClickListener {
            act.changeContent(FragmentResult(), false)
        }

        fabPrevQuestion.setOnClickListener {
            startAnimatedVector(act.quizBack)
        }

    }

}