package com.blondhino.menuely.ui.components

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.blondhino.menuely.ui.ui.theme.blackLight
import com.blondhino.menuely.ui.ui.theme.greenDark

@Composable

fun MenuelyAmountPicker(
    currentValue: Int = 0,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit,
    modifier: Modifier
) {
    val currentValueData = remember { mutableStateOf(currentValue) }
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White)
            .alpha(0.8F),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Card(
            modifier = Modifier
                .size(35.dp),
            shape = CircleShape,
            backgroundColor = Color.White,
            border = BorderStroke(2.dp, greenDark)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        if (currentValue != 0)
                            onDecrement()
                    },
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "-",
                    fontSize = 30.sp,
                    color = greenDark,
                    modifier = Modifier.offset(y = (-8).dp)
                )
            }
        }

        Text(
            text = currentValue.toString(),
            modifier = Modifier.padding(horizontal = 10.dp),
            fontSize = 20.sp,
            color = blackLight
        )

        Card(
            modifier = Modifier
                .size(35.dp),
            shape = CircleShape,
            backgroundColor = Color.White,
            border = BorderStroke(2.dp, greenDark)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        currentValueData.value = currentValue + 1
                        Log.d("picker", "oldValue: $currentValue, new value = ${currentValue + 1}")
                        onIncrement()

                    },
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "+",
                    fontSize = 20.sp,
                    color = greenDark,
                    modifier = Modifier.offset(y = (-2).dp)
                )
            }
        }

    }

}






