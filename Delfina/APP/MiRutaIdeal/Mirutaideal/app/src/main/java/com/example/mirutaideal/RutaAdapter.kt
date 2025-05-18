package com.example.mirutaideal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RutaAdapter(private val rutas: List<Ruta>) : RecyclerView.Adapter<RutaAdapter.RutaViewHolder>() {

    class RutaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val rutaNameTextView: TextView = itemView.findViewById(R.id.text_ruta_name)
        // Add more views if your item layout has more data to show
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RutaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_ruta, parent, false)
        return RutaViewHolder(view)
    }

    override fun onBindViewHolder(holder: RutaViewHolder, position: Int) {
        val ruta = rutas[position]
        holder.rutaNameTextView.text = ruta.nombre // Adjust field name accordingly
    }

    override fun getItemCount(): Int = rutas.size
}
