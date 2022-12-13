package com.example.listacompra.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.listacompra.BBDD.Listacompra
import com.example.listacompra.R


class ListaAdapter(val elementos: List<Listacompra>, val updateLista: (Listacompra) -> Unit, val deleteLista: (Listacompra) -> Unit) : RecyclerView.Adapter<ListaViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListaViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ListaViewHolder(layoutInflater.inflate(R.layout.elemento, parent, false))    }

    override fun onBindViewHolder(holder: ListaViewHolder, position: Int) {
        val item = elementos[position]

        holder.bind(item, updateLista, deleteLista)
    }

    override fun getItemCount(): Int {
        return elementos.size
    }


}