package com.example.roflobankomatvovastera.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roflobankomatvovastera.R

class MyAdapter(private val items: MutableList<String>) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    // ViewHolder хранит ссылки на элементы макета
    //ФРАГМЕНТ С РЕКУКЛЕРОМ - UserHistoryFragment!!!!!!!!!!!!!!!!!!!!!1
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemTV: TextView = view.findViewById(R.id.tv_item)
    }

    // Создает новый ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false)
        return ViewHolder(view)
    }

    // Привязывает данные к ViewHolder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemTV.text = items[position]
    }

    // Возвращает количество элементов
    override fun getItemCount(): Int {
        return items.size
    }
}