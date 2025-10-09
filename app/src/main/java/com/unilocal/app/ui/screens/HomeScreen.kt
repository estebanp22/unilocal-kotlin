package com.unilocal.app.ui.screens

import android.widget.Toast
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.unilocal.app.R
import com.unilocal.app.ui.NavRoutes


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    var expanded by remember { mutableStateOf(false) }

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

                    // Menú flotante al presionar el ícono
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
                    onClick = {navController.navigate(NavRoutes.RegisterPlace.route)},
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
                    onClick = {navController.navigate(NavRoutes.MyPlaces.route)},
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
            // Simulación del mapa
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.LightGray)
            )

            // Barra de búsqueda
            OutlinedTextField(
                value = "",
                onValueChange = {},
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

