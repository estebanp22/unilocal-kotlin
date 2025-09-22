package com.unilocal.app.ui.screens


import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import com.unilocal.app.R
import com.unilocal.app.ui.components.InputText

@Composable
fun LoginScreen(
    onNavigateToRegister: () -> Unit,
    onNavigateToHome: () -> Unit
) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    val context = LocalContext.current

    Surface {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
            modifier = Modifier
                .fillMaxSize()
                .padding(30.dp)
        ) {
            // Logo de UniLocal (usa un recurso tuyo en res/drawable/)
            Image(
                modifier = Modifier.width(150.dp),
                painter = painterResource(R.drawable.ic_launcher_foreground),
                contentDescription = stringResource(R.string.txt_image)
            )

            // Campo Email
            InputText(
                value = email,
                label = stringResource(R.string.txt_email),
                supportingText = stringResource(R.string.txt_email_error),
                onValueChange = { email = it },
                onValidate = {
                    email.isBlank() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()
                },
                icon = Icons.Outlined.Email
            )

            // Campo Password
            InputText(
                value = password,
                isPassword = true,
                label = stringResource(R.string.txt_password),
                supportingText = stringResource(R.string.txt_password_error),
                onValueChange = { password = it },
                onValidate = {
                    password.isBlank() || password.length < 5
                },
                icon = Icons.Outlined.Lock
            )

            // Botón Login
            Button(
                onClick = {
                    if (email == "demo@unilocal.com" && password == "12345") {
                        onNavigateToHome()
                        Toast.makeText(context, (R.string.toast_welcome), Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(
                            context,
                            (R.string.toast_invalid_credentials),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Outlined.Check,
                    contentDescription = stringResource(R.string.btn_register)
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(stringResource(R.string.btn_login))
            }

            // Botón ir a Registro
            Button(
                onClick = { onNavigateToRegister() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Outlined.Person,
                    contentDescription = stringResource(R.string.btn_create_account)
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(stringResource(R.string.btn_create_account))
            }
        }
    }
}
