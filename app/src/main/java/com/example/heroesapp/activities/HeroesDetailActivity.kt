package com.example.heroesapp.activities

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.heroesapp.R
import com.example.heroesapp.models.Heroes
import com.squareup.picasso.Picasso

class HeroesDetailActivity : AppCompatActivity() {

    lateinit var heroesTextView: TextView
    lateinit var heroesDescription: TextView
    lateinit var heroesImageView: ImageView
    lateinit var goBackBtn : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {

        // Dependiendo del publisher id mostrará un activity o otro.
        val publisherID = intent.getIntExtra("heroesPublisherId",0)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_mavel_detail)
        if (publisherID == 1) {
            setContentView(R.layout.activity_dc_detail)
        }
        // Variables del layout
        heroesTextView = findViewById(R.id.heroes_title)
        heroesDescription = findViewById(R.id.hero_description)
        heroesImageView = findViewById(R.id.hero_image)
        goBackBtn = findViewById(R.id.goBackBtn)
        val heroesID = intent.getIntExtra("heroesID",0)

        val hero = Heroes.heroes.firstOrNull() { hero : Heroes ->
            hero.id == heroesID
        }

        heroesTextView.text = hero?.name
        heroesDescription.text = hero?.description
        Picasso.get().load(hero?.image).into(heroesImageView)

        // Volver
        goBackBtn.setOnClickListener {
            val goBack = Intent(this@HeroesDetailActivity, PublisherActivity::class.java)
            startActivity(goBack)
        }
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right)
    }
}