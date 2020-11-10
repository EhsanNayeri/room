package ir.mostafaghanbari.quiz.view.utils

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ir.mostafaghanbari.quiz.App
import ir.mostafaghanbari.quiz.view.quiz.ActivityQuiz

abstract class MyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        App.ctx = this
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        App.ctx = this
        super.onResume()
    }

}