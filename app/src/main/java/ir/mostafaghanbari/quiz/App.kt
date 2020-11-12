package ir.mostafaghanbari.quiz

import android.app.Application
import android.content.Context
import androidx.room.Room
import androidx.sqlite.db.SimpleSQLiteQuery
import ir.mostafaghanbari.quiz.model.RoomDB
import ir.mostafaghanbari.quiz.model.entities.UserModel
import ir.mostafaghanbari.quiz.presenter.QuestionPresenter

class App : Application() {

    companion object {
        lateinit var ctx: Context
        lateinit var db: RoomDB
    }

    override fun onCreate() {
        super.onCreate()
        ctx = this
        db = Room.databaseBuilder(this, RoomDB::class.java, "quiz_db")
            .allowMainThreadQueries()
            .build()

        checkQuestionStored()
    }

    private fun checkQuestionStored() {
        val questionsCount =  db.questionDao().getQuestionsCount()

        if(questionsCount == 0)
            QuestionPresenter().store()

    }


}