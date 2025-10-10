package com.unilocal.app.viewmodel

import androidx.compose.runtime.staticCompositionLocalOf

val LocalMainViewModel = staticCompositionLocalOf<MainViewModel> {
    error("MainViewModel is not provided")
}
