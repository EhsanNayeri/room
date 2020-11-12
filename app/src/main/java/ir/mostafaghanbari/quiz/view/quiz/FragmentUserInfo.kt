package ir.mostafaghanbari.quiz.view.quiz

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.PermissionChecker
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import ir.mostafaghanbari.quiz.R
import ir.mostafaghanbari.quiz.model.entities.UserModel
import ir.mostafaghanbari.quiz.view.quiz.userList.DialogContacts
import ir.mostafaghanbari.quiz.view.utils.MyFragment
import ir.mostafaghanbari.quiz.view.utils.toast
import kotlinx.android.synthetic.main.fragment_user_info.*

class FragmentUserInfo : MyFragment() {

    private val act = ctx as ActivityQuiz

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_user_info, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpButtons()

    }

    private fun setUpButtons() {
        btnStartQuiz.setOnClickListener {
            checkUserInputs()
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
            showContactList()

    }

    private fun showContactList() {
        DialogContacts {
            edtName.setText(it.name)
            edtFamily.setText(it.family)
        }
    }

    private fun requestContactPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            act.requestPermissions(arrayOf(Manifest.permission.READ_CONTACTS), 1)
        }
        act.permissionResult = { granted ->
            if (granted)
                showContactList()
            else
                toast("برای انتخاب از لیست کاربران اجازه دسترسی به مخاطبین ضروری است.")
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

        val user = UserModel(name, family)
        showSureDialog(user)

    }

    private fun showSureDialog(user: UserModel) {
        MaterialAlertDialogBuilder(ctx)
            .setTitle("شروع آزمون")
            .setMessage("آیا از شروع آزمون مطمئن هستید؟")
            .setPositiveButton("بله") { d, w ->
                startQuiz(user)
            }
            .setNegativeButton("خیر", null)
            .show()
    }

    private fun startQuiz(user: UserModel) {
        act.apply {
            this.user = user
            changeContent(FragmentQuiz(), true)
        }
    }

}