package com.example.heroesapp.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.transition.Transition
import android.transition.TransitionManager
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.ColorUtils
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.heroesapp.R
import com.example.heroesapp.adapters.HeroesAdapter
import com.example.heroesapp.models.Publishers
import com.example.heroesapp.models.Heroes

class HeroesActivity : AppCompatActivity() {

    lateinit var publisherTitle : TextView
    lateinit var heroesLayout : LinearLayout
    lateinit var goBackBtn : ImageView
    lateinit var heroRecyclerView : RecyclerView

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_heroes)

        // Variables del layout
        publisherTitle = findViewById(R.id.publisher_name)
        heroRecyclerView = findViewById(R.id.heroes_recyclerView)
        goBackBtn = findViewById(R.id.goBackBtn)
        heroesLayout = findViewById(R.id.marvel_headerLayout)
//        heroesFrame = findViewById(R.id.heroes_background)
        val publisherID = intent.getIntExtra("heroesPublisherId",0)
        val publisher = Publishers.publishers.firstOrNull { publisher ->
            publisher.id == publisherID
        }
        publisherTitle.text = getString(R.string.superheroesOf_text, publisher?.name)
        val heroes = Heroes.heroes.filter { hero ->
            hero.PublisherId == publisherID
        }

        // Colores HARDCODEADOS.
        if (publisherID == 1) {
            heroesLayout.setBackgroundColor(Color.rgb(30,42,120))
        } else {
            heroesLayout.setBackgroundColor(Color.rgb(255,46,76))
//            heroesFrame.setBackgroundColor(Color.RED)
        }

        // Adaptador del Heroes dependiendo del publisherID, el cuál lo mandará al siguiente Intent.
        heroRecyclerView.adapter = HeroesAdapter(heroes,publisherID) {
            hero: Heroes ->
            val heroesDetail = Intent(this@HeroesActivity,HeroesDetailActivity::class.java)
            heroesDetail.putExtra("heroesID",hero.id)
            heroesDetail.putExtra("heroesPublisherId",publisherID)
            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left)
            startActivity(heroesDetail)
        }
        heroRecyclerView.layoutManager = GridLayoutManager(this,2)

        // Volver
        goBackBtn.setOnClickListener {
            val goBack = Intent(this@HeroesActivity, PublisherActivity::class.java)
            overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right)
            startActivity(goBack)
        }

    }
    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right)
    }
}