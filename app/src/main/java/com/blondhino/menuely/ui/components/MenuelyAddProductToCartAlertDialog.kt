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
import com.blondhino.menuely.data.common.enums.Mode
import com.blondhino.menuely.ui.ui.theme.blackLight
import com.blondhino.menuely.ui.ui.theme.greenDark
import com.blondhino.menuely.util.GalleryImagePickerMenus
import okhttp3.MultipartBody

@Composable
fun MenuelyAddProductToCartDialog(
    onDismiss: () -> Unit,
    productName:String="",
    productImageUrl : String ="",
    onProductAdded : () -> Unit
) {
    val isDialogOpen = remember { mutableStateOf(false) }
    val context = LocalContext.current
    if (!isDialogOpen.value) {
        AlertDialog(
            onDismissRequest = { isDialogOpen.value = false; onDismiss() },
            title = {
                Text(
                    text = productName,
                    style = MaterialTheme.typography.h1,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            },


            text = {
                Column(modifier = Modifier.fillMaxWidth(),horizontalAlignment = Alignment.CenterHorizontally) {
                    GalleryImagePickerMenus(
                        modifier = Modifier.padding(8.dp),
                        preloadedImageUrl = productImageUrl,
                        enabled = false
                    ) { uri, bitmap, multipart -> }
                }

            },


            confirmButton = {},
            dismissButton = {
                Column() {

                    MenuelyButton(modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),text = stringResource(
                                            R.string.add_to_cart)
                                        ) {
                        onProductAdded()
                    }
                }
            },
            shape = RoundedCornerShape(20.dp),
            properties = DialogProperties()
        )
    }

}