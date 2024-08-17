package com.example.kuharica.fragments

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
}
