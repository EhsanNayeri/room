package ir.mostafaghanbari.quiz.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import ir.mostafaghanbari.quiz.model.entities.QuestionAnswersModel


@Dao
interface QuestionDAO {

    @Transaction
    @Insert
    fun storeQuestions(data: List<QuestionAnswersModel>)

    @Transaction
    @Query("SELECT * FROM questions")
    fun getQuestions(): List<QuestionAnswersModel>

}