package ir.mostafaghanbari.quiz.view.quiz

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.drawable.Animatable
import android.os.Build
import android.os.Bundle
import android.os.PatternMatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.PermissionChecker
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import ir.mostafaghanbari.quiz.R
import ir.mostafaghanbari.quiz.view.utils.MyFragment
import ir.mostafaghanbari.quiz.view.utils.toast
import kotlinx.android.synthetic.main.activity_quiz.*
import kotlinx.android.synthetic.main.fragment_user_info.*

class FragmentUserInfo : MyFragment() {

    private val act = ctx as ActivityQuiz

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = LayoutInflater.from(ctx).inflate(R.layout.fragment_user_info, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpButtons()

    }

    private fun setUpButtons() {
        btnStartQuiz.setOnClickListener {
            //checkUserInputs()
            startQuiz()
        }

        fabChooseFromContacts.setOnClickListener {
            checkContactPermission()
        }

    }

    private fun checkContactPermission() {
        if (PermissionChecker.checkSelfPermission(ctx, Manifest.permission.READ_CONTACTS) !=
            PermissionChecker.PERMISSION_GRANTED
        )
            requestContactPermission()
        else
            toast("grant detected")

    }

    private fun requestContactPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            act.requestPermissions(arrayOf(Manifest.permission.READ_CONTACTS), 1)
        }
        act.permissionResult = { granted ->
            toast(granted.toString())
        }
    }

    private fun checkUserInputs() {

        val name = edtName.text.toString().trim()
        if (name.isEmpty()) {
            edtName.apply {
                error = "لطفا نام را وارد کنید"
                requestFocus()
            }
            return
        }

        val family = edtFamily.text.toString().trim()
        if (family.isEmpty()) {
            edtFamily.apply {
                error = "لطفا نام خانوادگی را وارد کنید"
                requestFocus()
            }
            return
        }

        showSureDialog()

    }

    private fun showSureDialog() {
        MaterialAlertDialogBuilder(ctx)
            .setTitle("شروع آزمون")
            .setMessage("آیا از شروع آزمون مطمئن هستید؟")
            .setPositiveButton("بله") { d, w ->
                startQuiz()
            }
            .setNegativeButton("خیر", null)
            .show()
    }

    private fun startQuiz() {
        act.changeContent(FragmentUserInfo(), true)
    }

}