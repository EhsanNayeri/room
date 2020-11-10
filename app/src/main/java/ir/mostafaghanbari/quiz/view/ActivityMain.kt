package ir.mostafaghanbari.quiz.view

import android.content.Intent
import android.graphics.drawable.AnimatedVectorDrawable
import android.os.Build
import android.os.Bundle
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import ir.mostafaghanbari.quiz.R
import ir.mostafaghanbari.quiz.view.quiz.ActivityQuiz
import ir.mostafaghanbari.quiz.view.utils.MyActivity
import ir.mostafaghanbari.quiz.view.utils.startAnimatedVector
import kotlinx.android.synthetic.main.activity_main.*

class ActivityMain : MyActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startAnimatedVector(mainHead)

        setUpButtons()

    }

    private fun setUpButtons() {
        btnStartQuiz.setOnClickListener {
            startActivityQuiz()
        }
    }

    private fun startActivityQuiz() {
        val intent = Intent(this, ActivityQuiz::class.java)
        startActivity(intent)
    }

}