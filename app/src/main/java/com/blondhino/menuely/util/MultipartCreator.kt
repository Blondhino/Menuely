package com.blondhino.menuely.util

import android.content.Context
import android.content.ContextWrapper
import android.net.Uri
import android.util.Log
import androidx.compose.ui.platform.LocalContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.FileOutputStream

fun createMultipart(uri: Uri, context: Context): MultipartBody.Part? {


    uri.let {
        val dir = ContextWrapper(context).getDir(
            "imgDir",
            Context.MODE_PRIVATE
        )
        val fileForUpload = File(dir, System.currentTimeMillis().toString()+"_img")
        FileOutputStream(fileForUpload).use { oStream ->
            context.contentResolver.openInputStream(uri)?.use { iStream ->
                oStream.write(iStream.readBytes())
            }
        }
        val file = File(fileForUpload.path)
        val reqBody = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
        return MultipartBody.Part.createFormData("image", file.name, reqBody)
    }
}