package com.blondhino.menuely.ui.components

import android.annotation.SuppressLint
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.blondhino.menuely.R
import com.blondhino.menuely.ui.ui.theme.*
import com.blondhino.menuely.util.ImageLoader


@SuppressLint("UnrememberedMutableState")
@Composable
fun MenuelyProductTicket(
    id: Int = 0,
    titleText: String = "",
    descriptionText: String = "",
    priceText: String = "",
    currency: String = "",
    imageUrl: String = "",
    onItemClick: (id: Int) -> Unit,
    onItemLongClick: (id: Int) -> Unit
) {
    val loadedMainImage = ImageLoader(imageUrl = imageUrl) { }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp, horizontal = 8.dp)
            .height(70.dp)
            .pointerInput(Unit) {
                detectTapGestures(
                    onLongPress = {
                        onItemLongClick(id)
                    },
                    onTap = { onItemClick(id) }
                )
            },
        shape = RoundedCornerShape(20.dp),
        backgroundColor = greyMedium
    ) {

        ConstraintLayout() {
            val (imagePlaceHolder, image, title, description, price) = createRefs()
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
                text = titleText,
                modifier = Modifier.constrainAs(title) {
                    top.linkTo(parent.top)
                    start.linkTo(imagePlaceHolder.end, 16.dp)
                    end.linkTo(parent.end,16.dp)
                    width= Dimension.fillToConstraints
                },
                style = MaterialTheme.typography.h2,
                fontSize = 16.sp,
                color= blackLight,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )

            Card(
                modifier = Modifier.constrainAs(price)
                {
                    top.linkTo(title.bottom)
                    end.linkTo(parent.end, 16.dp)
                }, backgroundColor = greenDark, shape = RoundedCornerShape(25)
            ) {
                Box() {
                    Text(
                        "$priceText $currency",
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(8.dp),
                        style = TextStyle(fontFamily = FontFamily(Font(R.font.montserrat_semi_bold))),
                        fontSize = 10.sp,
                        color = Color.White
                    )
                }
            }

            Text(
                text = descriptionText,
                modifier = Modifier.constrainAs(description) {
                    top.linkTo(title.bottom)
                    start.linkTo(imagePlaceHolder.end, 16.dp)
                    end.linkTo(price.start, 5.dp)
                    bottom.linkTo(parent.bottom)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                },
                style = MaterialTheme.typography.h6,
                color= blackLight,
                fontSize = 9.sp,
                overflow = TextOverflow.Ellipsis,
                maxLines = 3
            )
        }
    }

}

