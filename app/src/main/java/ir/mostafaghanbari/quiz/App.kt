package ir.mostafaghanbari.quiz

import android.app.Application
import android.content.Context
import androidx.room.Room
import ir.mostafaghanbari.quiz.model.RoomDB
import ir.mostafaghanbari.quiz.model.entities.UserModel

class App : Application() {

    companion object {
        lateinit var ctx: Context
        lateinit var db: RoomDB
    }

    override fun onCreate() {
        super.onCreate()
        ctx = this
        db = Room.databaseBuilder(this, RoomDB::class.java, "quiz_db").build()
    }


}