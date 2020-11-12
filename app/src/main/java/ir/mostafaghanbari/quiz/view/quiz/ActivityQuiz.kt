package ir.mostafaghanbari.quiz.view.quiz

import android.os.Bundle
import android.os.Handler
import androidx.core.content.PermissionChecker
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import ir.mostafaghanbari.quiz.R
import ir.mostafaghanbari.quiz.model.entities.QuestionAnswersModel
import ir.mostafaghanbari.quiz.model.entities.UserModel
import ir.mostafaghanbari.quiz.view.utils.MyActivity
import ir.mostafaghanbari.quiz.view.utils.MyFragment
import ir.mostafaghanbari.quiz.view.utils.startAnimatedVector
import ir.mostafaghanbari.quiz.view.utils.stopAnimatedVector
import kotlinx.android.synthetic.main.activity_quiz.*

class ActivityQuiz : MyActivity() {

    lateinit var permissionResult: (granted: Boolean) -> Unit
    lateinit var questions: List<QuestionAnswersModel>
    lateinit var user: UserModel
    var quizFinished = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        changeContent(FragmentQuiz())

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

    private fun showConfirmCloseDialog() {
        MaterialAlertDialogBuilder(this)
            .setTitle("خروج")
            .setMessage("آیا می خواهید از آزمون خارج شوید؟")
            .setPositiveButton("بله") { d, w ->
                quizFinished = true
                onBackPressed()
            }
            .setNegativeButton("خیر", null)
            .show()
    }

    override fun onBackPressed() {
        if (quizFinished) {
            super.onBackPressed()
            this.finish()
        }else
            showConfirmCloseDialog()
    }

}