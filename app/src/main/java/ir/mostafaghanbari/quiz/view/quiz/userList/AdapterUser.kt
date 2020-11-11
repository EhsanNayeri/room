package ir.mostafaghanbari.quiz.view.quiz.userList

import android.content.Context
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.mostafaghanbari.quiz.App
import ir.mostafaghanbari.quiz.R
import ir.mostafaghanbari.quiz.model.entities.UserModel
import kotlinx.android.synthetic.main.item_user.view.*

class AdapterUser(
    val data: ArrayList<UserModel>,
    private val ctx: Context = App.ctx,
    private val listener: (user: UserModel) -> Unit
) : RecyclerView.Adapter<AdapterUser.MyHolder>() {

    class MyHolder(v: View) : RecyclerView.ViewHolder(v)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder =
        MyHolder(LayoutInflater.from(ctx).inflate(R.layout.item_user, parent, false))

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.itemView.apply {
            userItem.apply {
                text = data[position].fullName
                setOnClickListener {
                    listener(data[position])
                }
            }

        }
    }

}