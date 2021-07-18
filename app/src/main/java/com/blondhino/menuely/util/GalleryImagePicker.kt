package com.blondhino.menuely.util

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.blondhino.menuely.R
import com.blondhino.menuely.ui.components.MenuelySideMenuItem
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import java.io.File


@Composable
fun GalleryImagePicker(
    modifier: Modifier = Modifier,
    enabled: Boolean =false,
    onImageSelected: (uri: Uri, bitmap: ImageBitmap, multipart: MultipartBody.Part) -> Unit
) {
    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    var alreadySelected by remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current
    val bitmap = remember {
        mutableStateOf<Bitmap?>(null)
    }

    val launcher = rememberLauncherForActivityResult(
        contract =
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri = uri
    }
    Column(modifier = modifier) {

        Image(
            painterResource(id = R.drawable.ic_edit),
            "",
            modifier = Modifier.size(30.dp)
                .clickable {
                    if (enabled) {
                        alreadySelected = false
                        imageUri = null
                        launcher.launch("image/*")
                    }
                }
        )

        imageUri?.let {
            if (Build.VERSION.SDK_INT < 28) {
                if (!alreadySelected) {
                    bitmap.value = MediaStore.Images
                        .Media.getBitmap(context.contentResolver, it)
                    bitmap.value?.let { bitmap ->
                        createMultipart(it, LocalContext.current)?.let { it1 ->
                            onImageSelected(
                                it, bitmap.asImageBitmap(),
                                it1
                            )
                        }
                    }
                    alreadySelected = true
                }

            } else {
                if (!alreadySelected) {
                    val source = ImageDecoder
                        .createSource(context.contentResolver, it)
                    bitmap.value = ImageDecoder.decodeBitmap(source)
                    bitmap.value?.let { bitmap ->
                        createMultipart(it, LocalContext.current)?.let { it1 ->
                            onImageSelected(
                                it, bitmap.asImageBitmap(),
                                it1
                            )
                        }
                    }

                    alreadySelected = true
                }
            }
        }


    }

}