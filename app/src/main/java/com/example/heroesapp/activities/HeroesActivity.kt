package com.example.heroesapp.activities

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.heroesapp.MainActivity
import com.example.heroesapp.R
import com.example.heroesapp.adapters.HeroesAdapter
import com.example.heroesapp.adapters.PublisherAdapter
import com.example.heroesapp.models.Companies
import com.example.heroesapp.models.Heroes

class HeroesActivity : AppCompatActivity() {
    // Variables
    lateinit var companyTitle : TextView
    lateinit var heroesLayout : LinearLayout
    lateinit var heroesFrame : FrameLayout
    lateinit var goBackBtn : ImageView
    lateinit var heroRecyclerView : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_heroes)
        companyTitle = findViewById(R.id.company_name)
        heroRecyclerView = findViewById(R.id.heroes_recyclerView)
        goBackBtn = findViewById(R.id.goBackBtn)
        heroesLayout = findViewById(R.id.marvel_headerLayout)
//        heroesFrame = findViewById(R.id.heroes_background)

        val companyID = intent.getIntExtra("heroesId",0)
        val company = Companies.companies.firstOrNull { company ->
            company.id == companyID
        }
        companyTitle.text = "Superhéroes de " + company?.name
        val heroes = Heroes.heroes.filter { hero ->
            hero.PublisherId == companyID
        }
        // Colores HARDCODEADOS.
        if (companyID == 1) {
            heroesLayout.setBackgroundColor(Color.BLUE)

        } else {
            heroesLayout.setBackgroundColor(Color.RED)
//            heroesFrame.setBackgroundColor(Color.RED)
        }
        heroRecyclerView.adapter = HeroesAdapter(heroes)
        heroRecyclerView.layoutManager = GridLayoutManager(this,2)
        // Volver
        goBackBtn.setOnClickListener {
            val goBack = Intent(this@HeroesActivity, PublisherActivity::class.java)
            startActivity(goBack)
        }

    }
}