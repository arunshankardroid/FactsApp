package com.arun.factsapp.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.arun.factsapp.R
import com.arun.factsapp.model.Fact
import com.bumptech.glide.Glide

class FactsAdapter(private var facts: List<Fact>) : RecyclerView.Adapter<FactsAdapter.FactViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_fact, parent, false)
        return FactViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: FactViewHolder, position: Int) {
        val fact = facts[position]
        fact.title?.let {
            viewHolder.title.text = it
        }
        fact.description?.let {
            viewHolder.description.text = it
        }
        fact.imageHref?.let {
            Glide.with(viewHolder.image.context).load(it).into(viewHolder.image)
        }
        viewHolder.title.showView(fact.title != null)
        viewHolder.description.showView(fact.description != null)
        viewHolder.image.showView(fact.imageHref != null)

    }

    override fun getItemCount(): Int {
        return facts.size
    }

    class FactViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.title)
        val image: ImageView = view.findViewById(R.id.imageView)
        val description: TextView = view.findViewById(R.id.description)
    }
}

fun View.showView(shouldShow: Boolean) {
    if (shouldShow) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}
