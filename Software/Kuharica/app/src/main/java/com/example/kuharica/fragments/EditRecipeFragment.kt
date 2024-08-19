package com.example.kuharica.fragments

import android.graphics.Typeface
import android.os.Bundle
import android.text.Editable
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import com.example.kuharica.R
import com.example.kuharica.data.Recipe
import com.example.kuharica.data.RecipeDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EditRecipeFragment : DialogFragment() {

    interface OnRecipeUpdatedListener {
        fun onRecipeUpdated()
    }

    private var onRecipeUpdatedListener: OnRecipeUpdatedListener? = null

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
        recipe = arguments?.getParcelable("recipe")
        recipe?.let {
            etRecipeName.setText(formatText("Naziv recepta:\n\n",it.name))
            etIngredients.setText(formatText("Sastojci:\n\n", it.ingredients))
            etDescription.setText(formatText("Opis postupka:\n\n", it.description))
        }

        btnSaveChanges.setOnClickListener {
            saveChanges()
        }

        return view
    }

    override fun onStart() {
        super.onStart()
        // Postavite veličinu dijaloga na MATCH_PARENT za širinu i WRAP_CONTENT za visinu
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }

    private fun formatText(title: String, content: String): Editable {
        val fullText = "$title\n$content"
        val spannableStringBuilder = SpannableStringBuilder(fullText)
        val titleStart = fullText.indexOf(title)
        val titleEnd = titleStart + title.length

        // Primijeni boldanje na naslov
        spannableStringBuilder.setSpan(
            StyleSpan(Typeface.BOLD),
            titleStart,
            titleEnd,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        return spannableStringBuilder
    }

    private fun saveChanges() {
        val newRecipeName = etRecipeName.text.toString()
        val newIngredients = extractContent(etIngredients.text.toString())
        val newDescription = extractContent(etDescription.text.toString())

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
                        onRecipeUpdatedListener?.onRecipeUpdated() // Poziv callback funkcije
                        dismiss()
                    }
                }
            }
        } else {
            Toast.makeText(requireContext(), "Molimo popunite sva polja", Toast.LENGTH_SHORT).show()
        }
    }

    private fun extractContent(text: String): String {
        val indexOfNewLine = text.indexOf('\n')
        return if (indexOfNewLine != -1) {
            text.substring(indexOfNewLine + 1)
        } else {
            text
        }
    }

    fun setOnRecipeUpdatedListener(listener: OnRecipeUpdatedListener) {
        onRecipeUpdatedListener = listener
    }
}




/*package com.example.kuharica.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.kuharica.R
import com.example.kuharica.data.Recipe
import com.example.kuharica.data.RecipeDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EditRecipeFragment : DialogFragment() {

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
    override fun onStart() {
        super.onStart()
        // Postavite veličinu dijaloga na MATCH_PARENT za širinu i WRAP_CONTENT za visinu
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
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
                        dismiss()
                    }
                }
            }
        } else {
            Toast.makeText(requireContext(), "Molimo popunite sva polja", Toast.LENGTH_SHORT).show()
        }
    }
}*/
