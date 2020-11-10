package ir.mostafaghanbari.quiz.view.utils

import android.content.Context
import android.graphics.drawable.AnimatedVectorDrawable
import android.os.Build
import android.widget.ImageView
import android.widget.Toast
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import ir.mostafaghanbari.quiz.App

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