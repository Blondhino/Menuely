package com.blondhino.menuely.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable

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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.blondhino.menuely.R
import com.blondhino.menuely.ui.ui.theme.greenDark
import com.blondhino.menuely.ui.ui.theme.greyDark
import com.blondhino.menuely.ui.ui.theme.greyLight
import com.blondhino.menuely.ui.ui.theme.greyMedium
import com.blondhino.menuely.util.ImageLoader


@SuppressLint("UnrememberedMutableState")
@Composable
fun MenuelyOrderTicket(
    id: Int = 0,
    titleText: String = "",
    imageUrl: String = "",
    priceText: String = "",
    currency: String = "",
    onOrderClicked: (orderId: Int) -> Unit
) {
    val loadedMainImage = ImageLoader(imageUrl = imageUrl) { }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp, horizontal = 8.dp)
            .height(80.dp),
        shape = RoundedCornerShape(20.dp),
        backgroundColor = greyMedium
    ) {
        Box(modifier = Modifier.clickable {
            onOrderClicked(id)
        }) {
            ConstraintLayout() {
                val (imagePlaceHolder, image, title, price, status) = createRefs()
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
                        top.linkTo(parent.top, 8.dp)
                        start.linkTo(imagePlaceHolder.end, 16.dp)
                    },
                    style = MaterialTheme.typography.h2,
                    fontSize = 14.sp
                )

                Card(
                    modifier = Modifier
                        .constrainAs(price)
                    {
                        bottom.linkTo(parent.bottom, 8.dp)
                        end.linkTo(parent.end, 8.dp)
                        start.linkTo(imagePlaceHolder.end, 8.dp)
                    }.width(200.dp), backgroundColor = greenDark, shape = RoundedCornerShape(25)
                ) {
                    Box() {
                        Text(
                            "$priceText $currency",
                            modifier = Modifier
                                .align(Alignment.Center)
                                .padding(10.dp),
                            style = TextStyle(fontFamily = FontFamily(Font(R.font.montserrat_semi_bold))),
                            fontSize = 10.sp,
                            color = Color.White
                        )
                    }

                }

            }
        }
    }


}

