package com.example.kuharica.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class MainPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle)
    : FragmentStateAdapter(fragmentManager, lifecycle) {

    data class FragmentItem(val titleRes: Int, val iconRes: Int, val fragmentClass: KClass<out Fragment>)
    val fragmentItems = ArrayList<FragmentItem>()
    fun addFragment(fragment: FragmentItem) {
        fragmentItems.add(fragment)
    }

    override fun getItemCount(): Int = fragmentItems.size


    override fun createFragment(position: Int): Fragment {
        return fragmentItems[position].fragmentClass.java.newInstance() as Fragment

    }

    companion object {
        fun FragmentItem(titleRes: Int, iconRes: Int): MainPagerAdapter.FragmentItem {

        }
    }

}
