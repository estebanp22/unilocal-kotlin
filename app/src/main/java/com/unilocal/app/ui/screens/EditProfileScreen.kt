package com.unilocal.app.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.unilocal.app.R

@Composable
fun EditProfileScreen(onSave: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text(stringResource(R.string.txt_only_name)) },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text(stringResource(R.string.txt_only_last_name)) },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text(stringResource(R.string.txt_email)) },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text(stringResource(R.string.txt_date)) },
            placeholder = { Text("dd-mm-aaaa") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = onSave,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(stringResource(R.string.txt_edit))
        }
    }
}
