package com.unilocal.app.viewmodel

import androidx.lifecycle.ViewModel
import com.unilocal.app.model.City
import com.unilocal.app.model.Role
import com.unilocal.app.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class UsersViewModel: ViewModel(){

    private val _users = MutableStateFlow(emptyList<User>())
    val users: StateFlow<List<User>> = _users.asStateFlow()

    init {
        loadUsers()
    }

    fun loadUsers(){

        _users.value = listOf(
            User(
                id = "1",
                name = "Admin",
                username = "admin",
                role = Role.ADMIN,
                city = City.ARMENIA,
                email = "admin@email.com",
                password = "123456"
            ),
            User(
                id = "2",
                name = "Esteban",
                username = "estebanp",
                role = Role.USER,
                city = City.ARMENIA,
                //email = "esteban@email.com",
                email = "e",
                //password = "123456"
                password = "e"
            ),
            User(
                id = "3",
                name = "Felipe",
                username = "felipelv",
                role = Role.USER,
                city = City.ARMENIA,
                email = "felipelv@email.com",
                password = "123456"
            )
        )

    }

    fun create(user: User){
        _users.value = _users.value + user
    }

    fun findById(id: String): User?{
        return _users.value.find { it.id == id }
    }

    fun findByEmail(email: String): User?{
        return _users.value.find { it.email == email }
    }

    fun login(email: String, password: String): User?{
        return _users.value.find { it.email == email && it.password == password }
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
    }

    fun printUsers() {
        println("=== Lista de usuarios actuales ===")
        _users.value.forEach { user ->
            println("ID: ${user.id}, Nombre: ${user.name}, Email: ${user.email}, Username: ${user.username}, Password: ${user.password}, Ciudad: ${user.city.displayName}")
        }
        println("=== Fin de la lista ===")
    }



}