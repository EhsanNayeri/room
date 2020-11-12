package ir.mostafaghanbari.quiz.model.entities

import androidx.annotation.Keep
import androidx.annotation.NonNull
import androidx.room.*

@Keep
@Entity(tableName = "users")
data class UserModel(
    var name: String,
    var family: String
) {

    @PrimaryKey(autoGenerate = true) @NonNull
    var userId: Long = 0

    @Ignore
    var fullName: String = "$name $family"

}

@Keep
@Entity(tableName = "histories")
data class QuizHistory(
    var questionsCount: Int,
    var answersCount: Int,
    var truesCount: Int
){
    @PrimaryKey(autoGenerate = true) @NonNull
    var historyId: Int = 0

    @NonNull
    var userId: Long = 0

    @Ignore
    val mistakesCount: Long = (answersCount - truesCount).toLong()
}

data class UserHistoryModel(
    @Embedded val user: UserModel,
    @Relation(
        parentColumn = "userId",
        entityColumn = "userId"
    )
    val histories: List<QuizHistory>
)







