package ir.mostafaghanbari.quiz.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ir.mostafaghanbari.quiz.R

class ActivityMain :AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setViewDrawedListener()

    }

    private fun setViewDrawedListener() {

    }
}