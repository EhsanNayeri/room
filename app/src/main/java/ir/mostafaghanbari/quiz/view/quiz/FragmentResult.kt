package ir.mostafaghanbari.quiz.view.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ir.mostafaghanbari.quiz.R
import ir.mostafaghanbari.quiz.view.utils.MyFragment

class FragmentResult:MyFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_result, container, false)

}