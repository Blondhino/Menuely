package com.blondhino.menuely.ui.base

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.core.view.WindowCompat
import com.blondhino.menuely.ui.ui.theme.MenuelyTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking

abstract class BaseComposeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //  WindowCompat.setDecorFitsSystemWindows(window, false)
        runBlocking {
            setContent {
                MenuelyTheme(darkTheme = false, content = setLayout())
            }
            fetchData()
        }

    }

    protected abstract fun setLayout(): @Composable() () -> Unit

    protected abstract fun fetchData()

}