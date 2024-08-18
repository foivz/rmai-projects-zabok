package com.example.kuharica.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kuharica.R
import com.example.kuharica.adapters.RecipeAdapter
import com.example.kuharica.data.Recipe
import com.example.kuharica.data.RecipeDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BaseFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var recipeAdapter: RecipeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_base, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        loadRecipes()

        return view
    }

    private fun deleteRecipe(recipe: Recipe) {
        lifecycleScope.launch(Dispatchers.IO) {
            RecipeDatabase.getInstance(requireContext()).recipeDao().deleteRecipe(recipe)
            withContext(Dispatchers.Main) {
                loadRecipes() // Ponovno učitajte popis recepata nakon brisanja
                Toast.makeText(requireContext(), "${recipe.name} obrisan!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loadRecipes() {
        lifecycleScope.launch(Dispatchers.IO) {
            val recipes = RecipeDatabase.getInstance(requireContext()).recipeDao().getAllRecipes()
            withContext(Dispatchers.Main) {
                recipeAdapter = RecipeAdapter(
                    recipes,
                    onEditClick = { recipe -> editRecipe(recipe) },
                    onDeleteClick = { recipe -> deleteRecipe(recipe) },
                    onItemClick = { recipe -> showRecipeDetails(recipe) }
                )
                recyclerView.adapter = recipeAdapter
            }
        }
    }

    private fun showRecipeDetails(recipe: Recipe) {
        val fragment = RecipeDetailFragment()
        val bundle = Bundle()
        bundle.putParcelable("recipe", recipe)
        fragment.arguments = bundle

        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment) // Promijenite na pravi ID vašeg container-a
            .addToBackStack(null)
            .commit()
    }

    private fun editRecipe(recipe: Recipe) {
        val fragment = EditRecipeFragment()
        val bundle = Bundle()
        bundle.putParcelable("recipe", recipe)
        fragment.arguments = bundle

        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    /* private fun editRecipe(recipe: Recipe) {
         // Ovdje pokrenite aktivnost ili fragment za uređivanje recepta
         Toast.makeText(requireContext(), "Editiranje: ${recipe.name}", Toast.LENGTH_SHORT).show()
         // Možete koristiti Bundle za prijenos podataka o receptu u novi fragment za uređivanje
     }*/



   /* private fun showRecipeDetails(recipe: Recipe) {
        // Ovdje otvorite novi prozor ili fragment koji prikazuje detalje recepta
        Toast.makeText(requireContext(), "Recept: ${recipe.name}", Toast.LENGTH_SHORT).show()
        // Možete koristiti Bundle za prijenos podataka o receptu u novi fragment za prikaz detalja
    }*/
}


/*package com.example.kuharica.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kuharica.R
import com.example.kuharica.adapters.RecipeAdapter
import com.example.kuharica.data.RecipeDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BaseFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var recipeAdapter: RecipeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_base, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        loadRecipes()

        return view
    }

    private fun loadRecipes() {
        lifecycleScope.launch(Dispatchers.IO) {
            val recipes = RecipeDatabase.getInstance(requireContext()).recipeDao().getAllRecipes()
            withContext(Dispatchers.Main) {
                recipeAdapter = RecipeAdapter(recipes)
                recyclerView.adapter = recipeAdapter
            }
        }
    }
}*/
