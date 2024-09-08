package com.example.heroesapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.heroesapp.activities.HomeActivity
import com.example.heroesapp.models.User
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    // Valores login
    lateinit var emailEditText : EditText
    lateinit var passwordEditTExt : EditText
    lateinit var  loginBtn : Button
    // Instancia
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        // Preferencias / isLogged.
        val sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE)
        val isLogged = sharedPreferences.getBoolean("isLogged",false)
        if (isLogged) {
            val loginSuccesful = Intent(this@MainActivity,HomeActivity::class.java)
            startActivity(loginSuccesful)
            finish()
        }
        // Valores login
        emailEditText = findViewById(R.id.emailET)
        passwordEditTExt = findViewById(R.id.passwordET)
        loginBtn = findViewById(R.id.btnLogin)
        loginBtn.setOnClickListener { v ->
            val email = emailEditText.text.toString()
            val password = passwordEditTExt.text.toString()
            // Login incorrecto, valores vacios.
            if (email.isEmpty() || password.isEmpty()) {
                Snackbar.make(v,"Correo o contraseña incorrectos",Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            // Verificar correo y contraseña.
            val isValidUser = User.users.any {
                user -> user.email == email && user.password == password
            }
            // Login incorrecto.
            if (!isValidUser) {
                Snackbar.make(v,"Correo o contraseña incorrectos",Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Login Succesful, Guardar preferencias
            val editor = sharedPreferences.edit()
            editor.putBoolean("isLogged",true)
            editor.apply()
            // Login Succesful, ingresar a HOME.
            val loginSuccesful = Intent(this@MainActivity,HomeActivity::class.java)
            startActivity(loginSuccesful)
            finish()
        }
    }
}