package com.unilocal.app.ui.screens.user

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.unilocal.app.R
import com.unilocal.app.model.City
import com.unilocal.app.viewmodel.LocalMainViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.text.input.PasswordVisualTransformation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(
    navController: NavController,
    onSave: () -> Unit
) {
    val mainViewModel = LocalMainViewModel.current
    val usersViewModel = mainViewModel.usersViewModel
    val context = LocalContext.current

    val currentUser = usersViewModel.currentUser.collectAsState().value

    // Si no hay usuario logueado
    if (currentUser == null) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(stringResource(R.string.txt_edit_profile_title)) },
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(Icons.Default.ArrowBack, contentDescription = stringResource(R.string.back))
                        }
                    }
                )
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("No hay un usuario logueado actualmente.")
            }
        }
        return
    }

    // Campos editables
    var name by remember { mutableStateOf(currentUser.name) }
    var username by remember { mutableStateOf(currentUser.username) }
    var email by remember { mutableStateOf(currentUser.email) }
    var password by remember { mutableStateOf(currentUser.password) }
    var selectedCity by remember { mutableStateOf(currentUser.city) }

    // Para el menú de ciudades
    var expanded by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.txt_edit_profile_title)) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = stringResource(R.string.back))
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text(stringResource(R.string.txt_only_name)) },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Nombre de usuario") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text(stringResource(R.string.txt_email)) },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Contraseña") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                OutlinedTextField(
                    value = selectedCity.displayName,
                    onValueChange = {},
                    label = { Text("Ciudad") },
                    readOnly = true,
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                )

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    City.values().forEach { city ->
                        DropdownMenuItem(
                            text = { Text(city.displayName) },
                            onClick = {
                                selectedCity = city
                                expanded = false
                            }
                        )
                    }
                }
            }

            Button(
                onClick = {
                    usersViewModel.updateUser(
                        id = currentUser.id,
                        name = name,
                        username = username,
                        email = email,
                        password = password,
                        city = selectedCity
                    )
                    Toast.makeText(context, "Perfil actualizado correctamente", Toast.LENGTH_SHORT).show()
                    onSave()
                    navController.popBackStack()
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(stringResource(R.string.txt_edit))
            }
        }
    }
}
