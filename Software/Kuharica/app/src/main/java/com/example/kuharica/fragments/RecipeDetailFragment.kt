package com.example.kuharica.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.kuharica.R
import com.example.kuharica.data.Recipe

class RecipeDetailFragment : Fragment() {

    private lateinit var tvRecipeName: TextView
    private lateinit var tvIngredients: TextView
    private lateinit var tvDescription: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_recipe_detail, container, false)

        tvRecipeName = view.findViewById(R.id.tvRecipeName)
        tvIngredients = view.findViewById(R.id.tvIngredients)
        tvDescription = view.findViewById(R.id.tvDescription)

        // Preuzmite podatke iz Bundle-a
        val recipe = arguments?.getParcelable<Recipe>("recipe")
        recipe?.let {
            tvRecipeName.text = it.name
            tvIngredients.text = it.ingredients
            tvDescription.text = it.description
        }

        return view
    }
}
