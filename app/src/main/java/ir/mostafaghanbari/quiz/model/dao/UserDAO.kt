package ir.mostafaghanbari.quiz.model.dao

import androidx.room.*
import ir.mostafaghanbari.quiz.model.entities.QuizHistory
import ir.mostafaghanbari.quiz.model.entities.UserHistoryModel
import ir.mostafaghanbari.quiz.model.entities.UserModel

@Dao
interface UserDAO {

    @Insert
    fun storeUser(data: UserModel):Long

    @Insert
    fun storeUserHistory(data: QuizHistory)

    @Transaction
    @Query("SELECT * FROM users")
    fun getUserHistories(): List<UserHistoryModel>

}