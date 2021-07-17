package com.blondhino.menuely.util

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition

@SuppressLint("UnrememberedMutableState")
@Composable
fun ImageLoader(
    imageUrl: String,
    onImageLoaded: (isLoaded: Boolean) -> Unit
): MutableState<Bitmap?>? {
    onImageLoaded(false)
    var bitmapState: MutableState<Bitmap?> = mutableStateOf(null)




    Glide.with(LocalContext.current)
        .asBitmap()
        .load(imageUrl)
        .into(object : CustomTarget<Bitmap>() {
            override fun onLoadCleared(placeholder: Drawable?) {}
            override fun onResourceReady(
                resource: Bitmap,
                transition: Transition<in Bitmap>?
            ) {

                bitmapState.value = resource;
                onImageLoaded(true)


            }
        })

    return bitmapState
}

