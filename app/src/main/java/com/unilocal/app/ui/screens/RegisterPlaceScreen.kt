package com.unilocal.app.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.unilocal.app.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterPlaceScreen(
    navController: NavController
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.register_place_title)) },
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
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text(stringResource(R.string.place_name)) },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text(stringResource(R.string.place_category)) },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text(stringResource(R.string.place_description)) },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text(stringResource(R.string.place_phone)) },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = { /* TODO: subir imágenes */ },
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(Icons.Default.AccountBox, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text(stringResource(R.string.upload_images))
            }

            Button(
                onClick = { /* TODO: abrir horarios */ },
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(Icons.Default.AccountBox, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text(stringResource(R.string.add_schedule))
            }

            Button(
                onClick = { /* TODO: registrar lugar */ },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.register))
            }
        }
    }
}
