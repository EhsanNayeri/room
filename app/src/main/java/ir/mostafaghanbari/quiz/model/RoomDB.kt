package ir.mostafaghanbari.quiz.model

import androidx.room.Database
import androidx.room.RoomDatabase
import ir.mostafaghanbari.quiz.model.dao.QuestionDAO
import ir.mostafaghanbari.quiz.model.dao.UserDAO
import ir.mostafaghanbari.quiz.model.entities.AnswerModel
import ir.mostafaghanbari.quiz.model.entities.QuestionModel
import ir.mostafaghanbari.quiz.model.entities.QuizHistory
import ir.mostafaghanbari.quiz.model.entities.UserModel

@Database(entities = [QuestionModel::class, AnswerModel::class, UserModel::class, QuizHistory::class], version = 1)
abstract class RoomDB:RoomDatabase() {

    abstract fun questionDao():QuestionDAO
    abstract fun userDao(): UserDAO

}