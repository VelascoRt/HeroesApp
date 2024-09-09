package com.example.heroesapp.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.heroesapp.R
import com.example.heroesapp.adapters.HeroesAdapter
import com.example.heroesapp.models.Publishers
import com.example.heroesapp.models.Heroes

class HeroesActivity : AppCompatActivity() {
    // Variables
    lateinit var publisherTitle : TextView
    lateinit var heroesLayout : LinearLayout
    lateinit var goBackBtn : ImageView
    lateinit var heroRecyclerView : RecyclerView

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_heroes)
        publisherTitle = findViewById(R.id.publisher_name)
        heroRecyclerView = findViewById(R.id.heroes_recyclerView)
        goBackBtn = findViewById(R.id.goBackBtn)
        heroesLayout = findViewById(R.id.marvel_headerLayout)
//        heroesFrame = findViewById(R.id.heroes_background)

        val publisherID = intent.getIntExtra("heroesId",0)
        val publisher = Publishers.publishers.firstOrNull { publisher ->
            publisher.id == publisherID
        }
        publisherTitle.text = getString(R.string.superheroesOf_text, publisher?.name)
        val heroes = Heroes.heroes.filter { hero ->
            hero.PublisherId == publisherID
        }
        // Colores HARDCODEADOS.
        if (publisherID == 1) {
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