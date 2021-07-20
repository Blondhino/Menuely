package com.blondhino.menuely.util

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.media.Image
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.blondhino.menuely.R
import com.blondhino.menuely.ui.components.MenuelySideMenuItem
import com.blondhino.menuely.ui.ui.theme.greenDark
import com.blondhino.menuely.ui.ui.theme.greyLight
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import java.io.File


@Composable
fun GalleryImagePickerMenus(
    modifier: Modifier = Modifier,
    height: Dp =100.dp,
    enabled: Boolean = true,
    onImageSelected: (uri: Uri, bitmap: ImageBitmap, multipart: MultipartBody.Part) -> Unit
) {
    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }


    var imageBitmapLoaded by remember {
        mutableStateOf<ImageBitmap?>(null)
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

        ConstraintLayout() {
            val (uploadImageButton, imageUpload) = createRefs()
            Card(
                shape = RoundedCornerShape(15.dp),
                border = BorderStroke(1.dp, greyLight),
                modifier = Modifier
                    .constrainAs(uploadImageButton) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                        start.linkTo(parent.start)
                    }
                    .size(100.dp)
                    .height(height)

            ) {

                Box(modifier= Modifier
                    .height(height)
                    .clickable {
                        if (enabled) {
                            alreadySelected = false
                            imageUri = null
                            launcher.launch("image/*")
                        }
                    }
                )
                {
                    imageBitmapLoaded?.let { Image(bitmap= it,"", modifier = Modifier.fillMaxSize(), contentScale = ContentScale.Crop) }
                }

            }
        }

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
                            imageBitmapLoaded = bitmap.asImageBitmap()
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
                            imageBitmapLoaded = bitmap.asImageBitmap()
                        }
                    }

                    alreadySelected = true
                }
            }
        }


    }

}