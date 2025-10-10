package com.unilocal.app.ui.screens.admin

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.unilocal.app.viewmodel.LocalMainViewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RequestListScreen(navController: NavController) {
    val mainViewModel = LocalMainViewModel.current
    val places by mainViewModel.placesViewModel.places.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Solicitudes de lugares") },
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
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(places) { place ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF7F3FA)),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            AsyncImage(
                                model = place.images.firstOrNull() ?: "",
                                contentDescription = place.title,
                                modifier = Modifier
                                    .size(90.dp)
                                    .clip(RoundedCornerShape(10.dp))
                            )

                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = place.title,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 16.sp,
                                    color = Color.Black
                                )
                                Text(
                                    text = place.city.displayName,
                                    color = Color.Gray,
                                    fontSize = 13.sp
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                Button(
                                    onClick = { /* aprobar */ },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color(0xFF7B4EFF)
                                    ),
                                    shape = RoundedCornerShape(50)
                                ) {
                                    Text("Aprobar", fontSize = 14.sp)
                                }

                                Button(
                                    onClick = { /* rechazar */ },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color(0xFFD0B3FF)
                                    ),
                                    shape = RoundedCornerShape(50)
                                ) {
                                    Text("Rechazar", fontSize = 14.sp, color = Color.Black)
                                }
                            }

                            OutlinedButton(
                                onClick = { /* ver más */ },
                                shape = RoundedCornerShape(40),
                                contentPadding = PaddingValues(vertical = 4.dp, horizontal = 8.dp)
                            ) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Text("Ver", fontSize = 12.sp)
                                    Text("más", fontSize = 12.sp)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
