package ir.mostafaghanbari.quiz.presenter

import com.google.gson.Gson
import ir.mostafaghanbari.quiz.App
import ir.mostafaghanbari.quiz.model.entities.AnswerModel
import ir.mostafaghanbari.quiz.model.entities.QuestionAnswersModel
import ir.mostafaghanbari.quiz.model.entities.QuestionModel
import ir.mostafaghanbari.quiz.view.utils.toast
import org.json.JSONArray
import java.io.ByteArrayInputStream

class QuestionPresenter(private val listener: QuestionListener? = null) {

    private val dao = App.db.questionDao()

    interface QuestionListener {

    }


    fun store() {

        val buffer = App.ctx.assets.open("questions.json").bufferedReader()
        var jsonString = ""
        buffer.forEachLine { line ->
            jsonString += line
        }

        val jsonArray = JSONArray(jsonString)

        val questionAnswers = ArrayList<QuestionAnswersModel>()

        for (i in 0 until jsonArray.length()){
        val questionAnswer = jsonArray.getJSONObject(i)
            questionAnswers.add(Gson().fromJson(questionAnswer.toString(), QuestionAnswersModel::class.java))
        }

        val questions = questionAnswers.map { qa -> qa.question }
        val questionsId = dao.storeQuestions(questions)

        val answers = questionAnswers.map { qa -> qa.answers }
        for (i in questionsId.indices) {
            answers[i].forEach { answer ->
                answer.questionId = questionsId[i]
            }
            dao.storeAnswers(answers[i])
        }

    }

    fun getQuestions() = dao.getQuestions()
    fun getAnswers() = dao.getAnswers()


}