package com.unilocal.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.CompositionLocalProvider
import com.unilocal.app.navigation.Navigation
//import com.unilocal.app.ui.theme.UniLocalAppTheme
import com.unilocal.app.viewmodel.*
class MainActivity : ComponentActivity() {

    private val usersViewModel: UsersViewModel by viewModels()
    private val placesViewModel: PlacesViewModel by viewModels()
    private val reviewsViewModel: ReviewsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val mainViewModel = MainViewModel(
                placesViewModel,
                reviewsViewModel,
                usersViewModel
            )

            CompositionLocalProvider(
                LocalMainViewModel provides mainViewModel
            ) {
                Navigation(mainViewModel)
            }
        }
    }
}

