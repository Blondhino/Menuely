package com.blondhino.menuely.ui.components

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.blondhino.menuely.R
import com.blondhino.menuely.ui.ui.theme.greyDark
import com.blondhino.menuely.ui.ui.theme.greyLight
import com.blondhino.menuely.ui.ui.theme.greyMedium
import com.blondhino.menuely.util.ImageLoader


@SuppressLint("UnrememberedMutableState")
@Composable
fun MenuelyCategoryTicket(
    id: Int ?= 0,
    titleText: String ?= "",
    imageUrl: String ?= "",
    onItemClick: (id: Int) -> Unit,
    onItemLongClick: (id: Int) -> Unit
) {
    val loadedMainImage = ImageLoader(imageUrl = imageUrl.toString()) { }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp, horizontal = 8.dp)
            .height(80.dp)
            .pointerInput(Unit) {
                detectTapGestures(
                    onLongPress = {
                        id?.let { it1 -> onItemLongClick(it1) }
                    },
                    onTap = { id?.let { it1 -> onItemClick(it1) } }
                )
            },
        shape = RoundedCornerShape(20.dp),
        backgroundColor = greyMedium
    ) {

        ConstraintLayout() {
            val (imagePlaceHolder, image, title, description, arrow) = createRefs()
            Image(
                painterResource(id = R.drawable.ic_empty_state_png),
                "",
                modifier = Modifier.constrainAs(imagePlaceHolder) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
            )
            loadedMainImage?.value?.asImageBitmap()?.let {
                Image(
                    bitmap = it,
                    "",
                    modifier = Modifier.constrainAs(image) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        end.linkTo(imagePlaceHolder.end)
                        width = Dimension.fillToConstraints
                        height = Dimension.fillToConstraints
                    },
                    contentScale = ContentScale.Crop
                )
            }

            Text(
                text = titleText.toString(),
                modifier = Modifier.constrainAs(title) {
                    top.linkTo(parent.top)
                    start.linkTo(imagePlaceHolder.end, 16.dp)
                    bottom.linkTo(parent.bottom)
                },
                style = MaterialTheme.typography.h2
            )
        }
    }

}

