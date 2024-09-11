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

class HeroesAdapter(val heroesList : List<Heroes>,val publiserId: Int,
    val onClick : (Heroes) -> Unit) : RecyclerView.Adapter<HeroesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroesViewHolder {
        var view: View?
        // Para cambiar el color de los superhéroes. Por el id del publisher mostrará un item o otro.
        if (publiserId == 0) {
            view = LayoutInflater.from(parent.context).inflate(R.layout.marvel_item,parent,false)
        } else {
            view = LayoutInflater.from(parent.context).inflate(R.layout.dc_item,parent,false)
        }
        return HeroesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return heroesList.count()
    }

    override fun onBindViewHolder(holder: HeroesViewHolder, position: Int) {
        val hero = heroesList[position]
        holder.heroeName.text = hero.name
        Picasso.get().load(hero.image).into(holder.heroeImage)
        holder.itemView.setOnClickListener {
            onClick(hero)
        }
    }

}

class HeroesViewHolder(view : View) : ViewHolder(view) {
    val heroeName : TextView = view.findViewById(R.id.heroes_name)
    val heroeImage : ImageView = view.findViewById(R.id.heroes_image)

}