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
import ir.mostafaghanbari.quiz.view.quiz.ActivityQuiz
import ir.mostafaghanbari.quiz.view.utils.toast
import kotlinx.android.synthetic.main.dialog_user_list.view.*

class DialogContacts(
    ctx: Context = App.ctx,
    contactClick: (user: UserModel) -> Unit
) : Dialog(ctx), LoaderManager.LoaderCallbacks<Cursor> {

    private val act = (ctx as ActivityQuiz)
    private val v = LayoutInflater.from(ctx).inflate(R.layout.dialog_user_list, null, false)
    private var userAdapter: AdapterUser = AdapterUser(ArrayList(), ctx) {
        cancel()
        contactClick(it)
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
        setContentView(v)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        LoaderManager.getInstance(act).initLoader(0, null, this)
        show()
    }


    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {
        return CursorLoader(
            act,
            ContactsContract.Data.CONTENT_URI,
            arrayOf(
                ContactsContract.Contacts.DISPLAY_NAME_PRIMARY,
                ContactsContract.Data.DATA2,
                ContactsContract.Data.DATA3
            ),
            null,
            null,
            null
        )
    }

    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        val result = ArrayList<UserModel>()

        if (data?.moveToFirst() == true) {
            do {

                val fullName =
                    data.getString(data.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME_PRIMARY))
                        .trim()

                val family =
                    data.getString(data.getColumnIndex(ContactsContract.Data.DATA3))?.trim() ?: ""

                val name =
                    data.getString(data.getColumnIndex(ContactsContract.Data.DATA2))?.trim() ?: ""


                if (name.isNotEmpty() && TextUtils.isDigitsOnly(name) ||
                    family.isNotEmpty() && TextUtils.isDigitsOnly(family)
                ) {
                    continue
                }

                if (result.find { u -> u.fullName == fullName } != null) {
                    continue
                }

                if (name.isNotEmpty() && fullName.contains(name) ||
                    family.isNotEmpty() && fullName.contains(family)
                )
                    result.add(UserModel(1, name, family, fullName))


            } while (data.moveToNext())
        }

        userAdapter.apply {
            this.data.clear()
            this.data.addAll(result)
            notifyDataSetChanged()
        }

    }

    override fun onLoaderReset(loader: Loader<Cursor>) {
        toast("load reset")
    }

}




