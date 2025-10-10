package com.unilocal.app.viewmodel

import androidx.lifecycle.ViewModel
import com.unilocal.app.model.City
import com.unilocal.app.model.Role
import com.unilocal.app.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class UsersViewModel : ViewModel() {

    private val _users = MutableStateFlow(emptyList<User>())
    val users: StateFlow<List<User>> = _users.asStateFlow()

    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> = _currentUser.asStateFlow()

    init {
        if (_users.value.isEmpty()) {
            loadUsers()
        }
    }

    fun loadUsers() {
        _users.value = listOf(
            User(
                id = "1",
                name = "Admin",
                username = "admin",
                role = Role.ADMIN,
                city = City.ARMENIA,
                email = "admin", //Para facil acceso en el login
                password = "admin"
            ),
            User(
                id = "2",
                name = "Esteban",
                username = "estebanp",
                role = Role.USER,
                city = City.ARMENIA,
                email = "e",//Para facil acceso en el login
                password = "e"
            ),
            User(
                id = "3",
                name = "Felipe",
                username = "felipelv",
                role = Role.USER,
                city = City.ARMENIA,
                email = "f", //Para facil acceso en el login
                password = "f"
            )
        )
    }

    fun create(user: User) {
        _users.value = _users.value + user
    }

    fun findById(id: String): User? = _users.value.find { it.id == id }

    fun findByEmail(email: String): User? = _users.value.find { it.email == email }

    fun login(email: String, password: String): User? {
        val user = _users.value.find { it.email == email && it.password == password }
        _currentUser.value = user
        return user
    }

    fun logout() {
        _currentUser.value = null
    }

    fun registerUser(name: String, username: String, email: String, password: String, city: City) {
        val nextId = (_users.value.size + 1).toString()
        val user = User(
            id = nextId,
            name = name,
            username = username,
            email = email,
            password = password,
            role = Role.USER,
            city = city
        )
        _users.value = _users.value + user
        _currentUser.value = user
    }

    fun updateUser(
        id: String,
        name: String,
        username: String,
        email: String,
        password: String,
        city: City
    ) {
        _users.value = _users.value.map { user ->
            if (user.id == id) user.copy(
                name = name,
                username = username,
                email = email,
                password = password,
                city = city
            ) else user
        }

        _currentUser.value = _users.value.find { it.id == id }
    }


    fun setCurrentUser(user: User?) {
        _currentUser.value = user
    }

    fun clearCurrentUser() {
        _currentUser.value = null
    }

    fun printUsers() {
        println("=== Lista de usuarios actuales ===")
        _users.value.forEach { user ->
            println("ID: ${user.id}, Nombre: ${user.name}, Email: ${user.email}, Username: ${user.username}, Password: ${user.password}, Ciudad: ${user.city.displayName}")
        }
        println("=== Fin de la lista ===")
    }
}
