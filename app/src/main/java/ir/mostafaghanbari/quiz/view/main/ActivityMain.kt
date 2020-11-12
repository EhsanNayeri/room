package ir.mostafaghanbari.quiz.view.main

import android.os.Bundle
import ir.mostafaghanbari.quiz.view.utils.MyActivity
import ir.mostafaghanbari.quiz.view.utils.MyFragment

class ActivityMain : MyActivity() {

    private var mainIsUp = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        changeContent(FragmentMain())

    }

    override fun changeContent(fragment: MyFragment) {
        mainIsUp = fragment.javaClass.name.contains("FragmentMain")
        super.changeContent(fragment)
    }

    override fun onBackPressed() {
        if (mainIsUp) {
            super.onBackPressed()
            this.finish()
        } else {
            changeContent(FragmentMain())
        }

    }

}