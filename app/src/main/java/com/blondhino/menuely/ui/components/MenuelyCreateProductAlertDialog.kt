package com.blondhino.menuely.ui.components

import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.blondhino.menuely.R
import com.blondhino.menuely.ui.ui.theme.blackLight
import com.blondhino.menuely.ui.ui.theme.greenDark
import com.blondhino.menuely.util.GalleryImagePickerMenus
import okhttp3.MultipartBody

@Composable
fun MenuelyCreateProductDialog(
    onDismiss: () -> Unit,
    onSave: () -> Unit,
    productNameValue: String = "",
    onProductNameValueChange: (value: String) -> Unit,
    priceValue: String = "0",
    onPriceValueChange: (value: String) -> Unit,
    descriptionValue: String = "",
    onDescriptionValueChange: (value: String) -> Unit,
    currency: String = "",
    onProductImageValueChanged: (bitmap: ImageBitmap, uri: Uri, multipart: MultipartBody.Part) -> Unit
) {
    val isDialogOpen = remember { mutableStateOf(false) }
    val context = LocalContext.current
    if (!isDialogOpen.value) {
        AlertDialog(
            onDismissRequest = { isDialogOpen.value = false; onDismiss() },
            title = {
                Text(
                    text = stringResource(R.string.product),
                    style = MaterialTheme.typography.h1,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            },


            text = {

                Column() {
                    MenuelyTextField(
                        inputText = productNameValue,
                        onInputTextChanged = { onProductNameValueChange(it) },
                        label = stringResource(R.string.product_name),
                        modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
                    )
                    Box(
                        Modifier.fillMaxWidth(),
                    ) {
                        MenuelyTextField(
                            inputText = priceValue,
                            onInputTextChanged = { onPriceValueChange(it) },
                            label = stringResource(R.string.price),
                            modifier = Modifier
                                .padding(top = 8.dp, bottom = 8.dp)
                                .fillMaxWidth(),
                            keyboardType = KeyboardType.Number
                        )
                        Text(
                            currency,
                            modifier = Modifier.align(Alignment.BottomEnd).padding(bottom = 20.dp, end = 8.dp)
                            ,
                            fontFamily = FontFamily(
                                Font(R.font.montserrat_regular)
                            ),
                            fontSize = 12.sp,
                            color = blackLight
                        )
                    }
                }

            },


            confirmButton = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        stringResource(R.string.cancel),
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .clickable {
                                onDismiss()
                            },
                        style = MaterialTheme.typography.h4,
                        fontSize = 14.sp,
                        color = blackLight
                    )
                    Text(
                        stringResource(R.string.save),
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .clickable {
                                onSave()
                            },
                        style = MaterialTheme.typography.h4,
                        fontSize = 14.sp,
                        color = greenDark
                    )
                }
            },
            dismissButton = {
                Column() {
                    MenuelyTextBox(
                        value = descriptionValue,
                        onValueChange = { value -> onDescriptionValueChange(value) })

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            stringResource(R.string.product_image),
                            modifier = Modifier.padding(8.dp),
                            style = MaterialTheme.typography.h3,
                            fontSize = 16.sp,
                            color = greenDark
                        )
                        GalleryImagePickerMenus(modifier = Modifier.padding(8.dp)) { uri, bitmap, multipart ->
                            onProductImageValueChanged(bitmap, uri, multipart)
                        }
                    }
                }

            },
            shape = RoundedCornerShape(20.dp),
            properties = DialogProperties()
        )
    }

}