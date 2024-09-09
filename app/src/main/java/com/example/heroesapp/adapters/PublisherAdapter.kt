package com.example.heroesapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.heroesapp.R
import com.example.heroesapp.models.Companies
import com.squareup.picasso.Picasso
import java.util.concurrent.Flow.Publisher

class PublisherAdapter(val publisherList: List<Companies>) : RecyclerView.Adapter<PublisherViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PublisherViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.publisher_item,parent,false)
        return PublisherViewHolder(view)
    }

    override fun getItemCount(): Int {
        return publisherList.count()
    }

    override fun onBindViewHolder(holder: PublisherViewHolder, position: Int) {
        val publisher = publisherList[position]
        holder.publisherNameTV.text = publisher.name
        Picasso.get().load(publisher.image).into(holder.publisherImage)
    }
}

class PublisherViewHolder(val view : View) : ViewHolder(view) {
    val publisherNameTV : TextView = view.findViewById(R.id.publisher_name)
    val publisherImage : ImageView = view.findViewById(R.id.publisher_image)
}