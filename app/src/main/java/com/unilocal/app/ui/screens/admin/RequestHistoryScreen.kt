package com.unilocal.app.ui.screens.admin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import coil.compose.AsyncImage
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.unilocal.app.viewmodel.LocalMainViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.foundation.layout.size
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.shape.CircleShape

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RequestHistoryScreen(navController: NavController) {
    val mainViewModel = LocalMainViewModel.current
    val places by mainViewModel.placesViewModel.places.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Historial de Solicitudes") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(places) { place ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
                        AsyncImage(
                            model = place.images.firstOrNull() ?: "",
                            contentDescription = place.title,
                            modifier = Modifier
                                .size(84.dp)
                                .clip(RoundedCornerShape(8.dp))
                        )

                        Spacer(modifier = Modifier.width(12.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Text(place.title, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                            Text(place.city.displayName, color = Color.Gray, fontSize = 13.sp)

                            Spacer(modifier = Modifier.height(8.dp))

                            // Estado simulado (más adelante vendrá del modelo)
                            val approved = (place.id.toIntOrNull() ?: 0) % 2 == 0
                            val bg = if (approved) Color(0xFF5E35B1) else Color(0xFFF3E5F5)
                            val textColor = if (approved) Color.White else Color(0xFF6A1B9A)

                            Box(
                                modifier = Modifier
                                    .padding(top = 8.dp)
                                    .background(bg, RoundedCornerShape(20.dp))
                                    .padding(horizontal = 12.dp, vertical = 6.dp)
                            ) {
                                Text(if (approved) "Aprobada" else "Rechazada", color = textColor)
                            }
                        }
                    }
                }
            }
        }
    }
}
