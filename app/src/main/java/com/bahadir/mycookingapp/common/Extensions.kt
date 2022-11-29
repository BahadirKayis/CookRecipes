package com.bahadir.mycookingapp.common

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import androidx.core.content.FileProvider
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.bahadir.mycookingapp.R
import com.bahadir.mycookingapp.data.model.remote.filter.FilterTypes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

fun String.titleCount(count: Int): String {
    return if (this.length > count) {
        this.substring(0, count) + "..."
    } else {
        this
    }
}

fun List<FilterTypes>.pars(): String {
    var filterType = ""
    this.forEach {
        if (it.checked) filterType += it.name.lowercase() + ","
    }

    return filterType
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
    if (boolean) {
        this.visible()
    } else {
        this.gone()
    }

}

fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}


fun View.snackBar(text: String, duration: Int) {
    Snackbar.make(this, text, duration).show()
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

suspend fun Context.imageDownloadSaveFile(photoName: String, url: String): String {
    try {
        val image = File(filesDir, photoName)
        val imageUri = FileProvider.getUriForFile(
            this,
            "com.bahadir.mycookingapp.fileProvider",
            image
        ).toString()

        val loading = ImageLoader(this)
        val request = ImageRequest.Builder(this)
            .data(url)
            .build()

        val result = (loading.execute(request) as SuccessResult).drawable
        val bitmap = (result as BitmapDrawable).bitmap

        val outputStream =
            this.contentResolver.openOutputStream(Uri.parse(imageUri))
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        withContext(Dispatchers.IO) {
            outputStream!!.close()
        }
        return imageUri
    } catch (e: Exception) {
        Log.e("getBitmap-Ex", e.toString())

        return ""
    }

}