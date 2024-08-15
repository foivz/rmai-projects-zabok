package com.example.kuharica.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.kuharica.R
import com.example.kuharica.data.Recipe
import com.example.kuharica.data.RecipeDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewRecipeFragment : Fragment() {

    private lateinit var etRecipeName: EditText
    private lateinit var etIngredients: EditText
    private lateinit var etDescription: EditText
    private lateinit var btnSave: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_new_recipe, container, false)

        etRecipeName = view.findViewById(R.id.etRecipeName)
        etIngredients = view.findViewById(R.id.etIngredients)
        etDescription = view.findViewById(R.id.etDescription)
        btnSave = view.findViewById(R.id.btnSave)

        btnSave.setOnClickListener {
            saveRecipe()
        }

        return view
    }

    private fun saveRecipe() {
        val recipeName = etRecipeName.text.toString()
        val ingredients = etIngredients.text.toString()
        val description = etDescription.text.toString()

        if (recipeName.isNotEmpty() && ingredients.isNotEmpty() && description.isNotEmpty()) {
            val recipe = Recipe(0, recipeName, ingredients, description)

            lifecycleScope.launch(Dispatchers.IO) {
                RecipeDatabase.getInstance(requireContext()).recipeDao().insertRecipe(recipe)
            }
        }
    }
}
