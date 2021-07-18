package com.blondhino.menuely.ui.components

import android.annotation.SuppressLint
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.blondhino.menuely.R
import com.blondhino.menuely.util.GalleryImagePicker
import com.blondhino.menuely.util.ImageLoader
import okhttp3.MultipartBody


@SuppressLint("UnrememberedMutableState")
@Composable
fun MenuelyHeader(
    height: Dp = 200.dp,
    headerUrl: String = "",
    mainImageUrl: String = "",
    isInEditMode: Boolean = false,
    onMainImageSelected: (uri: Uri, bitmap: ImageBitmap, multipart: MultipartBody.Part) -> Unit  ,
    onCoverImageSelected: (uri: Uri, bitmap: ImageBitmap, multipart: MultipartBody.Part) -> Unit
) {
    Log.d("MenuelyHeader", "called");
    Log.d("MenuelyHeader", "header: $headerUrl mainImage: $mainImageUrl");
    var imageOverlayActive = mutableStateOf(true)
    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
        val (headerImage, headerOverlay, mainImage, mainImageOverlay, editProfile, editCover) = createRefs()
        val loadedMainImage =
            ImageLoader(imageUrl = mainImageUrl) { imageOverlayActive.value = !it }
        val loadedHeaderImage =
            ImageLoader(imageUrl = headerUrl) {}



        loadedHeaderImage?.value?.asImageBitmap()?.let {
            Image(
                bitmap = it,
                modifier = Modifier
                    .constrainAs(headerImage) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                        start.linkTo(parent.start)
                    }
                    .height(height)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(0.dp, 0.dp, 15.dp, 15.dp)),
                contentScale = ContentScale.Crop,
                contentDescription = ""
            )

        }

        Image(
            painter = painterResource(id = R.drawable.ic_header_overlay), contentDescription = "",
            modifier = Modifier
                .constrainAs(headerOverlay) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                }
                .height(height)
                .fillMaxWidth()
                .clip(RoundedCornerShape(0.dp, 0.dp, 15.dp, 15.dp)),
            contentScale = ContentScale.Crop,
        )


        loadedMainImage?.value?.asImageBitmap()?.let {
            Image(
                bitmap = it,
                modifier = Modifier
                    .constrainAs(mainImage) {
                        top.linkTo(headerOverlay.top, height - 65.dp)
                        end.linkTo(parent.end)
                        start.linkTo(parent.start)
                    }
                    .size(130.dp)
                    .clip(RoundedCornerShape(15.dp)),
                contentScale = ContentScale.Crop,
                contentDescription = ""
            )
        }

        Image(
            painterResource(id = R.drawable.ic_empty_state_png),
            modifier = Modifier
                .constrainAs(mainImageOverlay) {
                    top.linkTo(headerOverlay.top, height - 65.dp)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                }
                .size(130.dp)
                .clip(RoundedCornerShape(15.dp))
                .alpha(if (imageOverlayActive.value) 1F else 0F),
            contentScale = ContentScale.Crop,
            contentDescription = ""
        )

        GalleryImagePicker(
            enabled = isInEditMode,
            modifier = Modifier
                .constrainAs(editProfile) {
                    top.linkTo(mainImageOverlay.top)
                    bottom.linkTo(mainImageOverlay.top)
                    end.linkTo(mainImageOverlay.end)
                    start.linkTo(mainImageOverlay.end)
                }
                .alpha(if (isInEditMode) 1F else 0F),
            onImageSelected = { uri, bitmap, multipart ->
                onMainImageSelected(
                    uri,
                    bitmap,
                    multipart
                )
            }
        )

        GalleryImagePicker(
            enabled = isInEditMode ,
            modifier = Modifier
                .constrainAs(editCover) {
                    top.linkTo(parent.top,16.dp)
                    end.linkTo(parent.end,16.dp)

                }
                .alpha(if (isInEditMode) 1F else 0F),
            onImageSelected = { uri, bitmap, multipart ->
                onCoverImageSelected(
                    uri,
                    bitmap,
                    multipart
                )
            }
        )


    }

}
