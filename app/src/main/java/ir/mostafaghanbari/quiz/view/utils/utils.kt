package ir.mostafaghanbari.quiz.view.utils

import android.content.Context
import android.graphics.drawable.AnimatedVectorDrawable
import android.os.Build
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import ir.mostafaghanbari.quiz.App
import ir.mostafaghanbari.quiz.R
import kotlinx.android.synthetic.main.fragment_result.*

fun toast(message: String, duration: Int = Toast.LENGTH_SHORT, ctx: Context = App.ctx) {
    Toast.makeText(ctx, message, duration).show()
}

fun startAnimatedVector(img: ImageView) {
    try {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            (img.drawable as AnimatedVectorDrawable).start()
        } else {
            (img.drawable as AnimatedVectorDrawableCompat).start()
        }
    } catch (e: Exception) {
    }
}

fun stopAnimatedVector(img: ImageView) {
    try {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            (img.drawable as AnimatedVectorDrawable).stop()
        } else {
            (img.drawable as AnimatedVectorDrawableCompat).stop()
        }
    } catch (e: Exception) {
    }
}

fun getColorBaseScore(score: Int): Int {
    return when (score) {
        in 0..50 -> {
            ContextCompat.getColor(App.ctx, R.color.red)
        }
        in 50..70 -> {
            ContextCompat.getColor(App.ctx, R.color.orange)
        }
        else -> {
            ContextCompat.getColor(App.ctx, R.color.green)
        }
    }
}