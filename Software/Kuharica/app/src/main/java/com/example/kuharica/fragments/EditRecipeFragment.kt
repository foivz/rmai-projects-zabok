package com.example.kuharica.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.kuharica.R
import com.example.kuharica.data.Recipe
import com.example.kuharica.data.RecipeDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EditRecipeFragment : Fragment() {

    private lateinit var etRecipeName: EditText
    private lateinit var etIngredients: EditText
    private lateinit var etDescription: EditText
    private lateinit var btnSaveChanges: Button
    private var recipe: Recipe? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_edit_recipe, container, false)

        etRecipeName = view.findViewById(R.id.etRecipeName)
        etIngredients = view.findViewById(R.id.etIngredients)
        etDescription = view.findViewById(R.id.etDescription)
        btnSaveChanges = view.findViewById(R.id.btnSaveChanges)

        // Preuzmi podatke iz bundle-a
        recipe = arguments?.getParcelable<Recipe>("recipe")
        recipe?.let {
            etRecipeName.setText(it.name)
            etIngredients.setText(it.ingredients)
            etDescription.setText(it.description)
        }

        btnSaveChanges.setOnClickListener {
            saveChanges()
        }

        return view
    }

    private fun saveChanges() {
        val newRecipeName = etRecipeName.text.toString()
        val newIngredients = etIngredients.text.toString()
        val newDescription = etDescription.text.toString()

        if (newRecipeName.isNotEmpty() && newIngredients.isNotEmpty() && newDescription.isNotEmpty()) {
            recipe?.let {
                val updatedRecipe = it.copy(
                    name = newRecipeName,
                    ingredients = newIngredients,
                    description = newDescription
                )

                lifecycleScope.launch(Dispatchers.IO) {
                    RecipeDatabase.getInstance(requireContext()).recipeDao().updateRecipe(updatedRecipe)
                    withContext(Dispatchers.Main) {
                        Toast.makeText(requireContext(), "Recept ažuriran", Toast.LENGTH_SHORT).show()
                        parentFragmentManager.popBackStack()
                    }
                }
            }
        } else {
            Toast.makeText(requireContext(), "Molimo popunite sva polja", Toast.LENGTH_SHORT).show()
        }
    }
}
