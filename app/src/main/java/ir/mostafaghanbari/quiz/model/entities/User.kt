package ir.mostafaghanbari.quiz.model.entities

import androidx.annotation.Keep
import androidx.room.*

@Keep
@Entity(tableName = "users")
data class UserModel(
    @PrimaryKey(autoGenerate = true) val userId: Int,
    val name: String,
    val family: String,
    @Ignore val fullName: String
)

@Keep
@Entity(tableName = "histories")
data class QuizHistory(
    @PrimaryKey(autoGenerate = true) val historyId: Int,
    val userId: Int,
    val questionsCount: Int,
    val answersCount: Int,
    val truesCount: Int,
    @Ignore val mistakesCount: Int
)

data class UserHistory(
    @Embedded val user: UserModel,
    @Relation(
        parentColumn = "userId",
        entityColumn = "userId"
    )
    val histories: List<QuizHistory>
)