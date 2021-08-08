package com.blondhino.menuely.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.blondhino.menuely.R
import com.blondhino.menuely.ui.ui.theme.greyMedium
import com.blondhino.menuely.util.ImageLoader


@SuppressLint("UnrememberedMutableState")
@Composable
fun MenuelySearchUserTicket(
    id: Int = 0,
    titleText: String = "",
    descText: String = "",
    imageUrl: String = "",
    onInviteButtonClicked: (id : Int) -> Unit,
    onItemClick: (id: Int) -> Unit
) {
    val loadedMainImage = ImageLoader(imageUrl = imageUrl) { }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp, horizontal = 8.dp)
            .height(80.dp)
            .clickable { onItemClick(id) },
        shape = RoundedCornerShape(20.dp),
        backgroundColor = greyMedium
    ) {

        ConstraintLayout() {
            val (imagePlaceHolder, image, title, description, button) = createRefs()
            Image(
                painterResource(id = R.drawable.ic_empty_state_png),
                "",
                modifier = Modifier.constrainAs(imagePlaceHolder) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
            )

            MenuelyButton(modifier = Modifier
                .width(80.dp)
                .height(30.dp)
                .constrainAs(button) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end, 5.dp)
                }, text = "Invite",
                cornerRadius = 10.dp,
                textStyle = MaterialTheme.typography.h5,
            ) {
                onInviteButtonClicked(id)
            }

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
                    end.linkTo(button.start)
                    width= Dimension.fillToConstraints
                },
                style = MaterialTheme.typography.h2
            )

            Text(
                text = descText,
                modifier = Modifier.constrainAs(description) {
                    top.linkTo(title.bottom, 4.dp)
                    start.linkTo(imagePlaceHolder.end, 16.dp)
                    end.linkTo(parent.end, 8.dp)
                    bottom.linkTo(parent.bottom)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                },
                style = MaterialTheme.typography.subtitle2,

                )

        }
    }

}

