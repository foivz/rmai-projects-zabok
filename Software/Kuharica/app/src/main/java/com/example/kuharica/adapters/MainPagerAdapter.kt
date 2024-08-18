package com.example.kuharica.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.kuharica.fragments.BaseFragment
import com.example.kuharica.fragments.NewRecipeFragment
import com.example.kuharica.fragments.WeekFragment
import com.example.kuharica.R


class MainPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    // Define a list of FragmentItem objects to manage fragments and their details
    val fragmentItems = mutableListOf(
        FragmentItem(
            R.string.new_fragment, // Resource ID for the title
            R.drawable.baseline_post_add_24, // Resource ID for the icon
            NewRecipeFragment::class.java // Fragment class
        ),
        FragmentItem(
            R.string.base_fragment,
            R.drawable.baseline_storage_24,
            BaseFragment::class.java
        ),
        FragmentItem(
            R.string.week_fragment,
            R.drawable.baseline_calendar_today_24,
            WeekFragment::class.java
        )
    )

    // Provides the number of fragments
    override fun getItemCount(): Int = fragmentItems.size

    // Creates a new fragment based on the position
    override fun createFragment(position: Int): Fragment {
        return fragmentItems[position].fragmentClass.newInstance()
    }

    // Adds a fragment item to the list
    fun addFragment(fragmentItem: FragmentItem) {
        fragmentItems.add(fragmentItem)
        notifyDataSetChanged()
    }

    // Data class to hold fragment information
    data class FragmentItem(
        val titleRes: Int,
        val iconRes: Int,
        val fragmentClass: Class<out Fragment>
    )
}
