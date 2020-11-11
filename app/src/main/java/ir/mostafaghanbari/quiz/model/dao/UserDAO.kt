package ir.mostafaghanbari.quiz.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import ir.mostafaghanbari.quiz.model.entities.UserHistory

@Dao
interface UserDAO {

    @Transaction
    @Insert
    fun storeUserHistory(data: UserHistory)

    @Transaction
    @Query("SELECT * FROM users")
    fun getUserHistories(): List<UserHistory>

}