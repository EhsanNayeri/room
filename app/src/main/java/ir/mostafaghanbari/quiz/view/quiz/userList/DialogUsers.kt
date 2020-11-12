package ir.mostafaghanbari.quiz.view.quiz.userList

import android.app.Dialog
import android.content.Context
import android.database.Cursor
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.provider.ContactsContract
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import androidx.recyclerview.widget.LinearLayoutManager
import ir.mostafaghanbari.quiz.App
import ir.mostafaghanbari.quiz.R
import ir.mostafaghanbari.quiz.model.entities.UserModel
import ir.mostafaghanbari.quiz.presenter.UserPresenter
import ir.mostafaghanbari.quiz.view.quiz.ActivityQuiz
import ir.mostafaghanbari.quiz.view.utils.toast
import kotlinx.android.synthetic.main.dialog_user_list.*
import kotlinx.android.synthetic.main.dialog_user_list.view.*
import kotlin.time.milliseconds

class DialogUsers(
    ctx: Context = App.ctx,
    userClick: (user: UserModel) -> Unit
) : Dialog(ctx) {

    private val v = LayoutInflater.from(ctx).inflate(R.layout.dialog_user_list, null, false)
    private var userAdapter: AdapterUser = AdapterUser(ArrayList(), ctx) {
        cancel()
        userClick(it)
    }

    init {

        v.apply {
            RVUser.apply {
                layoutManager = LinearLayoutManager(ctx)
                adapter = userAdapter
            }

            closeUserList.setOnClickListener {
                cancel()
            }

        }
        v.lblUserList.text = "تاریخچه کاربران"
        setContentView(v)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        getUsers()
        show()
    }

    private fun getUsers() {
        v.pbarUserList.visibility = View.GONE
        val users = UserPresenter().getUsers()
        userAdapter.apply {
            this.data.clear()
            this.data.addAll(users)
            notifyDataSetChanged()
        }
        if (users.isEmpty())
            txtEmptyUserList.visibility = View.VISIBLE
    }


}




