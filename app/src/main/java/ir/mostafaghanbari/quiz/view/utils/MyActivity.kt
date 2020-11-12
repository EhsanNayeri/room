package ir.mostafaghanbari.quiz.view.utils

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ir.mostafaghanbari.quiz.App
import ir.mostafaghanbari.quiz.R

abstract class MyActivity : AppCompatActivity() {

    private var activityState = "pause"
    private var storedFragment: MyFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.my_activity)
        App.ctx = this
    }

    open fun changeContent(fragment: MyFragment) {
        if (activityState != "alive") {
            storedFragment = fragment
            return
        }
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
            .replace(R.id.myActivityContent, fragment).commit()

    }

    override fun onResume() {
        super.onResume()
        App.ctx = this
        activityState = "alive"
        if (storedFragment != null) {
            changeContent(storedFragment!!)
            storedFragment = null
        }
    }

    override fun onPause() {
        super.onPause()
        activityState = "pause"
    }

}