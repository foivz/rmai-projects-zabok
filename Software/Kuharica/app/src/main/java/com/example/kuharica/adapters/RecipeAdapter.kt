package com.example.kuharica.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kuharica.R
import com.example.kuharica.data.Recipe

class RecipeAdapter(
    private var recipes: List<Recipe>,
    private val onEditClick: (Recipe) -> Unit,
    private val onDeleteClick: (Recipe) -> Unit,
    private val onItemClick: (Recipe) -> Unit
) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    inner class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvRecipeName: TextView = itemView.findViewById(R.id.tvRecipeName)
        val btnEdit: ImageButton = itemView.findViewById(R.id.btnEdit)
        val btnDelete: ImageButton = itemView.findViewById(R.id.btnDelete)

        fun bind(recipe: Recipe) {
            tvRecipeName.text = recipe.name
            tvRecipeName.setOnClickListener { onItemClick(recipe) }
            btnEdit.setOnClickListener { onEditClick(recipe) }
            btnDelete.setOnClickListener { onDeleteClick(recipe) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recipe, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(recipes[position])
    }

    override fun getItemCount(): Int = recipes.size

    fun updateData(newRecipes: List<Recipe>) {
        recipes = newRecipes
        notifyDataSetChanged()
    }
}
