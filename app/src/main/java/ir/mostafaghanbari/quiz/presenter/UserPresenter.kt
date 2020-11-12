package ir.mostafaghanbari.quiz.presenter

import ir.mostafaghanbari.quiz.App
import ir.mostafaghanbari.quiz.model.entities.QuizHistory
import ir.mostafaghanbari.quiz.model.entities.UserHistoryModel
import ir.mostafaghanbari.quiz.model.entities.UserModel


class UserPresenter() {

    private val dao = App.db.userDao()

    fun storeUserHistory(user: UserModel, quizHistory: QuizHistory) {

        val userId = dao.storeUser(user)

        quizHistory.userId = userId
        dao.storeUserHistory(quizHistory)

    }

    fun getUserHistories() = dao.getUserHistories()

}