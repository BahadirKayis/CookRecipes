package com.bahadir.mycookingapp.common

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bahadir.mycookingapp.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.snackbar.Snackbar

fun String.titleCount(): String {
    return if (this.length > 20) {
        this.substring(0, 20) + "..."
    } else {
        this
    }
}


fun String.idToImageUrl(imgType: String): String {

    return "https://spoonacular.com/recipeImages/$this-556x370.$imgType"
}

fun View.gone() {
    visibility = View.GONE
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.visibleOrGone(boolean: Boolean) {
    Log.e("visible", boolean.toString())
    if (boolean) {
        this.visible()
    } else {
        this.gone()
    }

}

fun snackBar(view: View, text: String, duration: Int) {
    Snackbar.make(view, text, duration).show()
}

fun Context.circularProgressDrawable(): Drawable {
    return CircularProgressDrawable(this).apply {
        strokeWidth = 5f
        centerRadius = 30f
        start()
    }
}

fun ImageView.glideImage(url: String?) {

    Glide.with(this.context)
        .load(url)
        .override(500, 500) //1
        .diskCacheStrategy(DiskCacheStrategy.DATA) //6
        .placeholder(this.context.circularProgressDrawable())
        .error(R.drawable.serving)
        .into(this)
}