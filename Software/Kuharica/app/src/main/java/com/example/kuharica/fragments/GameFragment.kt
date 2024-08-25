package com.example.kuharica.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.kuharica.R

class GameFragment : Fragment() {

    private lateinit var gridLayout: GridLayout
    private lateinit var resultCounter: TextView
    private var selectedCard: ImageView? = null
    private val cardImages = listOf(R.drawable.keks, R.drawable.bombon, R.drawable.kosarica, R.drawable.vrecica) // Use your images
    private val cardPairs = cardImages + cardImages
    private val cardButtons = mutableListOf<ImageView>()
    private var score = 0
    private var isProcessing = false
    private var totalPairs = cardPairs.size / 2
    private var matchedPairs = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        gridLayout = view.findViewById(R.id.gridLayout)
        resultCounter = view.findViewById(R.id.result_counter)

        setupGame()
    }

    private fun setupGame() {

        cardButtons.clear()
        gridLayout.removeAllViews()
        matchedPairs = 0
        score = 0
        resultCounter.text = "Score: $score"


        val shuffledCards = cardPairs.shuffled()


        for (i in shuffledCards.indices) {
            val imageView = ImageView(context).apply {
                layoutParams = GridLayout.LayoutParams().apply {
                    width = 0
                    height = 0
                    columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
                    rowSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
                    setMargins(4, 4, 4, 4)
                }
                scaleType = ImageView.ScaleType.FIT_CENTER
                setImageResource(R.drawable.card_back)
                setOnClickListener { onCardClicked(this, shuffledCards[i]) }
            }
            cardButtons.add(imageView)
            gridLayout.addView(imageView)
        }
    }


    private fun onCardClicked(imageView: ImageView, cardImage: Int) {
        if (isProcessing || imageView.drawable.constantState != resources.getDrawable(R.drawable.card_back).constantState) return


        imageView.setImageResource(cardImage)

        if (selectedCard == null) {

            selectedCard = imageView
        } else {

            isProcessing = true
            val selectedCardImage = (selectedCard as ImageView).drawable
            val clickedCardImage = imageView.drawable

            if (selectedCardImage.constantState == clickedCardImage.constantState) {

                score++
                resultCounter.text = "Score: $score"
                matchedPairs++
                selectedCard = null
                isProcessing = false // End processing

                if (matchedPairs == totalPairs) {

                    showCompletionDialog()
                }
            } else {

                imageView.postDelayed({
                    imageView.setImageResource(R.drawable.card_back)
                    selectedCard?.setImageResource(R.drawable.card_back)
                    selectedCard = null
                    isProcessing = false // End processing
                }, 1000)
            }
        }
    }

    private fun showCompletionDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("Game Over")
            .setMessage("Čestitamo! Spojili ste sve parove.")
            .setPositiveButton("Igraj ponovno") { _, _ -> setupGame() }
            .setNegativeButton("Izađi") { dialog, _ -> dialog.dismiss() }
            .show()
    }
}
