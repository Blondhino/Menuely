package com.blondhino.menuely.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.blondhino.menuely.R
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun MenuelyHeader(
    modifier: Modifier,
    height: Dp = 200.dp,
    headerUrl: String = "",
    mainImageUrl: String = ""
) {
    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
        val (headerImage, headerOverlay, mainImage) = createRefs()

        GlideImage(
            imageModel = headerUrl,
            placeHolder = ImageBitmap.imageResource(id = R.drawable.ic_header_overlay),
            error = ImageBitmap.imageResource(id = R.drawable.ic_header_overlay),
            modifier = Modifier
                .constrainAs(headerImage) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                }
                .height(height)
                .clip(RoundedCornerShape(0.dp, 0.dp, 15.dp, 15.dp)),
            contentScale = ContentScale.Crop,
        )
        GlideImage(
            imageModel = R.drawable.ic_header_overlay,

            modifier = Modifier
                .constrainAs(headerOverlay) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                }
                .height(height)
                .clip(RoundedCornerShape(0.dp, 0.dp, 15.dp, 15.dp)),
            contentScale = ContentScale.Crop
        )

        GlideImage(imageModel = mainImageUrl,
            placeHolder = ImageBitmap.imageResource(id = R.drawable.ic_empty_state_png),
            error = ImageBitmap.imageResource(id = R.drawable.ic_empty_state_png),
            modifier = Modifier
                .constrainAs(mainImage) {
                    top.linkTo(headerOverlay.bottom)
                    bottom.linkTo(headerImage.bottom)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                }
                .size(130.dp)
                .clip(RoundedCornerShape(15.dp)),
            contentScale = ContentScale.Crop
        )

    }

}
