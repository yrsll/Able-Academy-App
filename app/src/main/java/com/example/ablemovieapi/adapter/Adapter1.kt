package com.example.ablemovieapi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ablemovieapi.R
import com.example.ablemovieapi.data.MovieImages
import com.example.ablemovieapi.data.Poster


 class Adapter1 () : RecyclerView.Adapter<Adapter1.ViewHolder>() {
     private val list = mutableListOf<Poster>()
     fun setItems(list: List<Poster>) {
         this.list.clear()
         this.list.addAll(list)
         notifyDataSetChanged()

     }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        fun bind(product: Poster){
            val image = itemView.findViewById<ImageView>(R.id.IV)
            Glide.with(image).load("https://image.tmdb.org/t/p/original/${product.filePath}").into(image)

            val imageForBackDrops = itemView.findViewById<ImageView>(R.id.IVForBackdrops)
            Glide.with(imageForBackDrops).load("https://image.tmdb.org/t/p/original/${product.filePath}").into(imageForBackDrops)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
val view = LayoutInflater.from(parent.context).inflate(R.layout.for_adapter,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }
}
