package ir.mostafaghanbari.quiz.view.main.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import ir.mostafaghanbari.quiz.R
import ir.mostafaghanbari.quiz.model.entities.UserHistoryModel
import ir.mostafaghanbari.quiz.presenter.UserPresenter
import ir.mostafaghanbari.quiz.view.main.ActivityMain
import ir.mostafaghanbari.quiz.view.utils.MyFragment
import kotlinx.android.synthetic.main.fragment_history.*

class FragmentHistory:MyFragment() {


    private val act = ctx as ActivityMain

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_history, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getHistories()

    }

    private fun getHistories() {
        val histories = UserPresenter().getUserHistories()

        setUpRV(histories)
    }

    private fun setUpRV(histories: List<UserHistoryModel>) {
        RVHistory.apply {
            layoutManager = LinearLayoutManager(ctx)
            adapter = AdapterHistory(histories)
        }
    }

}