package com.example.heroesapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.heroesapp.R
import com.example.heroesapp.models.Heroes
import com.squareup.picasso.Picasso

class HeroesAdapter(val heroesList : List<Heroes>) : RecyclerView.Adapter<HeroesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.heroes_item,parent,false)
        return HeroesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return heroesList.count()
    }

    override fun onBindViewHolder(holder: HeroesViewHolder, position: Int) {
        val hero = heroesList[position]
        holder.heroeName.text = hero.name
        Picasso.get().load(hero.image).into(holder.heroeImage)
    }

}

class HeroesViewHolder(view : View) : ViewHolder(view) {
    val heroeName : TextView = view.findViewById(R.id.heroes_name)
    val heroeImage : ImageView = view.findViewById(R.id.heroes_image)

}