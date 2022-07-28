package com.ameen.weatherphoto.presentation.extentions

import android.app.Activity
import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide

fun Toast.showToast(activity: Activity?, message: String) {
    Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
}


fun ImageView.loadImage(url: String) {
    Glide.with(this.context).load(url)
        .into(this)
}

fun ImageView.loadImage(url: Uri) {
    Glide.with(this.context).load(url)
        .into(this)
}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}