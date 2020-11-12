package ir.mostafaghanbari.quiz.view.utils

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ir.mostafaghanbari.quiz.App
import ir.mostafaghanbari.quiz.R
import ir.mostafaghanbari.quiz.view.quiz.ActivityQuiz

abstract class MyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.my_activity)
        App.ctx = this
    }

   open fun changeContent(fragment: MyFragment) {
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
            .replace(R.id.quizContent, fragment).commit()

    }

    override fun onResume() {
        App.ctx = this
        super.onResume()
    }

}