package com.blondhino.menuely.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.blondhino.menuely.R
import com.blondhino.menuely.ui.ui.theme.blackLight
import com.blondhino.menuely.ui.ui.theme.greenDark
import com.blondhino.menuely.ui.ui.theme.greyLight

@Composable
fun MenuelyCart(
    modifier: Modifier ,
    isEmpty: Boolean = true,
    onCartClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(15.dp),
        backgroundColor = Color.White,
        modifier = modifier
    ) {
        ConstraintLayout(modifier = Modifier.padding(8.dp) .clickable { onCartClick() }) {
            val (cart, indicator) = createRefs()
            Icon(
                painterResource(id = R.drawable.ic_cart),
                tint = blackLight,
                contentDescription = "lololol",
                modifier = Modifier
                    .constrainAs(cart) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                        start.linkTo(parent.start)
                    }
                    .size(30.dp)
            )

            Card(shape = CircleShape, modifier = Modifier
                .size(10.dp)
                .alpha(if(isEmpty)0F else 1F)
                .constrainAs(indicator) {
                    top.linkTo(cart.top)
                    bottom.linkTo(cart.top)
                    end.linkTo(cart.end)
                    start.linkTo(cart.end)

                }
                , backgroundColor = greenDark){}

        }
    }

}

