package com.blondhino.menuely.ui.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.blondhino.menuely.R

// Set of Material typography styles to start with
val Typography = Typography(
    h1 = TextStyle(
        fontFamily = FontFamily(Font(R.font.montserrat_semi_bold)),
        fontSize = 48.sp,
        color = blackLight
    ),
    h2 = TextStyle(
        fontFamily = FontFamily(Font(R.font.montserrat_semi_bold)),
        fontSize = 16.sp,
        color = blackLight
    ),

    h3 = TextStyle(
        fontFamily = FontFamily(Font(R.font.montserrat_medium)),
        fontSize = 14.sp,
        color = blackLight
    ),
    h4 = TextStyle(
        fontFamily = FontFamily(Font(R.font.montserrat_semi_bold)),
        fontSize = 14.sp,
        color = blackLight
    ),

    h5 = TextStyle(
        fontFamily = FontFamily(Font(R.font.montserrat_light)),
        fontSize = 14.sp,
        color = blackLight
    ),

    h6 = TextStyle(
        fontFamily = FontFamily(Font(R.font.montserrat_light)),
        fontSize = 12.sp,
        color = blackLight
    ),

    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),

    body2 = TextStyle(
        fontFamily = FontFamily(Font(R.font.montserrat_medium)),
        fontSize = 24.sp,
        color = blackLight
    ),

    caption = TextStyle(
        fontFamily = FontFamily(Font(R.font.montserrat_medium)),
        fontSize = 12.sp,
        color = blackLight
    ),

    subtitle2 = TextStyle(
        fontFamily = FontFamily(Font(R.font.montserrat_light)),
        fontSize = 9.sp,
        color = blackLight
    ),

    subtitle1 = TextStyle(fontFamily = FontFamily(Font(R.font.montserrat_extra_bold)))


)