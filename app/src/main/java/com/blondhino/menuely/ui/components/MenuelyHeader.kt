package com.blondhino.menuely.ui.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.blondhino.menuely.R
import com.blondhino.menuely.util.ImageLoader
import com.google.accompanist.glide.rememberGlidePainter


@Composable
fun MenuelyHeader(
    modifier: Modifier,
    height: Dp = 200.dp,
    headerUrl: String = "",
    mainImageUrl: String = ""
) {
    Log.d("MenuelyHeader", "called");
    Log.d("MenuelyHeader", "header: $headerUrl mainImage: $mainImageUrl");
    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
        val (headerImage, headerOverlay, mainImage) = createRefs()
        val loadedMainImage =
            ImageLoader(imageUrl = mainImageUrl, placeHolder = R.drawable.ic_empty_state_png)
        val loadedHeaderImage =
            ImageLoader(imageUrl = headerUrl, placeHolder = R.drawable.ic_header_overlay)

        loadedHeaderImage.value?.asImageBitmap()?.let {
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


         loadedMainImage.value?.asImageBitmap()?.let {
             Image(
                 bitmap = it,
                 modifier = Modifier
                     .constrainAs(mainImage) {
                         top.linkTo(headerOverlay.bottom)
                         bottom.linkTo(headerImage.bottom)
                         end.linkTo(parent.end)
                         start.linkTo(parent.start)
                     }
                     .size(130.dp)
                     .clip(RoundedCornerShape(15.dp)),
                 contentScale = ContentScale.Crop,
                 contentDescription = ""
             )
         }


    }

}
