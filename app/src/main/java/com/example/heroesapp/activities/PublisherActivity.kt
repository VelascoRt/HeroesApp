package com.example.heroesapp.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.heroesapp.MainActivity
import com.example.heroesapp.R
import com.example.heroesapp.adapters.PublisherAdapter
import com.example.heroesapp.models.Publishers

class PublisherActivity : AppCompatActivity() {
    // Valores de usuario
    lateinit var usernameTV : TextView
    lateinit var logoutBtn : ImageView
    lateinit var publisherRecyclerView : RecyclerView
    // ?
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_publisher)

        // Preferencias
        val sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE)
        val name = sharedPreferences.getString("name","Usuario")

        // Valores de usuario
        usernameTV = findViewById(R.id.usernameTV)
        logoutBtn = findViewById(R.id.logoutBtn)

        // Publishers
        publisherRecyclerView = findViewById(R.id.publisher_recycleview)
        publisherRecyclerView.adapter = PublisherAdapter(Publishers.publishers) { publisher ->
            val heroesAct = Intent(this@PublisherActivity, HeroesActivity::class.java)
            heroesAct.putExtra("heroesId",publisher.id)
            startActivity(heroesAct)
        }
        publisherRecyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        usernameTV.text = "Bienvenido $name"

        // Logout | volver a iniciar sesión.
        logoutBtn.setOnClickListener {
            val editor = sharedPreferences.edit()
            editor.remove("isLogged")
            editor.apply()
            // Volver a iniciar sesión
            val logOut = Intent(this@PublisherActivity,MainActivity::class.java)
            startActivity(logOut)
            finish()
        }
    }
}