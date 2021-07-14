package com.blondhino.menuely.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.blondhino.menuely.BuildConfig
import com.blondhino.menuely.R
import com.blondhino.menuely.ui.ui.theme.blackLight
import com.blondhino.menuely.ui.ui.theme.greenDark

@Composable
fun MenuelyAppVersion() {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
        Text(
            stringResource(R.string.app_version), style = TextStyle(
                fontSize = 10.sp, fontFamily = FontFamily(
                    Font(R.font.montserrat_light)
                ),
                color = blackLight
            ),
            modifier = Modifier.padding(top=24.dp)
        )
        Text(
            BuildConfig.VERSION_NAME, style = TextStyle(
                fontSize = 10.sp, fontFamily = FontFamily(
                    Font(R.font.montserrat_medium)
                ),
                color = greenDark
            )
        )
    }

}