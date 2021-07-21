package com.blondhino.menuely.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.blondhino.menuely.R
import com.blondhino.menuely.util.GalleryImagePicker
import com.blondhino.menuely.util.ImageLoader

@Composable
fun MenuelyProductsHeader(
    titleText: String = "",
    headerUrl: String = "",
    height: Dp = 200.dp
) {
    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
        val (headerImage, headerOverlay, title) = createRefs()
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

        Text(text = titleText, modifier = Modifier.constrainAs(title) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            end.linkTo(parent.end)
            start.linkTo(parent.start)
        }, style = MaterialTheme.typography.subtitle1, fontSize = 36.sp, color = Color.White)


    }


}

