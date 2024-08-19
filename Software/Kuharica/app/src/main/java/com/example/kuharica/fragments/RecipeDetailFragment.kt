package com.example.kuharica.fragments

import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.kuharica.R
import com.example.kuharica.data.Recipe

class RecipeDetailFragment : DialogFragment() {

    private lateinit var tvRecipeName: TextView
    private lateinit var tvIngredients: TextView
    private lateinit var tvDescription: TextView
    private lateinit var btnClose: ImageButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_recipe_detail, container, false)

        tvRecipeName = view.findViewById(R.id.tvRecipeName)
        tvIngredients = view.findViewById(R.id.tvIngredients)
        tvDescription = view.findViewById(R.id.tvDescription)
        btnClose = view.findViewById(R.id.btnClose)

        btnClose.setOnClickListener {
            dismiss()
        }
        val recipe = arguments?.getParcelable<Recipe>("recipe")
        recipe?.let {
            tvRecipeName.text = it.name
            tvIngredients.text = createBoldedText("Sastojci:\n\n", it.ingredients)
            tvDescription.text = createBoldedText("Opis postupka:\n\n", it.description)
        }

        return view
    }
    private fun createBoldedText(boldPart: String, normalPart: String): SpannableString {
        val spannable = SpannableString("$boldPart\n$normalPart")
        spannable.setSpan(StyleSpan(Typeface.BOLD), 0, boldPart.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        return spannable
    }
}
