package com.unilocal.app.ui.screens.user

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.unilocal.app.model.Location
import com.unilocal.app.model.Place
import com.unilocal.app.model.City
import com.unilocal.app.model.PlaceType
import com.unilocal.app.model.Schedule
import com.unilocal.app.navigation.NavRoutes
import com.unilocal.app.viewmodel.LocalMainViewModel
import java.time.DayOfWeek
import java.time.LocalTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterPlaceScreen(
    navController: NavController,
    onRegister: () -> Unit = {}
) {
    val mainViewModel = LocalMainViewModel.current
    val usersViewModel = mainViewModel.usersViewModel
    val placesViewModel = mainViewModel.placesViewModel

    val currentUser = usersViewModel.currentUser.collectAsState().value
    if (currentUser == null) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("No hay usuario logueado.")
        }
        return
    }

    // Estados de los campos
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var city by remember { mutableStateOf(City.ARMENIA) }
    var phone by remember { mutableStateOf("") }
    var type by remember { mutableStateOf(PlaceType.RESTAURANT) }
    var imageUrl by remember { mutableStateOf("") }
    var imageUrls by remember { mutableStateOf(listOf<String>()) }

    // Estados para horario
    var showSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val startTimeState = rememberTimePickerState()
    val endTimeState = rememberTimePickerState()
    val selectedDays = remember { mutableStateListOf<DayOfWeek>() }
    val schedules = remember { mutableStateListOf<Schedule>() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Registrar lugar") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Nombre del lugar") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Descripción") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = address,
                onValueChange = { address = it },
                label = { Text("Dirección") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = phone,
                onValueChange = { phone = it },
                label = { Text("Teléfono") },
                modifier = Modifier.fillMaxWidth()
            )

            // Selección de ciudad
            var cityExpanded by remember { mutableStateOf(false) }
            ExposedDropdownMenuBox(
                expanded = cityExpanded,
                onExpandedChange = { cityExpanded = !cityExpanded }
            ) {
                OutlinedTextField(
                    value = city.displayName,
                    onValueChange = {},
                    label = { Text("Ciudad") },
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = cityExpanded) },
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                )

                ExposedDropdownMenu(
                    expanded = cityExpanded,
                    onDismissRequest = { cityExpanded = false }
                ) {
                    City.entries.forEach { c ->
                        DropdownMenuItem(
                            text = { Text(c.displayName) },
                            onClick = {
                                city = c
                                cityExpanded = false
                            }
                        )
                    }
                }
            }

    // Selección de tipo de lugar
            var typeExpanded by remember { mutableStateOf(false) }
            ExposedDropdownMenuBox(
                expanded = typeExpanded,
                onExpandedChange = { typeExpanded = !typeExpanded }
            ) {
                OutlinedTextField(
                    value = type.displayName,
                    onValueChange = {},
                    label = { Text("Tipo de lugar") },
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = typeExpanded) },
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                )

                ExposedDropdownMenu(
                    expanded = typeExpanded,
                    onDismissRequest = { typeExpanded = false }
                ) {
                    PlaceType.entries.forEach { t ->
                        DropdownMenuItem(
                            text = { Text(t.displayName) },
                            onClick = {
                                type = t
                                typeExpanded = false
                            }
                        )
                    }
                }
            }

            // Campo de imagen
            OutlinedTextField(
                value = imageUrl,
                onValueChange = { imageUrl = it },
                label = { Text("URL de imagen") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    if (imageUrl.isNotBlank()) {
                        imageUrls = imageUrls + imageUrl
                        imageUrl = ""
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(Icons.Default.Add, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text("Agregar imagen")
            }

            if (imageUrls.isNotEmpty()) {
                Text("Imágenes agregadas:")
                imageUrls.forEach {
                    Text("• $it", fontSize = 13.sp, color = Color.Gray)
                }
            }

            // Horarios
            Button(
                onClick = { showSheet = true },
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(Icons.Default.Add, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text("Agregar horario")
            }

            if (schedules.isNotEmpty()) {
                schedules.forEach {
                    Text(it.toDisplayString(), fontSize = 14.sp, color = Color.DarkGray)
                }
            }

            // Botón registrar
            Button(
                onClick = {
                    if (title.isNotBlank() && description.isNotBlank() && address.isNotBlank()) {
                        val newPlace = Place(
                            id = (placesViewModel.places.value.size + 1).toString(),
                            title = title,
                            description = description,
                            address = address,
                            city = city,
                            location = Location(0.0, 0.0),
                            images = imageUrls,
                            phoneNumber = phone,
                            type = type,
                            schedules = schedules.toList(),
                            ownerId = currentUser.id
                        )

                        placesViewModel.create(newPlace)
                        onRegister()
                        navController.navigate(NavRoutes.Home.route) {
                            popUpTo(NavRoutes.RegisterPlace.route) { inclusive = true }
                        }

                        title = ""
                        description = ""
                        address = ""
                        phone = ""
                        imageUrl = ""
                        imageUrls = emptyList()
                        schedules.clear()

                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Registrar lugar")
            }
        }
    }

    // BottomSheet para agregar horarios
    if (showSheet) {
        ModalBottomSheet(
            onDismissRequest = { showSheet = false },
            sheetState = sheetState
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text("Selecciona días", fontWeight = FontWeight.SemiBold)

                val allDays = DayOfWeek.entries
                FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    allDays.forEach { day ->
                        FilterChip(
                            selected = selectedDays.contains(day),
                            onClick = {
                                if (selectedDays.contains(day)) selectedDays.remove(day)
                                else selectedDays.add(day)
                            },
                            label = { Text(day.name.take(3)) }
                        )
                    }
                }

                Text("Hora de apertura")
                TimePicker(state = startTimeState)

                Text("Hora de cierre")
                TimePicker(state = endTimeState)

                Button(
                    onClick = {
                        selectedDays.forEach { day ->
                            schedules.add(
                                Schedule(
                                    day = day,
                                    open = LocalTime.of(startTimeState.hour, startTimeState.minute),
                                    close = LocalTime.of(endTimeState.hour, endTimeState.minute)
                                )
                            )
                        }
                        showSheet = false
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Guardar horario")
                }
            }
        }
    }
}
