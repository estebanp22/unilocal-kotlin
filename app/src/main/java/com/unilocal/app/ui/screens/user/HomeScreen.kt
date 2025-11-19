package com.unilocal.app.ui.screens.user

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.unilocal.app.R
import com.unilocal.app.navigation.NavRoutes
import com.unilocal.app.viewmodel.LocalMainViewModel

// ===== Mapbox (SOLO lo básico que sí existe en 11.9.1) =====
import com.mapbox.geojson.Point
import com.mapbox.maps.extension.compose.MapboxMap
import com.mapbox.maps.extension.compose.animation.viewport.rememberMapViewportState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    val mainViewModel = LocalMainViewModel.current
    val usersViewModel = mainViewModel.usersViewModel

    var expanded by remember { mutableStateOf(false) }
    var searchText by remember { mutableStateOf("") }

    val currentUser = usersViewModel.currentUser
    if (currentUser == null) {
        // Usuario no logueado → redirigir
        LaunchedEffect(Unit) {
            navController.navigate(NavRoutes.Login.route) {
                popUpTo(NavRoutes.Home.route) { inclusive = true }
            }
        }
        return
    }

    // === Estado de la cámara inicial (ej: Bogotá) ===
    val mapViewportState = rememberMapViewportState {
        setCameraOptions {
            center(Point.fromLngLat(-75.681111, 4.533889)) // Armenia, Quindío
            zoom(14.0)

        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.txt_mapas)) },
                actions = {
                    IconButton(onClick = { expanded = true }) {
                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = stringResource(R.string.cuenta)
                        )
                    }

                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text(stringResource(R.string.editar_datos)) },
                            onClick = {
                                expanded = false
                                navController.navigate(NavRoutes.EditProfile.route)
                            }
                        )
                        DropdownMenuItem(
                            text = { Text(stringResource(R.string.cerrar_sesion)) },
                            onClick = {
                                expanded = false
                                usersViewModel.clearCurrentUser()
                                navController.navigate(NavRoutes.Login.route) {
                                    popUpTo(NavRoutes.Home.route) { inclusive = true }
                                }
                            }
                        )
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = true,
                    onClick = { /* TODO Favoritos */ },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = stringResource(R.string.cd_favorites)
                        )
                    },
                    label = { Text(stringResource(R.string.nav_favorites)) }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate(NavRoutes.RegisterPlace.route) },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = stringResource(R.string.cd_add_place)
                        )
                    },
                    label = { Text(stringResource(R.string.nav_add_places)) }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate(NavRoutes.MyPlaces.route) },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Place,
                            contentDescription = stringResource(R.string.cd_my_places)
                        )
                    },
                    label = { Text(stringResource(R.string.nav_my_places)) }
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            // ======= MAPA REAL DE MAPBOX =======
            MapboxMap(
                modifier = Modifier.fillMaxSize(),
                mapViewportState = mapViewportState
                // Sin estilo custom: usa el estilo default de Mapbox
            )

            // ======= BARRA DE BÚSQUEDA FLOTANTE =======
            OutlinedTextField(
                value = searchText,
                onValueChange = { searchText = it },
                placeholder = { Text(stringResource(R.string.txt_search_place)) },
                leadingIcon = {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = stringResource(R.string.cd_search)
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .align(Alignment.TopCenter)
                    .clip(RoundedCornerShape(50))
                    .background(Color.White)
            )
        }
    }
}
