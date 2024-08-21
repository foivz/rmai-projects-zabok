package com.example.kuharica.fragments

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
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
    private lateinit var searchView: SearchView
    private var allRecipes: List<Recipe> = listOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_base, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        searchView = view.findViewById(R.id.searchView)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val dividerDrawable: Drawable? = ContextCompat.getDrawable(requireContext(), R.drawable.divider_custom)
        val dividerItemDecoration = DividerItemDecoration(recyclerView.context, (recyclerView.layoutManager as LinearLayoutManager).orientation)
        dividerDrawable?.let {
            dividerItemDecoration.setDrawable(it)
        }
        recyclerView.addItemDecoration(dividerItemDecoration)

        loadRecipes()

        setupSearchView()

        return view
    }

    override fun onResume() {
        super.onResume()
        loadRecipes()
    }

    private fun setupSearchView() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterRecipes(newText)
                return true
            }
        })
    }

    private fun filterRecipes(query: String?) {
        val filteredRecipes = if (!query.isNullOrEmpty()) {
            allRecipes.filter { it.name.contains(query, ignoreCase = true) }
        } else {
            allRecipes
        }
        recipeAdapter.updateData(filteredRecipes)
    }

    private fun deleteRecipe(recipe: Recipe) {
        lifecycleScope.launch(Dispatchers.IO) {
            RecipeDatabase.getInstance(requireContext()).recipeDao().deleteRecipe(recipe)
            withContext(Dispatchers.Main) {
                loadRecipes()
                Toast.makeText(requireContext(), "${recipe.name} obrisan!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun loadRecipes() {
        lifecycleScope.launch(Dispatchers.IO) {
            allRecipes = RecipeDatabase.getInstance(requireContext()).recipeDao().getAllRecipes()
            withContext(Dispatchers.Main) {
                recipeAdapter = RecipeAdapter(
                    allRecipes,
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

        fragment.show(parentFragmentManager, "RecipeDetailDialog")
    }

    private fun editRecipe(recipe: Recipe) {
        val fragment = EditRecipeFragment()
        val bundle = Bundle()
        bundle.putParcelable("recipe", recipe)
        fragment.arguments = bundle

        fragment.setOnRecipeUpdatedListener(object : EditRecipeFragment.OnRecipeUpdatedListener {
            override fun onRecipeUpdated() {
                loadRecipes()
            }
        })

        fragment.show(parentFragmentManager, "EditRecipeDialog")
    }
}


