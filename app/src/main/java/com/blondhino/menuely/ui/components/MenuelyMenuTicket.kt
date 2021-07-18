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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.blondhino.menuely.R
import com.blondhino.menuely.ui.ui.theme.greyDark
import com.blondhino.menuely.ui.ui.theme.greyLight
import com.blondhino.menuely.ui.ui.theme.greyMedium
import com.blondhino.menuely.util.ImageLoader


@SuppressLint("UnrememberedMutableState")
@Composable
fun MenuelyMenuTicket(
    id: Int = 0,
    titleText: String = "",
    descText: String = "",
    onItemClick: (id: Int) -> Unit
) {
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
            val (imagePlaceHolder, image, title, description, arrow) = createRefs()
            Image(
                painterResource(id = R.drawable.ic_menu_green),
                "",
                modifier = Modifier
                    .constrainAs(imagePlaceHolder) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .width(60.dp)
            )


            Text(
                text = titleText,
                modifier = Modifier.constrainAs(title) {
                    top.linkTo(parent.top, 8.dp)
                    start.linkTo(imagePlaceHolder.end, 16.dp)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                },
                style = MaterialTheme.typography.h2,
                fontSize = 16.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
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
                maxLines = 3,
                overflow = TextOverflow.Ellipsis

            )
        }
    }

}

