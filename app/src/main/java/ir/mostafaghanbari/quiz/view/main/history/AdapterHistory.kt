package ir.mostafaghanbari.quiz.view.main.history

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.mostafaghanbari.quiz.App
import ir.mostafaghanbari.quiz.R
import ir.mostafaghanbari.quiz.model.entities.UserHistoryModel
import ir.mostafaghanbari.quiz.view.utils.getColorBaseScore
import kotlinx.android.synthetic.main.item_history.view.*

class AdapterHistory(
    private val data: List<UserHistoryModel>,
    private val ctx: Context = App.ctx
) : RecyclerView.Adapter<AdapterHistory.MyHolder>() {

    class MyHolder(v: View) : RecyclerView.ViewHolder(v)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder =
        MyHolder(LayoutInflater.from(ctx).inflate(R.layout.item_history, parent, false))

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.itemView.apply {
            with(data[position]) {
                historyUserName.text = user.fullName
                historyAnswerNumber.text = "${historie.answersCount} از ${historie.questionsCount} سوال پاسخ داده شده "
                historyTrueNumber.text =
                    "${historie.truesCount} پاسخ صحیح و  ${historie.mistakesCount} پاسخ اشتباه"
                historyScore.text = historie.totalScore.toString()
                historyScore.setTextColor(getColorBaseScore(historie.totalScore))
                historyPercent.setTextColor(getColorBaseScore(historie.totalScore))
            }
        }
    }

}