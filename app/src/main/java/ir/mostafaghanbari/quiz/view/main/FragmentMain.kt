package ir.mostafaghanbari.quiz.view.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ir.mostafaghanbari.quiz.R
import ir.mostafaghanbari.quiz.view.main.history.FragmentHistory
import ir.mostafaghanbari.quiz.view.quiz.ActivityQuiz
import ir.mostafaghanbari.quiz.view.utils.MyFragment
import ir.mostafaghanbari.quiz.view.utils.startAnimatedVector
import kotlinx.android.synthetic.main.fragment_main.*

class FragmentMain : MyFragment() {

    private val act = ctx as ActivityMain

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_main, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        startAnimatedVector(mainHead)

        setUpButtons()
    }

    private fun setUpButtons() {
        btnStartQuiz.setOnClickListener {
            startActivityQuiz()
        }
        btnQuizHistory.setOnClickListener {
            act.changeContent(FragmentHistory())
        }
    }

    private fun startActivityQuiz() {
        val intent = Intent(ctx, ActivityQuiz::class.java)
        startActivity(intent)
    }

}