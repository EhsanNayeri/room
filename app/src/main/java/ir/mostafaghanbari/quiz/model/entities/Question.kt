package ir.mostafaghanbari.quiz.model.entities

import androidx.annotation.Keep
import androidx.annotation.NonNull
import androidx.room.*

@Keep
@Entity(tableName = "questions")
data class QuestionModel(
    var text: String
){
    @PrimaryKey(autoGenerate = true)
    @NonNull
    var questionId: Long = 0
}

@Keep
@Entity(tableName = "answers")
data class AnswerModel(
    var questionId: Long,
    var text: String,
    var itsAnswer: Boolean
){
    @PrimaryKey(autoGenerate = true)
    @NonNull
    var answerId: Long = 0

    @Ignore
    var choosed:Boolean = false
}

@Keep
data class QuestionAnswersModel(
    @Embedded var question:QuestionModel,
    @Relation(
        parentColumn = "questionId",
        entityColumn = "questionId"
    )
    var answers:List<AnswerModel>
)
