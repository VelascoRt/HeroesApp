package com.example.heroesapp.models

data class User(val name: String, val email: String, val password: String) {
//    val computedName : String get() = "Bienvenido $name"
    companion object{
        val users = listOf(
            User("Victor","victor@hotmail.com","contrasea"),
            User("Samuel","samu1321@hotmail.com","pass"),
            User("Jonathan","dipshelby@hotmail.com","word"),
            User("Leonardo","leorambo@hotmail.com","ordwassp"),
            User("Juan","juanfr97@hotmail.com","12345"),
            User("Daniel","daniel98@hotmail.com","12345")
        )
    }
}