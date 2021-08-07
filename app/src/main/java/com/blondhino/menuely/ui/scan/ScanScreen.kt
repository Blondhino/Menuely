package com.blondhino.menuely.ui.scan

import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import com.blondhino.menuely.R
import com.blondhino.menuely.data.common.constants.NavigationRoutes
import com.blondhino.menuely.data.common.constants.Routes.BASE_URL
import com.blondhino.menuely.ui.cart.CartViewModel
import com.blondhino.menuely.ui.menus.MenusViewModel
import com.blondhino.menuely.ui.search_restaurant.SearchViewModel
import com.blondhino.menuely.ui.ui.theme.greenDark
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.journeyapps.barcodescanner.CaptureManager
import com.journeyapps.barcodescanner.CompoundBarcodeView
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ScanScreen(navController: NavHostController, menusViewModel: MenusViewModel,cartViewModel: CartViewModel, searchViewModel: SearchViewModel) {
    val context = LocalContext.current
    var scanFlag by remember {
        mutableStateOf(false)
    }
    val coroutineScope = rememberCoroutineScope()
    val compoundBarcodeView = remember {
        CompoundBarcodeView(context).apply {
            val capture = CaptureManager(context as Activity, this)
            capture.initializeFromIntent(context.intent, null)
            this.setStatusText("")
            this.resume()
            capture.decode()
            this.decodeContinuous { result ->
                if (scanFlag) {
                    return@decodeContinuous
                }
                scanFlag = true
                result.text?.let { barCodeOrQr ->
                    coroutineScope.launch {
                        try {
                            Log.d("qrResult:", barCodeOrQr)
                            searchViewModel.clickedRestaurantId.value=0
                            val restaurantId = barCodeOrQr.split(BASE_URL)[1].split("?restaurantId=")[1].split("&")[0]
                            val tableId = barCodeOrQr.split(BASE_URL)[1].split("&tableId=")[1]
                            cartViewModel.scannedRestaurantId.value=restaurantId.toInt()
                            cartViewModel.scannedTableId.value=tableId.toInt()
                            navController.navigate(NavigationRoutes.RESTAURANT_SCREEN_SINGLE)


                        } catch (e: Exception) {
                            Toast.makeText(
                                context,
                                context.getString(R.string.qr_error),
                                Toast.LENGTH_SHORT
                            ).show()
                            delay(2000)
                            scanFlag = false
                        }

                    }

                }


            }
        }
    }

    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        AndroidView(
            modifier = Modifier
                .size(300.dp)
                .border(BorderStroke(8.dp, greenDark), RoundedCornerShape(25.dp))
                .clip(shape = RoundedCornerShape(10.dp)),

            factory = { compoundBarcodeView },
        )
    }
}