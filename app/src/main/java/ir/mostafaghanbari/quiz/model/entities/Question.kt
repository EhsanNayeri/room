package ir.mostafaghanbari.quiz.model.entities

import androidx.annotation.Keep
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Keep
@Entity(tableName = "questions")
data class QuestionModel(
    @PrimaryKey(autoGenerate = true) val questionId: Int,
    val text: String
)

@Keep
@Entity(tableName = "answers")
data class AnswerModel(
    @PrimaryKey(autoGenerate = true) val answerId: Int,
    val questionId: Int,
    val text: String,
    val itsAnswer: Boolean
)


data class QuestionAnswersModel(
    @Embedded val question:QuestionModel,
    @Relation(
        parentColumn = "questionId",
        entityColumn = "questionId"
    )
    val answers:List<AnswerModel>
)
