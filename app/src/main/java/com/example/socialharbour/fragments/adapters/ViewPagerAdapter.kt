package com.example.socialharbour.fragments.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    val fragmentList = mutableListOf<Fragment>()
    val titleList = mutableListOf<String>()

    override fun getCount(): Int {
        return fragmentList.size
    }

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titleList[position]
    }

    fun addFragments(fragment: Fragment, title: String) {
        fragmentList.add(fragment) // Add the fragment to the list
        titleList.add(title)       // Add the title to the list
    }
}
