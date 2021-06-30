package com.blondhino.menuely.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.blondhino.menuely.R
import com.blondhino.menuely.ui.ui.theme.greyLight
import com.blondhino.menuely.ui.ui.theme.greyMedium

@Composable
fun MenuelyInfoCard(title: String = "", description: String = "", @DrawableRes image: Int = 0, modifier: Modifier =Modifier) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = 5.dp,
        backgroundColor = greyMedium,
        shape = RoundedCornerShape(15.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = image),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth(0.2F)
                    .height(25.dp)
            )
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    title,
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.caption
                )
                Text(description, modifier = Modifier
                    .padding(bottom = 8.dp)
                    .fillMaxWidth(),
                    style = MaterialTheme.typography.subtitle2
                )
            }

        }
    }

}

@Composable
@Preview
fun previewInfoCard() {
    MenuelyInfoCard(
        "This is title",
        "description in two lines \n evo druge lajne ",
        R.drawable.ic_profile_green
    )
}