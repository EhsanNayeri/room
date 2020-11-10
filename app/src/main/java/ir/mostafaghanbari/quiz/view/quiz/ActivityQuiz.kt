package ir.mostafaghanbari.quiz.view.quiz

import android.os.Bundle
import android.os.Handler
import androidx.core.content.PermissionChecker
import androidx.fragment.app.Fragment
import ir.mostafaghanbari.quiz.R
import ir.mostafaghanbari.quiz.view.utils.MyActivity
import ir.mostafaghanbari.quiz.view.utils.MyFragment
import ir.mostafaghanbari.quiz.view.utils.startAnimatedVector
import ir.mostafaghanbari.quiz.view.utils.stopAnimatedVector
import kotlinx.android.synthetic.main.activity_quiz.*

class ActivityQuiz : MyActivity() {

    lateinit var permissionResult: (granted: Boolean) -> Unit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        changeContent(FragmentUserInfo())

    }

    fun changeContent(fragment: MyFragment, animateBack: Boolean = false) {

        if (animateBack) {
            startBackAnimation(fragment)
            return
        }

        supportFragmentManager.beginTransaction()
            .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
            .replace(R.id.quizContent, fragment).commit()

    }

    private fun startBackAnimation(fragment: MyFragment) {
        startAnimatedVector(quizBack)
        Handler().postDelayed({
            changeContent(fragment)
        }, 500)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            1 -> {
                permissionResult(
                    grantResults.isNotEmpty() &&
                            grantResults[0] == PermissionChecker.PERMISSION_GRANTED
                )
            }
            else -> {
                super.onRequestPermissionsResult(requestCode, permissions, grantResults)
            }
        }
    }

}