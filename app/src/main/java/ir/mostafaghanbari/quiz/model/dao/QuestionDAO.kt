package ir.mostafaghanbari.quiz.model.dao

import androidx.room.*
import ir.mostafaghanbari.quiz.model.entities.AnswerModel
import ir.mostafaghanbari.quiz.model.entities.QuestionAnswersModel
import ir.mostafaghanbari.quiz.model.entities.QuestionModel


@Dao
interface QuestionDAO {

    @Insert
    fun storeQuestions(data: List<QuestionModel>):LongArray

    @Insert
    fun storeAnswers(data: List<AnswerModel>)

    @Transaction
    @Query("SELECT * FROM questions")
    fun getQuestions(): List<QuestionAnswersModel>

    @Query("SELECT * FROM answers")
    fun getAnswers(): List<AnswerModel>

    @Query("SELECT COUNT(*) FROM questions")
    fun getQuestionsCount():Int

    @Query("DELETE FROM questions")
    fun deleteQuestions()

    @Query("DELETE FROM answers")
    fun deleteAnswers()

}